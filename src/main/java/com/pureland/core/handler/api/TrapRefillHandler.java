package com.pureland.core.handler.api;

import com.pureland.common.db.data.UserRace;
import com.pureland.common.enums.RefillServerType;
import com.pureland.common.enums.SearchServerType;
import com.pureland.common.error.CoreException;
import com.pureland.common.log.PurelandLog;
import com.pureland.common.protocal.CampRespProtocal;
import com.pureland.common.protocal.FightSearchReqProtocal.FightSearchReq;
import com.pureland.common.protocal.FightSearchReqProtocal.FightSearchReq.SearchType;
import com.pureland.common.protocal.ReqWrapperProtocal.ReqWrapper;
import com.pureland.common.protocal.RespWrapperProtocal.RespWrapper;
import com.pureland.common.protocal.TrapRefillReqProtocal;
import com.pureland.common.service.CampCommonService;
import com.pureland.common.service.impl.CampCommonServiceImpl;
import com.pureland.common.util.SpringContextUtil;
import com.pureland.core.handler.RequestAPIHandler;
import com.pureland.core.service.BuildingService;
import com.pureland.core.service.UserRaceService;
import com.pureland.core.service.impl.BuildingServiceImpl;
import com.pureland.core.service.impl.UserRaceServiceImpl;
import com.pureland.core.service.search.FightSearchMap;
import com.pureland.core.service.search.FightSearchService;

/**
 * Created by qinpeirong on 15-1-16.
 */
public class TrapRefillHandler extends RequestAPIHandler {

    private BuildingService buildingService = (BuildingService) SpringContextUtil.getBean(BuildingServiceImpl.class.getSimpleName());

    @Override
    public RespWrapper handler(ReqWrapper reqWrapper, String authToken, Long timestamp) throws CoreException {
        Long userId = authUser(authToken);
        Long userRaceId = getLastUserRaceId(userId);
        TrapRefillReqProtocal.TrapRefillReq trapRefillReq = reqWrapper.getTrapRefillReq();
        TrapRefillReqProtocal.TrapRefillReq.RefillType refillType = trapRefillReq.getRefillType();
        RefillServerType refillServerType = RefillServerType.getRefillServerTypeById(refillType.getNumber());
        Long sid = trapRefillReq.getSid();
        buildingService.refillTrap(userRaceId, sid, refillServerType);
        RespWrapper respWrapper = RespWrapper.newBuilder().build();
        PurelandLog.info(reqWrapper.toString());
        return respWrapper;
    }

}
