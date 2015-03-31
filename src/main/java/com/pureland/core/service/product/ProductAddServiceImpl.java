package com.pureland.core.service.product;

import com.pureland.common.db.data.*;
import com.pureland.common.db.statics.EntityModel;
import com.pureland.common.enums.EntityServerTypeEnum;
import com.pureland.common.enums.ResourceServerTypeEnum;
import com.pureland.common.error.CoreException;
import com.pureland.common.service.bean.ProductBean;
import com.pureland.common.util.GameUtil;
import com.pureland.core.init.EntityModelHelper;

import org.apache.commons.collections.CollectionUtils;

import java.util.List;

/**
 * @author qinpeirong
 */
public class ProductAddServiceImpl extends ProductServiceImpl {

    @Override
    public void product0(ProductBean productBean, Building building) throws CoreException {
        Long userRaceId = productBean.getUserRaceId();
        Long buildingSid = productBean.getBuildingSid();
        Integer cid = productBean.getCid();
        Long timestamp = productBean.getTimestamp();
        Integer amount = productBean.getAmount();
        Long endTime = productBean.getEndTime();

        //TODO 校验发送时间

        //解锁条件校验 cid是否满足buildNeed条件
        EntityModel buildingEm = EntityModelHelper.ENTITIES.get(building.getCid());
        EntityModel productEm = EntityModelHelper.ENTITIES.get(cid);
        if (!productEm.getBuildNeedType().equals(buildingEm.getSubType())) {
            throw new CoreException("类型不匹配");
        }
        if (buildingEm.getLevel().intValue() < productEm.getBuildNeedLevel().intValue()) {
            throw new CoreException(String.format("等级条件不满足 , level:%d ,needLevel:%d\n", buildingEm, productEm.getBuildNeedLevel().intValue()));
        }

        //验证资源是否足够
        UserExt userExt = userExtCommonService.getUserExt(userRaceId);
        ResourceServerTypeEnum resourceServerType = ResourceServerTypeEnum.getResourceServerTypeEnumByName(productEm.getTrainCostResourceType());
        Resource resource = userExt.getResourcesForMap().get(resourceServerType);
        int resourceNeededCount = productEm.getTrainCostResourceCount().intValue() * amount;
        if (resource.getCount().intValue() < resourceNeededCount) {
            throw new CoreException(String.format("资源不足,  currentReourceCount: %d, resourceNeededCount: %d", resource.getCount().intValue(), resourceNeededCount));
        }

        //判断空间是否足够
        int needSpace = amount * productEm.getSpaceUse();
        // 计算队列大小 ,技能和军队不一样, 技能需要加上已经产出的技能
        int nowSpace = 0;
        if (productEm.getEntityType().equals(EntityServerTypeEnum.Skill.name())) {
            needSpace = 1;
            List<Skill> skillList = skillCommonService.getSkills(userRaceId);
            for (Skill skill : skillList) {
                nowSpace += skill.getAmount();
            }
        }
        nowSpace += getArmyBuildingsCurSpace(userRaceId, buildingSid);
        int maxSpace = buildingEm.getQueueSize();
        if (needSpace + nowSpace > maxSpace) {
            throw new CoreException(String.format("空间不足,  needSpace: %d, nowSpace: %d, maxSpace:%d", needSpace, nowSpace, maxSpace));
        }

        long beginTime = timestamp;

        List<Product> productList = super.getProducts(userRaceId, buildingSid);
        if (CollectionUtils.isEmpty(productList)) {
            //只有生产队列为空的时候才需要校验时间
            long now = System.currentTimeMillis();
            if (GameUtil.IsTimeWrong(beginTime, now)) {
                throw new CoreException(String.format("time is wrong, beginTime: %d,currentTime: %d, productEm.getTrainTime():%d s\n", beginTime, now, productEm.getTrainTime()));
            }
        }

        //扣掉资源
        resource.setCount(resource.getCount().intValue() - resourceNeededCount);
        resourceCommonService.updateResource(resource);

        //添加产品
        Product product = super.getProduct(userRaceId, buildingSid, cid);
        if (product == null) {
            super.addProduct(userRaceId, buildingSid, cid, amount, beginTime, endTime);
        } else {
            // 如果这个消息的时间小于product中的时间, 则说明是先发的包后到,则需要更新product的开始时间,以便重新排序
            if (beginTime < product.getBeginTime().longValue()) {
                product.setBeginTime(beginTime);
            }
            Integer amount2 = product.getAmount() + amount;
            product.setAmount(amount2);
            super.updateProduct(product);
        }
    }
}
