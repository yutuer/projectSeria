package com.pureland.core.handler.api;

import com.pureland.common.db.data.UserRace;
import com.pureland.common.error.CoreException;
import com.pureland.common.log.PurelandLog;
import com.pureland.common.protocal.PlayerListRespProtocal;
import com.pureland.common.protocal.ReqWrapperProtocal.ReqWrapper;
import com.pureland.common.protocal.RespWrapperProtocal.RespWrapper;
import com.pureland.common.protocal.vo.PlayerLoginSimpleVOProtocal;
import com.pureland.common.util.SpringContextUtil;
import com.pureland.core.handler.RequestAPIHandler;
import com.pureland.core.service.UserRaceService;
import com.pureland.core.service.impl.UserRaceServiceImpl;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * Created by qinpeirong on 15-1-15.
 */
public class PlayerRoleHandler extends RequestAPIHandler {
    private UserRaceService userRaceService = (UserRaceService) SpringContextUtil.getBean(UserRaceServiceImpl.class.getSimpleName());

    @Override
    public RespWrapper handler(ReqWrapper reqWrapper, String authToken, Long timestamp) throws CoreException {
        Long userId = authUser(authToken);
        List<UserRace> userRaces = userRaceService.getUserRaces(userId, null);

        RespWrapper.Builder builder = RespWrapper.newBuilder();
        if (CollectionUtils.isNotEmpty(userRaces)) {
            PlayerListRespProtocal.PlayerListResp playerListResp = buildPlayerListResp(userRaces);
            builder.setPlayerListResp(playerListResp);
        }
        RespWrapper respWrapper = builder.build();
        PurelandLog.info(reqWrapper.toString());
        return respWrapper;
    }

    private PlayerListRespProtocal.PlayerListResp buildPlayerListResp(List<UserRace> userRaces) {
        PlayerListRespProtocal.PlayerListResp.Builder builder = PlayerListRespProtocal.PlayerListResp.newBuilder();
        for (UserRace userRace : userRaces) {
            PlayerLoginSimpleVOProtocal.PlayerLoginSimpleVO playerLoginSimpleVO = buildPlayerLoginSimpleVO(userRace);
            builder.addLoginSimpleVOs(playerLoginSimpleVO);
        }
        return builder.build();
    }

    private PlayerLoginSimpleVOProtocal.PlayerLoginSimpleVO buildPlayerLoginSimpleVO(UserRace userRace) {
        Long id = userRace.getId();
        Integer raceId = userRace.getRaceId();
        String nickName = userRace.getNickName();
        nickName = StringUtils.isEmpty(nickName) ? "" : nickName;
        Integer level = userRace.getUserExt().getLevel();

        PlayerLoginSimpleVOProtocal.PlayerLoginSimpleVO playerLoginSimpleVO = PlayerLoginSimpleVOProtocal.PlayerLoginSimpleVO.newBuilder()
                .setUserId(id)
                .setRaceType(Integer.parseInt(raceId.toString()))
                .setUserName(nickName)
                .setUserLevel(Integer.parseInt(level.toString()))
                .build();
        return playerLoginSimpleVO;
    }
}
