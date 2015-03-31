package com.pureland.core.service.product;

import com.pureland.common.db.data.Building;
import com.pureland.common.db.data.Product;
import com.pureland.common.db.statics.EntityModel;
import com.pureland.common.enums.ResourceServerTypeEnum;
import com.pureland.common.error.CoreException;
import com.pureland.common.service.bean.ProductBean;
import com.pureland.common.service.bean.ResourceCostBean;
import com.pureland.core.init.EntityModelHelper;

/**
 * @author qinpeirong
 */
public class ProductRemoveServiceImpl extends ProductServiceImpl {

    @Override
    public void product0(ProductBean productBean, Building building) throws CoreException {
        Long userRaceId = productBean.getUserRaceId();
        Long buildingSid = productBean.getBuildingSid();
        Integer cid = productBean.getCid();
        Long timestamp = productBean.getTimestamp();
        Integer amount = productBean.getAmount();
        Long endTime = productBean.getEndTime();

        Product product = super.getProduct(userRaceId, buildingSid, cid);
        if (product == null) {
            throw new CoreException("No product, userRaceId=" + userRaceId + ", buildingSid＝" + buildingSid + ", cid=" + cid);
        }
        //判断数量是否满足
        Integer amount2 = product.getAmount() - amount;
        if (amount2 < 0) {
            throw new CoreException(String.format("队列中排队的数量,cur:%d , in:%d", product.getAmount(), amount));
        }
        Long id = product.getId();
        product.setAmount(amount2);
        //这里不需要更改nextTime
        if (amount2 <= 0) {
            deleteQuene(userRaceId, buildingSid, cid, id);
            //更新第一个队列的时间
            super.updateFirstQueueNextTime(userRaceId, buildingSid, endTime);
        } else {
            super.updateProduct(product);
        }

        //给玩家加回钱
        EntityModel productEm = EntityModelHelper.ENTITIES.get(cid);
        String costType = ResourceServerTypeEnum.getResourceServerTypeEnumByName(productEm.getTrainCostResourceType()).name();
        userRaceCommonService.resourceAdd(new ResourceCostBean(userRaceId, costType, productEm.getTrainCostResourceCount().intValue()));
    }
}
