package com.pureland.core.service.impl;

import com.pureland.common.enums.ResearchRequestServerType;
import com.pureland.common.error.CoreException;
import com.pureland.core.handler.ResearchLogicHandler;
import com.pureland.core.service.ResearchService;
import com.pureland.common.service.bean.ResearchBean;

import java.util.Map;

/**
 * Created by Administrator on 2015/1/30.
 */
public class ResearchServiceImpl implements ResearchService {

    private Map<String, ResearchLogicHandler> handlerMap;

    @Override
    public void research(Long userRaceId, ResearchRequestServerType researchRequestType, int cid, long currentTime, int diamondCount) throws CoreException {
        ResearchLogicHandler researchLogicHandler = handlerMap.get(researchRequestType.getEnumName());
        researchLogicHandler.research(new ResearchBean(userRaceId, cid, currentTime, diamondCount));
    }

    public void setHandlerMap(Map<String, ResearchLogicHandler> handlerMap) {
        this.handlerMap = handlerMap;
    }
}
