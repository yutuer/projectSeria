package com.pureland.core.handler.api;

import org.apache.commons.lang3.ArrayUtils;

import com.google.protobuf.InvalidProtocolBufferException;
import com.pureland.common.db.data.battle.Replay;
import com.pureland.common.error.CoreException;
import com.pureland.common.log.PurelandLog;
import com.pureland.common.protocal.BattleReplayReqProtocal.BattleReplayReq;
import com.pureland.common.protocal.BattleReplayRespProtocal;
import com.pureland.common.protocal.BattleReplayRespProtocal.BattleReplayResp;
import com.pureland.common.protocal.ReqWrapperProtocal.ReqWrapper;
import com.pureland.common.protocal.RespWrapperProtocal;
import com.pureland.common.protocal.RespWrapperProtocal.RespWrapper;
import com.pureland.common.protocal.vo.BattleReplayVOProtocal;
import com.pureland.common.protocal.vo.BattleReplayVOProtocal.BattleReplayVO;
import com.pureland.common.util.SpringContextUtil;
import com.pureland.core.handler.RequestAPIHandler;
import com.pureland.core.service.battle.ReplayService;
import com.pureland.core.service.battle.impl.ReplayServiceImpl;
/**
 * 
 * @author qinpeirong
 *
 */
public class BattleReplayHandler extends RequestAPIHandler {
	private ReplayService replayService = (ReplayService) SpringContextUtil.getBean(ReplayServiceImpl.class.getSimpleName());

	@Override
	public RespWrapper handler(ReqWrapper reqWrapper, String authToken,
			Long timestamp) throws CoreException {
		authUser(authToken);
		BattleReplayReq battleReplayReq = reqWrapper.getBattleReplayReq();
		Long replayId = battleReplayReq.getReplayId();
		Replay replay = replayService.getReplay(replayId);
		BattleReplayVO battleReplayVO = buildBattleReplayVO(replay);
		BattleReplayResp battleReplayResp = BattleReplayRespProtocal.BattleReplayResp.newBuilder().setBattleReplayVO(battleReplayVO).build();
		RespWrapper respWrapper = RespWrapperProtocal.RespWrapper.newBuilder().setBattleReplayResp(battleReplayResp).build();
		PurelandLog.info(reqWrapper.toString());
		return respWrapper;
	}
	
	private BattleReplayVO buildBattleReplayVO(Replay replay) throws CoreException {
		byte[] relay = replay.getReplayByte();
		try {
			BattleReplayVO battleReplayVO = null;
			if(ArrayUtils.isNotEmpty(relay)) {
				battleReplayVO = BattleReplayVO.parseFrom(relay);
			} else {
				battleReplayVO = BattleReplayVOProtocal.BattleReplayVO.newBuilder().build();
			}
			return battleReplayVO;
		} catch (InvalidProtocolBufferException e) {
			throw new CoreException(e);
		}
	}

}
