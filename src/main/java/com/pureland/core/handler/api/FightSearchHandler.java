package com.pureland.core.handler.api;

import com.pureland.common.db.data.UserRace;
import com.pureland.common.enums.SearchServerType;
import com.pureland.common.error.CoreException;
import com.pureland.common.log.PurelandLog;
import com.pureland.common.protocal.CampRespProtocal;
import com.pureland.common.protocal.FightSearchReqProtocal.FightSearchReq;
import com.pureland.common.protocal.FightSearchReqProtocal.FightSearchReq.SearchType;
import com.pureland.common.protocal.ReqWrapperProtocal.ReqWrapper;
import com.pureland.common.protocal.RespWrapperProtocal;
import com.pureland.common.protocal.RespWrapperProtocal.RespWrapper;
import com.pureland.common.service.CampCommonService;
import com.pureland.common.service.UserRaceCommonService;
import com.pureland.common.service.impl.CampCommonServiceImpl;
import com.pureland.common.service.impl.UserRaceCommonServiceImpl;
import com.pureland.common.util.SpringContextUtil;
import com.pureland.core.handler.RequestAPIHandler;
import com.pureland.core.service.UserRaceService;
import com.pureland.core.service.impl.UserRaceServiceImpl;
import com.pureland.core.service.search.FightSearchMap;
import com.pureland.core.service.search.FightSearchService;

/**
 * Created by qinpeirong on 15-1-16.
 */
public class FightSearchHandler extends RequestAPIHandler {
    private FightSearchMap fightSearchMap = (FightSearchMap) SpringContextUtil.getBean(FightSearchMap.class.getSimpleName());
    private UserRaceService userRaceService = (UserRaceService) SpringContextUtil.getBean(UserRaceServiceImpl.class.getSimpleName());
    private CampCommonService campCommonService = (CampCommonService) SpringContextUtil.getBean(CampCommonServiceImpl.class.getSimpleName());

    @Override
    public RespWrapper handler(ReqWrapper reqWrapper, String authToken, Long timestamp) throws CoreException {
        Long userId = authUser(authToken);
        Long userRaceId = getLastUserRaceId(userId);
        FightSearchReq fightSearchReq = reqWrapper.getFightSearchReq();
        SearchType searchType = fightSearchReq.getSearchType();
        SearchServerType searchServerType = SearchServerType.getSearchServerType(searchType);
        FightSearchService fightSearchService = fightSearchMap.getFightSearchMap().get(searchServerType.getName());
        Long playerId = fightSearchService.fightSearch(userId, userRaceId);

        if (playerId == null) {
            throw new CoreException("fight search failed, " + userRaceId);
        }
        UserRace userRace = userRaceService.getUserRace(playerId);
        CampRespProtocal.CampResp campResp = campCommonService.buildCampResp(userRace);
        RespWrapper respWrapper = RespWrapperProtocal.RespWrapper.newBuilder().setCampResp(campResp).build();
        PurelandLog.info(reqWrapper.toString());
        return respWrapper;
    }

}
