package com.pureland.core.service.product;

import com.pureland.common.db.data.Building;
import com.pureland.common.db.data.Product;
import com.pureland.common.db.statics.EntityModel;
import com.pureland.common.enums.SubServerTypeEnum;
import com.pureland.common.enums.EntityServerTypeEnum;
import com.pureland.common.error.CoreException;
import com.pureland.common.service.bean.ProductBean;
import com.pureland.common.util.GameUtil;
import com.pureland.core.init.EntityModelHelper;

/**
 * @author qinpeirong
 */
public class ProductCompleteServiceImpl extends ProductServiceImpl {

    @Override
    public void product0(ProductBean productBean, Building building) throws CoreException {
        Long userRaceId = productBean.getUserRaceId();
        Long buildingSid = productBean.getBuildingSid();
        Integer cid = productBean.getCid();
        Long timestamp = productBean.getTimestamp();
        Integer amount = productBean.getAmount();
        Long endTime = productBean.getEndTime();

        // 军营如果满了,直接报错
        int barrackMaxSpace = buildingCommonService.getSameTypeBuildingsLeftSpace(userRaceId, SubServerTypeEnum.Barracks);
        int armySpace = armyCommonService.getArmySpace(userRaceId);
        int leftSpace = barrackMaxSpace - armySpace;

        if (leftSpace <= 0) {
            throw new CoreException(String.format("兵营已满\n"));
        }

        //校验当前建筑队列中的第一个product是否和cid相符
        String firstProductId = super.getFirstQueue(userRaceId, buildingSid);
        if (firstProductId == null) {
            throw new CoreException(String.format("当前建筑没有生产队列\n"));
        }
        Product firstProduct = super.getProduct(firstProductId);
        if (firstProduct == null) {
            throw new CoreException("No product, userRaceId=" + userRaceId + ", buildingSid＝" + buildingSid + ", cid=" + cid);
        }
        if (firstProduct.getCid().intValue() != cid.intValue()) {
            throw new CoreException(String.format("传入的cid不符合,current:%d, in's cid:%d\n", firstProduct.getCid().intValue(), cid.intValue()));
        }

        EntityModel entityModel = EntityModelHelper.ENTITIES.get(firstProduct.getCid());
        int leftAmount = firstProduct.getAmount() - 1;
        firstProduct.setAmount(leftAmount);
        //保存数量
        updateProduct(firstProduct);
        if (leftAmount <= 0) {
            // 要出队
            deleteQuene(userRaceId, buildingSid, firstProduct.getCid(), Long.valueOf(firstProduct.getId()));
        }
        if (entityModel.getEntityType().equals(EntityServerTypeEnum.Skill.name())) {
            skillService.trainingSkill(userRaceId, firstProduct.getCid(), 1);
        } else {
            armyService.trainingArmy(userRaceId, firstProduct.getCid(), 1);
        }

        //更新下一个队列的时间
        String secondProductId = getFirstQueue(userRaceId, buildingSid);
        if (secondProductId != null) {
            Product secondProduct = getProduct(secondProductId);
            EntityModel em = EntityModelHelper.ENTITIES.get(secondProduct.getCid());
            long beginTime = System.currentTimeMillis();
            if (GameUtil.IsTimeWrong(em.getTrainTime() * 1000L + beginTime, endTime)) {
                throw new CoreException("时间不正确, nextEndTime:%d, em.getTrainTime() + beginTime:%d\n", endTime, em.getTrainTime() + beginTime);
            }
            secondProduct.setNextEndTime(endTime);
            updateProduct(secondProduct);
        } else {
            if (GameUtil.IsTimeWrong(0, endTime)) {
                throw new CoreException("时间不正确, nextEndTime:%d, em.getTrainTime() + beginTime:%d\n", endTime, 0);
            }
        }
    }
}
