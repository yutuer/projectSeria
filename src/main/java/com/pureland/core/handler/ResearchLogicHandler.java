package com.pureland.core.handler;

import com.pureland.common.db.data.Building;
import com.pureland.common.db.data.Resource;
import com.pureland.common.db.data.UserExt;
import com.pureland.common.db.statics.EntityModel;
import com.pureland.common.enums.SubServerTypeEnum;
import com.pureland.common.enums.ResourceServerTypeEnum;
import com.pureland.common.error.CoreException;
import com.pureland.common.service.BuildingCommonService;
import com.pureland.common.service.SkillCommonService;
import com.pureland.common.service.UserExtCommonService;
import com.pureland.common.service.UserRaceCommonService;
import com.pureland.common.service.bean.SkillInfo;
import com.pureland.common.service.impl.BuildingCommonServiceImpl;
import com.pureland.common.service.impl.SkillCommonServiceImpl;
import com.pureland.common.service.impl.UserExtCommonServiceImpl;
import com.pureland.common.service.impl.UserRaceCommonServiceImpl;
import com.pureland.common.util.SpringContextUtil;
import com.pureland.common.service.ResourceCommonService;
import com.pureland.core.init.EntityModelHelper;
import com.pureland.core.service.UserRaceService;
import com.pureland.common.service.impl.ResourceCommonServiceImpl;
import com.pureland.core.service.impl.UserRaceServiceImpl;
import com.pureland.common.service.bean.ResearchBean;
import com.pureland.common.util.GameUtil;

import org.apache.commons.collections.CollectionUtils;

import java.util.List;
import java.util.Set;

/**
 * Created by Administrator on 2015/1/31.
 */
public abstract class ResearchLogicHandler {

    private UserRaceCommonService userRaceCommonService = (UserRaceCommonService) SpringContextUtil.getBean(UserRaceCommonServiceImpl.class.getSimpleName());
    private UserRaceService userRaceService = (UserRaceService) SpringContextUtil.getBean(UserRaceServiceImpl.class.getSimpleName());
    private BuildingCommonService buildingCommonService = (BuildingCommonService) SpringContextUtil.getBean(BuildingCommonServiceImpl.class.getSimpleName());
    private SkillCommonService skillCommonService = (SkillCommonService) SpringContextUtil.getBean(SkillCommonServiceImpl.class.getSimpleName());

    public void research(ResearchBean researchBean) throws CoreException {
        Building laboratory = null;
        List<Building> laboratorys = null;
        //1.判断有没有laboratory
        laboratorys = buildingCommonService.getBuildingBySubType(researchBean.getUserRaceId(), SubServerTypeEnum.Laboratory);
        if (CollectionUtils.isEmpty(laboratorys)) {
            throw new CoreException(String.format("玩家 %d没有 LABORATORY 的建筑\n", researchBean.getUserRaceId()));
        }

        //2.判断自己是否有已经解锁
        Set<String> set = userRaceCommonService.getSkillShop(researchBean.getUserRaceId());
        if (!set.contains(String.valueOf(researchBean.getCid()))) {
            throw new CoreException(String.format("玩家 %d没有解锁 %d\n", researchBean.getUserRaceId(), researchBean.getCid()));
        }

        //只有1个laboratory
        laboratory = laboratorys.get(0);
        //3.判断开启等级
        EntityModel em = EntityModelHelper.ENTITIES.get(researchBean.getCid());
        EntityModel laboratoryEm = EntityModelHelper.ENTITIES.get(laboratory.getCid());
        if (laboratoryEm.getLevel() < em.getLevel()) {
            throw new CoreException(String.format("玩家 %d开启等级不足 当前laberaoty:%d\n", researchBean.getUserRaceId(), laboratory.getCid()));
        }

        //4.已经不能升级
        if (em.getUpgradeId().intValue() == 0) {
            throw new CoreException(String.format("玩家 %d已经到最高级 %d\n", researchBean.getUserRaceId()));
        }

        research0(researchBean, laboratory);
    }

    public void skillUpgradeComplete(Long userRaceId, Integer skillCid) throws CoreException {
        //清空升级队列
        buildingCommonService.deleteUpgradeSkillInfo(userRaceId);

        //更新玩家skillShop
        EntityModel researchEm = EntityModelHelper.ENTITIES.get(skillCid);
        Integer upgradeId = researchEm.getUpgradeId();
        userRaceCommonService.updateSkillShopOneSkillId(userRaceId, String.valueOf(skillCid), String.valueOf(upgradeId));

        //更改生产队列中的技能id
        userRaceService.updateAllBuildingSkillCid(userRaceId, skillCid, upgradeId);

        //更改已经造完的技能id
        skillCommonService.upgradeAllSkills(userRaceId, skillCid, upgradeId);
    }

    public abstract void research0(ResearchBean researchBean, Building laboratory) throws CoreException;

    public static class ResearchUpgradeHandler extends ResearchLogicHandler {

        private BuildingCommonService buildingCommonService = (BuildingCommonService) SpringContextUtil.getBean(BuildingCommonServiceImpl.class.getSimpleName());
        private UserExtCommonService userExtCommonService = (UserExtCommonService) SpringContextUtil.getBean(UserExtCommonServiceImpl.class.getSimpleName());
        private ResourceCommonService resourceCommonService = (ResourceCommonService) SpringContextUtil.getBean(ResourceCommonServiceImpl.class.getSimpleName());

