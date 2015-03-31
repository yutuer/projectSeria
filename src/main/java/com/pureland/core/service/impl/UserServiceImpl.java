package com.pureland.core.service.impl;

import com.pureland.common.component.cache.api.RString;
import com.pureland.common.component.cache.error.RedisException;
import com.pureland.common.db.data.User;
import com.pureland.common.db.data.UserRace;
import com.pureland.common.enums.Entity;
import com.pureland.common.enums.RaceServerTypeEnum;
import com.pureland.common.error.CoreException;
import com.pureland.common.error.InFightException;
import com.pureland.common.service.MachineCommonService;
import com.pureland.common.service.UserRaceCommonService;
import com.pureland.common.service.impl.MachineCommonServiceImpl;
import com.pureland.common.service.impl.UserCommonServiceImpl;
import com.pureland.common.service.impl.UserRaceCommonServiceImpl;
import com.pureland.common.util.SpringContextUtil;
import com.pureland.core.handler.UserRaceHandler;
import com.pureland.core.service.UserRaceService;
import com.pureland.core.service.UserService;

import java.util.Map;

/**
 * @author qinpeirong
 */
public class UserServiceImpl extends UserCommonServiceImpl implements UserService {

    private UserRaceService userRaceService = (UserRaceService) SpringContextUtil.getBean(UserRaceServiceImpl.class.getSimpleName());
    private MachineCommonService machineCommonService = (MachineCommonService) SpringContextUtil.getBean(MachineCommonServiceImpl.class.getSimpleName());
    private Map<String, UserRaceHandler> handlerMap;
    private UserRaceCommonService userRaceCommonService = (UserRaceCommonService) SpringContextUtil.getBean(UserRaceCommonServiceImpl.class.getSimpleName());

    @Override
    public Long login(String machineId, Integer raceId, String nickName) throws CoreException {
        Long userId = machineCommonService.getUserIdByMachineId(machineId);
        if (userId == null) {
            return register(machineId, raceId, nickName);
        }
        Long lastUserRaceId = getLastUserRaceId(userId);
        userRaceCommonService.updateLastOperateTime(lastUserRaceId);
        //再判断是否在战斗,如果在,重新登录
        if (userRaceCommonService.isUserRaceInFight(lastUserRaceId)) {
            throw new InFightException(lastUserRaceId);
        }
        if (lastUserRaceId != null) {
            userRaceService.updateLastLoginTime(lastUserRaceId);
        }
        return lastUserRaceId;
    }

    @Override
    public Long register(String machineId, Integer raceId, String nickName)
            throws CoreException {
        if (raceId == null || RaceServerTypeEnum.getRaceServerTypeEnumById(raceId) == null)
            throw new CoreException("raceId is invalid, can't be null and must be from one to five");

        Long userId = addQuickUser(machineId);
        machineCommonService.addMachine(machineId, userId);
        Long userRaceId = userRaceService.addUserRace(userId, raceId, nickName);
        RaceServerTypeEnum race = RaceServerTypeEnum.getRaceServerTypeEnumById(raceId);
        UserRaceHandler userRaceHandler = handlerMap.get(race.name());
        userRaceHandler.initCamp(userRaceId, Integer.parseInt(raceId.toString()));
        userRaceCommonService.updateLastOperateTime(userRaceId);
        return userRaceId;
    }

    @Override
    public Long authLogin(final String machineId, String account, String passwd)
            throws CoreException {
        User user = getUser(account, passwd);
        if (user == null) {
            throw new CoreException("not exits user, account=" + account);
        }

        Long userId = user.getId();
        machineCommonService.updateMachine(machineId, userId);
        return userId;
    }


    @Override
    public void bindAcccount(Long userId, String account, String passwd, String telephone) throws CoreException {
        Boolean exists = existsAccount(userId);
        if (exists) {
            throw new CoreException("account is exist, " + account);
        }
        User user = new User();
        user.setId(userId);
        user.setAccount(account);
        user.setPasswd(passwd);
        user.setTelephone(telephone);
        updateUser(user);
    }

    @Override
    public Boolean existsAccount(Long userId) throws CoreException {
        String keyAccount = User.generatorFieldKey(userId, Entity.User.ACCOUNT.getName());
        try {
            return RString.exists(keyAccount);
        } catch (RedisException e) {
            throw new CoreException(e);
        }
    }

    private Long getLastUserRaceId(Long userId) throws CoreException {
        UserRace lastUserRace = userRaceService.getLastUserRace(userId);
        if (lastUserRace == null) {
            throw new CoreException("userId is not mapping race, userId=" + userId);
        }
        return lastUserRace.getId();
    }

    /**
     * @param handlerMap the handlerMap to set
     */
    public void setHandlerMap(Map<String, UserRaceHandler> handlerMap) {
        this.handlerMap = handlerMap;
    }


}
