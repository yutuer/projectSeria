package com.pureland.common.service;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.pureland.common.error.CoreException;
import com.pureland.common.log.PurelandLog;
import com.pureland.common.util.SpringContextUtil;

public abstract class BaseService {

	protected ThreadPoolTaskExecutor taskExecutor = (ThreadPoolTaskExecutor) SpringContextUtil.getBean("taskExecutor");

	protected void error(Exception e) throws CoreException {
		PurelandLog.error(e.getMessage());
		throw new CoreException(e);
	}
}