        @Override
        public void research0(ResearchBean researchBean, Building laboratory) throws CoreException {
            EntityModel em = EntityModelHelper.ENTITIES.get(researchBean.getCid());
            EntityModel upgradeEm = EntityModelHelper.ENTITIES.get(em.getUpgradeId());

            //4.判断资源
            UserExt userExt = userExtCommonService.getUserExt(researchBean.getUserRaceId());
            ResourceServerTypeEnum costType = ResourceServerTypeEnum.getResourceServerTypeEnumByName(em.getCostResourceType());
            Resource resource = userExt.getResourcesForMap().get(costType);
            if (costType == ResourceServerTypeEnum.GoldOrOil) {

            } else {
                if (upgradeEm.getCostResourceCount().intValue() > resource.getCount().intValue()) {
                    throw new CoreException(String.format("玩家 %d没有足够的货币, need:%d, has:%d\n", researchBean.getUserRaceId(), upgradeEm.getCostResourceCount().intValue(), resource.getCount().intValue()));
                }
            }

            //5.判断升级技能队列是否为空
            SkillInfo skillInfo = buildingCommonService.getUpgradeSkillInfo(researchBean.getUserRaceId());
            if (skillInfo.getUpgradeSkillCid() != null && skillInfo.getUpgradeSkillCid().intValue() > 0) {
                throw new CoreException(String.format("玩家 %d正在升级技能:%d\n", researchBean.getUserRaceId(), skillInfo.getUpgradeSkillCid()));
            }

            long finishTime = researchBean.getCurrentTime() + upgradeEm.getBuildTime() * 1000L;
            buildingCommonService.saveUpgradeSkillInfo(researchBean.getUserRaceId(), new SkillInfo(researchBean.getCid(), finishTime));

            //扣除掉消费
            resource.setCount(resource.getCount() - upgradeEm.getCostResourceCount().intValue());
            resourceCommonService.updateResource(resource);
        }
    }

    public static class ResearchCompleteHandler extends ResearchLogicHandler {

        private BuildingCommonService buildingCommonService = (BuildingCommonService) SpringContextUtil.getBean(BuildingCommonServiceImpl.class.getSimpleName());

        @Override
        public void research0(ResearchBean researchBean, Building laboratory) throws CoreException {
            Long userRaceId = researchBean.getUserRaceId();
            Integer clientCid = researchBean.getCid();
            long clientNow = researchBean.getCurrentTime();
            SkillInfo skillInfo = buildingCommonService.getUpgradeSkillInfo(userRaceId);
            //校验时间
            if (GameUtil.IsTimeWrong(clientNow, skillInfo.getUpgradeSkillCompleteTime().longValue())) {
                throw new CoreException("时间不正确,reqIn:%d, now:%d", clientNow, skillInfo.getUpgradeSkillCompleteTime());
            }

            if (clientCid.intValue() != skillInfo.getUpgradeSkillCid().intValue()) {
                throw new CoreException("传入的cid 不一致, reqIn:%d, server:%d", clientCid, skillInfo.getUpgradeSkillCid());
            }

            skillUpgradeComplete(userRaceId, clientCid);
        }
    }

    public static class ResearchUpgradeCompleteImmediateHandler extends ResearchLogicHandler {

        private BuildingCommonService buildingCommonService = (BuildingCommonService) SpringContextUtil.getBean(BuildingCommonServiceImpl.class.getSimpleName());
        private UserExtCommonService userExtCommonService = (UserExtCommonService) SpringContextUtil.getBean(UserExtCommonServiceImpl.class.getSimpleName());
        private ResourceCommonService resourceCommonService = (ResourceCommonService) SpringContextUtil.getBean(ResourceCommonServiceImpl.class.getSimpleName());

        @Override
        public void research0(ResearchBean researchBean, Building laboratory) throws CoreException {
            //4.判断升级技能队列是否是要立即完成的
            SkillInfo skillInfo = buildingCommonService.getUpgradeSkillInfo(researchBean.getUserRaceId());
            if (skillInfo.getUpgradeSkillCid() != researchBean.getCid()) {
                throw new CoreException(String.format("玩家 %d立即完成升级技能cid不相符:%d\n", researchBean.getUserRaceId(), skillInfo.getUpgradeSkillCid()));
            }

            //判断资源够不够
            int seconds = (int) ((skillInfo.getUpgradeSkillCompleteTime() - researchBean.getCurrentTime()) / 1000);
            int consume = GameUtil.getConsumeByTime(seconds);
            UserExt userExt = userExtCommonService.getUserExt(researchBean.getUserRaceId());
            Resource resource = userExt.getResourcesForMap().get(ResourceServerTypeEnum.Diamond);
            int has = resource.getCount().intValue();
            if (consume > has) {
                throw new CoreException(String.format("玩家 %d立即完成升级技能消费不足,consume:%d ,当前拥有:%d\n", researchBean.getUserRaceId(), consume, has));
            }
            //扣钱
            int left = has - consume;
            resource.setCount(left);
            resourceCommonService.updateResource(resource);

            skillUpgradeComplete(researchBean.getUserRaceId(), researchBean.getCid());
        }
    }
}
