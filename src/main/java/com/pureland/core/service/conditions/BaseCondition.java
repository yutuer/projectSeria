package com.pureland.core.service.conditions;

import com.pureland.common.db.data.UserRace;
import com.pureland.common.db.statics.EntityModel;
import com.pureland.common.error.CoreException;
import com.pureland.common.service.UserRaceCommonService;
import com.pureland.common.service.WorkerQueueCommonService;
import com.pureland.common.service.impl.UserRaceCommonServiceImpl;
import com.pureland.common.service.impl.WorkerQueueCommonServiceImpl;
import com.pureland.common.util.SpringContextUtil;
import com.pureland.core.init.EntityModelHelper;

/**
 * 
 * @author qinpeirong
 *
 */
public abstract class BaseCondition {

	protected UserRaceCommonService userRaceCommonService = (UserRaceCommonService) SpringContextUtil.getBean(UserRaceCommonServiceImpl.class.getSimpleName());
	protected WorkerQueueCommonService queueService = (WorkerQueueCommonService) SpringContextUtil.getBean(WorkerQueueCommonServiceImpl.class.getSimpleName());
	
	public void init(Long userRaceId, Integer cid) throws CoreException {

	}
}
