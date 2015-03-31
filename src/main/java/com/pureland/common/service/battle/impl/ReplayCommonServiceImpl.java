package com.pureland.common.service.battle.impl;

import com.pureland.common.db.dao.redis.battle.ReplayDAO;
import com.pureland.common.db.data.battle.Replay;
import com.pureland.common.db.error.DBException;
import com.pureland.common.error.CoreException;
import com.pureland.common.service.BaseService;
import com.pureland.common.service.battle.ReplayCommonService;
import com.pureland.common.util.SpringContextUtil;
/**
 * 
 * @author qinpeirong
 *
 */
public class ReplayCommonServiceImpl extends BaseService implements
		ReplayCommonService {
	private ReplayDAO replayDAO = (ReplayDAO) SpringContextUtil.getBean(ReplayDAO.class.getSimpleName());

	@Override
	public Long addRelay(Replay replay) throws CoreException {
		try {
			return replayDAO.addRelay(replay);
		} catch (DBException e) {
			throw new CoreException(e);
		}
	}

	@Override
	public Replay getReplay(Long id) throws CoreException {
		try {
			return replayDAO.getReplay(id);
		} catch (DBException e) {
			throw new CoreException(e);
		}
	}

}
