package com.pureland.core.service.search.impl;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;

import com.google.common.collect.Lists;
import com.pureland.common.db.data.UserRace;
import com.pureland.common.error.CoreException;
import com.pureland.common.log.PurelandLog;
import com.pureland.common.service.UserRaceCommonService;
import com.pureland.common.service.impl.UserRaceCommonServiceImpl;
import com.pureland.common.util.SpringContextUtil;
import com.pureland.core.service.search.AbstractFightSearch;
import com.pureland.core.service.search.FightSearchService;

public class PVPHighServiceImpl extends AbstractFightSearch implements FightSearchService {
	private String TAG = PurelandLog.getClassTag(PVPHighServiceImpl.class);
	private UserRaceCommonService userRaceCommonService = (UserRaceCommonService) SpringContextUtil.getBean(UserRaceCommonServiceImpl.class.getSimpleName());

	@Override
	public Long fightSearch(Long userId, Long userRaceId) throws CoreException {
		// 获取所有玩家id(不能好友角色, 不能同工会角色, 不能自己的同一个用户下的账号)
		Set<String> userRaceIdSets = userRaceCommonService.getAllOfflineUserRaceIds();
		if (CollectionUtils.isNotEmpty(userRaceIdSets)) {
			List<String> userRaceIds = Lists.newArrayList(userRaceIdSets);
			Collections.shuffle(userRaceIds);
			for (String urId : userRaceIds) {
				Long urIdLong = Long.valueOf(urId);
				UserRace ur = userRaceCommonService.getUserRace(urIdLong);
				// userId不相等 并且不在战斗中
				if (ur.getUserId().longValue() != userId.longValue() && !userRaceCommonService.isUserRaceInFight(ur.getId())) {
					// 更改上个对手的状态
					Long opponentId = userRaceCommonService.getUserRaceFightOpponent(userRaceId);
					if (opponentId != null) {
						userRaceCommonService.updateUserRaceFightStatus(opponentId, false);
					}

					// 设置对方进入战斗
					userRaceCommonService.updateUserRaceFightStatus(ur.getId(), true);
					userRaceCommonService.updateUserRaceFightOpponent(userRaceId, ur.getId());
					return urIdLong;
				}
			}
		}
		return null;
	}
}
