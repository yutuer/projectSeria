package com.pureland.core.handler.api;

import com.pureland.common.db.data.UserRace;
import com.pureland.common.error.CoreException;
import com.pureland.common.log.PurelandLog;
import com.pureland.common.protocal.AuthLoginReqProtocal.AuthLoginReq;
import com.pureland.common.protocal.PlayerListRespProtocal.PlayerListResp;
import com.pureland.common.protocal.ReqWrapperProtocal.ReqWrapper;
import com.pureland.common.protocal.RespWrapperProtocal.RespWrapper;
import com.pureland.common.protocal.vo.PlayerLoginSimpleVOProtocal;
import com.pureland.common.protocal.vo.PlayerLoginSimpleVOProtocal.PlayerLoginSimpleVO;
import com.pureland.common.util.SpringContextUtil;
import com.pureland.core.handler.RequestAPIHandler;
import com.pureland.core.service.UserRaceService;
import com.pureland.core.service.UserService;
import com.pureland.core.service.impl.UserRaceServiceImpl;
import com.pureland.core.service.impl.UserServiceImpl;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * Created by qinpeirong on 15-1-15.
 */
public class AuthLoginHandler extends RequestAPIHandler {

    private UserService userService = (UserService) SpringContextUtil.getBean(UserServiceImpl.class.getSimpleName());
    private UserRaceService userRaceService = (UserRaceService) SpringContextUtil.getBean(UserRaceServiceImpl.class.getSimpleName());

    @Override
    public RespWrapper handler(ReqWrapper reqWrapper, String authToken, Long timestamp) throws CoreException {
        AuthLoginReq authLoginReq = reqWrapper.getAuthLoginReq();
        String machineId = authLoginReq.getMachineId();
        String account = authLoginReq.getAccount();
        String passwd = authLoginReq.getPasswd();
        Long userId = userService.authLogin(machineId, account, passwd);
        List<UserRace> userRaces = userRaceService.getUserRaces(userId, null);

        RespWrapper.Builder builder = RespWrapper.newBuilder();
        if (CollectionUtils.isNotEmpty(userRaces)) {
            PlayerListResp playerListResp = buildPlayerListResp(userRaces);
            builder.setPlayerListResp(playerListResp);
        }
        RespWrapper respWrapper = builder.build();
        PurelandLog.info(reqWrapper.toString());
        return respWrapper;
    }

    private PlayerListResp buildPlayerListResp(List<UserRace> userRaces) {
        PlayerListResp.Builder builder = PlayerListResp.newBuilder();
        for (UserRace userRace : userRaces) {
            PlayerLoginSimpleVO playerLoginSimpleVO = buildPlayerLoginSimpleVO(userRace);
            builder.addLoginSimpleVOs(playerLoginSimpleVO);
        }
        return builder.build();
    }

    private PlayerLoginSimpleVO buildPlayerLoginSimpleVO(UserRace userRace) {
        Long id = userRace.getId();
        Integer raceId = userRace.getRaceId();
        String nickName = userRace.getNickName();
        nickName = StringUtils.isEmpty(nickName) ? "" : nickName;
        Integer level = userRace.getUserExt().getLevel();

        PlayerLoginSimpleVO playerLoginSimpleVO = PlayerLoginSimpleVOProtocal.PlayerLoginSimpleVO.newBuilder()
                .setUserId(id)
                .setRaceType(Integer.parseInt(raceId.toString()))
                .setUserName(nickName)
                .setUserLevel(level)
                .build();
        return playerLoginSimpleVO;
    }

    @Override
    protected boolean isLoginReq() {
        return true;
    }
}
