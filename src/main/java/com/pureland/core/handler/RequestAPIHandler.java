package com.pureland.core.handler;

import org.apache.commons.lang3.StringUtils;

import com.pureland.common.component.cache.api.RString;
import com.pureland.common.component.cache.error.RedisException;
import com.pureland.common.db.data.Machine;
import com.pureland.common.db.data.UserRace;
import com.pureland.common.enums.Entity;
import com.pureland.common.error.CoreException;
import com.pureland.common.error.InFightException;
import com.pureland.common.error.InOffLineException;
import com.pureland.common.protocal.ReqWrapperProtocal.ReqWrapper;
import com.pureland.common.protocal.RespWrapperProtocal.RespWrapper;
import com.pureland.common.service.UserRaceCommonService;
import com.pureland.common.service.impl.UserRaceCommonServiceImpl;
import com.pureland.common.util.DesUtil;
import com.pureland.common.util.GameUtil;
import com.pureland.common.util.SpringContextUtil;
import com.pureland.core.service.UserRaceService;
import com.pureland.core.service.impl.UserRaceServiceImpl;

public abstract class RequestAPIHandler {

	private UserRaceService userRaceService = (UserRaceService) SpringContextUtil
			.getBean(UserRaceServiceImpl.class.getSimpleName());
	private UserRaceCommonService userRaceCommonService = (UserRaceCommonService) SpringContextUtil
			.getBean(UserRaceCommonServiceImpl.class.getSimpleName());

	public RespWrapper handler0(ReqWrapper reqWrapper, String authToken, Long timestamp) throws CoreException {
		if (!isLoginReq()) {
			Long userId = authUser(authToken);
			Long userRaceId = getLastUserRaceId(userId);
			// 先判断是否超时,如果超时,重新发送登录
			long now = System.currentTimeMillis();
			long lastOperateTime = userRaceCommonService.getLastOperateTime(userRaceId).longValue();
			if (now - lastOperateTime > GameUtil.OFFLINETIME) {
				throw new InOffLineException(userRaceId);
			}
			// 设置时间,让玩家上线,防止被打
			userRaceCommonService.updateLastOperateTime(userRaceId);
			// 再判断是否在战斗,如果在,重新登录
			if (userRaceCommonService.isUserRaceInFight(userRaceId)) {
				throw new InFightException(userRaceId);
			}
		}
		return handler(reqWrapper, authToken, timestamp);
	}

	public abstract RespWrapper handler(ReqWrapper reqWrapper, String authToken, Long timestamp) throws CoreException;

	protected Long authUser(String authToken) throws CoreException {
		Long userId = null;
		String machineId = DesUtil.decrypt(authToken);
		String keyId = Machine.generatorIdKey(String.valueOf(machineId));
		try {
			String id = RString.get(keyId);
			if (StringUtils.isNotEmpty(id)) {
				String keyUserId = Machine.generatorFieldKey(Long.parseLong(id), Entity.Machine.USERID.getName());
				String uid = RString.get(keyUserId);
				if (StringUtils.isNotEmpty(uid)) {
					userId = Long.parseLong(uid);
				}
			}
		} catch (RedisException e) {
			throw new CoreException(e.getMessage());
		}

		if (userId == null) {
			throw new CoreException("auth user failed, " + authToken);
		}
		return userId;
	}

	protected Long getLastUserRaceId(Long userId) throws CoreException {
		UserRace lastUserRace = userRaceService.getLastUserRace(userId);
		Long userRaceId = lastUserRace.getId();
		return userRaceId;
	}

	protected boolean isLoginReq() {
		return false;
	}
}
