package com.pureland.core.handler.api;

import com.pureland.common.component.cache.error.RedisException;
import com.pureland.common.db.data.Building;
import com.pureland.common.db.data.clan.Clan;
import com.pureland.common.error.CoreException;
import com.pureland.common.protocal.LoginRespProtocal;
import com.pureland.common.protocal.LoginRespProtocal.LoginResp;
import com.pureland.common.protocal.NoAuthLoginReqProtocal.NoAuthLoginReq;
import com.pureland.common.protocal.ReqWrapperProtocal.ReqWrapper;
import com.pureland.common.protocal.RespWrapperProtocal;
import com.pureland.common.protocal.RespWrapperProtocal.RespWrapper;
import com.pureland.common.service.ClanCommonService;
import com.pureland.common.service.impl.ClanCommonServiceImpl;
import com.pureland.common.util.DesUtil;
import com.pureland.common.util.SpringContextUtil;
import com.pureland.core.handler.RequestAPIHandler;
import com.pureland.core.service.UserService;
import com.pureland.core.service.impl.UserServiceImpl;

public class NoAuthLoginHandler extends RequestAPIHandler {

	private UserService userService = (UserService) SpringContextUtil.getBean(UserServiceImpl.class.getSimpleName());
	private ClanCommonService clanCommonService = (ClanCommonService) SpringContextUtil.getBean(ClanCommonServiceImpl.class.getSimpleName());

	@Override
	public RespWrapper handler(ReqWrapper reqWrapper, String authToken, Long timestamp) throws CoreException {
		NoAuthLoginReq noAuthLoginReq = reqWrapper.getNoAuthLoginReq();
		String machineId = noAuthLoginReq.getMachineId();
		Integer raceId = noAuthLoginReq.getRaceType();
		String userName = noAuthLoginReq.getUserName();
		Long userRaceId = userService.login(machineId, raceId != null ? raceId : null, userName);

		Long clanId = null;
		Clan clan = clanCommonService.getClanInfoByUserRaceId(userRaceId);
		if (clan != null) {
			clanId = clan.getClanBase().getClanId();
		}
		String token = DesUtil.encrypt(machineId);
		RespWrapper respWrapper = null;
		try {
			LoginRespProtocal.LoginResp.Builder loginRespBuilder = LoginRespProtocal.LoginResp.newBuilder();
			if (clanId != null) {
				loginRespBuilder.setClanId(clanId);
			}
			LoginResp laLoginResp = loginRespBuilder.setAuthToken(token).setUserId(userRaceId).setCurrentTime(System.currentTimeMillis())
					.setNextItemSid(Building.getIdValueWithUserRace(userRaceId)).build();
			respWrapper = RespWrapperProtocal.RespWrapper.newBuilder().setLoginResp(laLoginResp).build();

		} catch (RedisException e) {
			throw new CoreException(e.getMessage());
		}
		return respWrapper;
	}

	@Override
	protected boolean isLoginReq() {
		return true;
	}
}
