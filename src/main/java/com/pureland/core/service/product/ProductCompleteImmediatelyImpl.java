package com.pureland.core.service.product;

import com.pureland.common.db.data.Building;
import com.pureland.common.db.data.Product;
import com.pureland.common.db.data.Resource;
import com.pureland.common.db.data.UserExt;
import com.pureland.common.db.statics.EntityModel;
import com.pureland.common.enums.SubServerTypeEnum;
import com.pureland.common.enums.EntityServerTypeEnum;
import com.pureland.common.enums.ResourceServerTypeEnum;
import com.pureland.common.error.CoreException;
import com.pureland.common.service.bean.ProductBean;
import com.pureland.common.util.GameUtil;
import com.pureland.core.init.EntityModelHelper;

/**
 * @author qinpeirong
 */
public class ProductCompleteImmediatelyImpl extends ProductServiceImpl {

    @Override
    public void product0(ProductBean productBean, Building building) throws CoreException {
        Long userRaceId = productBean.getUserRaceId();
        Long buildingSid = productBean.getBuildingSid();
        Integer cid = productBean.getCid();
        Long timestamp = productBean.getTimestamp();
        Integer amount = productBean.getAmount();
        Long endTime = productBean.getEndTime();
        Integer diamondCount = productBean.getDiamondCount();


        //得到当前队列立即完成需要的钻石数量
        int finishSeconds = super.getAllProductsFinishSecond(userRaceId, buildingSid, timestamp);
        // 判断消费够不够
        int consume = GameUtil.getConsumeByTime(finishSeconds);
        if (diamondCount != consume) {
            throw new CoreException("消费数量不相等 ,reqin: %d , server:%d \n", diamondCount, consume);
        }

        UserExt userExt = userExtCommonService.getUserExt(userRaceId);
        Resource resource = userExt.getResourcesForMap().get(ResourceServerTypeEnum.Diamond);
        if (consume > resource.getCount().intValue()) {
            throw new CoreException(String.format("资源不够 ,need: %d, has:%d", consume, resource.getCount().intValue()));
        }

        //计算军营剩余空间
        int barrackMaxSpace = buildingCommonService.getSameTypeBuildingsLeftSpace(userRaceId, SubServerTypeEnum.Barracks);
        int armySpace = armyCommonService.getArmySpace(userRaceId);
        int barrackLeftSpace = barrackMaxSpace - armySpace;

        //计算生产队列中空间
        int nowAllProductSpace = getArmyBuildingsCurSpace(userRaceId, buildingSid);
        if (barrackLeftSpace < nowAllProductSpace) {
            throw new CoreException(String.format("不能立即完成, barrackSpace:%d , nowSpace:%d\n", barrackLeftSpace, nowAllProductSpace));
        }

        //取出队列第一个productId
        String productId = null;
        while ((productId = super.getFirstQueue(userRaceId, buildingSid)) != null) {
            Product product = super.getProduct(productId);
            int cidd = product.getCid();
            deleteQuene(userRaceId, buildingSid, cidd, Long.valueOf(productId));
            EntityModel entityModel = EntityModelHelper.ENTITIES.get(cidd);
            if (entityModel.getEntityType().equals(EntityServerTypeEnum.Skill.name())) {
                skillService.trainingSkill(userRaceId, cidd, product.getAmount());
            } else {
                armyService.trainingArmy(userRaceId, cidd, product.getAmount());
            }
        }

        //扣掉钱
        resource.setCount(resource.getCount().intValue() - consume);
        resourceCommonService.updateResource(resource);
    }

}
