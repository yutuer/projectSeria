package com.pureland.core.handler.api;

import com.pureland.common.error.CoreException;
import com.pureland.common.log.PurelandLog;
import com.pureland.common.protocal.RespWrapperProtocal;
import com.pureland.common.protocal.ProductionReqProtocal.ProductionReq;
import com.pureland.common.protocal.ProductionReqProtocal.ProductionReq.ProductionRequestType;
import com.pureland.common.protocal.ReqWrapperProtocal.ReqWrapper;
import com.pureland.common.protocal.RespWrapperProtocal.RespWrapper;
import com.pureland.common.protocal.vo.ProductionItemVOProtocal.ProductionItemVO;
import com.pureland.common.util.SpringContextUtil;
import com.pureland.core.handler.RequestAPIHandler;
import com.pureland.core.service.ProductService;
import com.pureland.common.service.bean.ProductBean;
import com.pureland.core.service.product.ProductMap;

/**
 * @author qinpeirong
 */
public class ProductionReqHandler extends RequestAPIHandler {
    private ProductMap productMap = (ProductMap) SpringContextUtil.getBean(ProductMap.class.getSimpleName());

    @Override
    public RespWrapper handler(ReqWrapper reqWrapper, String authToken, Long timestamp)
            throws CoreException {
        Long userId = authUser(authToken);
        Long userRaceId = getLastUserRaceId(userId);
        ProductionReq productionReq = reqWrapper.getProductionReq();
        ProductionRequestType productionRequestType = productionReq.getProductionRequestType();
        Long buildingSid = productionReq.getSid();
        Long endTime = productionReq.getTime();
        Integer diamondCount = productionReq.getDiamondCount();
        ProductionItemVO productionItemVO = productionReq.getProductionItemVO();
        Integer cid = productionItemVO.getCid();
        Integer count = productionItemVO.getCount();
        ProductService productService = productMap.getProductMaps().get(productionRequestType.toString());
        ProductBean productBean = new ProductBean(userRaceId, buildingSid, cid, timestamp, count, endTime, diamondCount);
        productService.product(productBean);
        RespWrapper respWrapper = RespWrapperProtocal.RespWrapper.newBuilder().build();
        PurelandLog.info(reqWrapper.toString());
        return respWrapper;
    }

}
