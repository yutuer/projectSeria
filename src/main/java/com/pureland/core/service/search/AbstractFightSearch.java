package com.pureland.core.service.search;

import com.pureland.common.service.UserRaceCommonService;
import com.pureland.common.service.impl.UserRaceCommonServiceImpl;
import com.pureland.common.util.SpringContextUtil;

/**
 * Created by qinpeirong on 15-1-16.
 */
public abstract class AbstractFightSearch {

    protected UserRaceCommonService userRaceCommonService = (UserRaceCommonService) SpringContextUtil.getBean(UserRaceCommonServiceImpl.class.getSimpleName());
}
