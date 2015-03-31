package com.pureland.common.service.battle.impl;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.google.common.collect.Lists;
import com.pureland.common.db.dao.redis.battle.ArmyConsumeDAO;
import com.pureland.common.db.data.battle.ArmyConsume;
import com.pureland.common.db.error.DBException;
import com.pureland.common.enums.BattleType;
import com.pureland.common.error.CoreException;
import com.pureland.common.service.BaseService;
import com.pureland.common.service.battle.ArmyConsumeCommonService;
import com.pureland.common.util.SpringContextUtil;

public class ArmyConsumeCommonServiceImpl extends BaseService implements ArmyConsumeCommonService {
	private ArmyConsumeDAO armyConsumeDAO = (ArmyConsumeDAO) SpringContextUtil.getBean(ArmyConsumeDAO.class.getSimpleName());

	@Override
	public Long addArmyConsume(ArmyConsume armyConsume) throws CoreException {
		try {
			return armyConsumeDAO.addArmyConsume(armyConsume);
		} catch (DBException e) {
			throw new CoreException(e);
		}
	}

	@Override
	public void addBatchArmyConsume(List<ArmyConsume> armyConsumes)
			throws CoreException {
		for(ArmyConsume armyConsume : armyConsumes) {
			addArmyConsume(armyConsume);
		}
		
	}

	@Override
	public List<ArmyConsume> getArmyConsumes(Long userRaceId, Long battleId, BattleType battleType)
			throws CoreException {
		List<ArmyConsume> armyConsumes = Lists.newArrayList();
		try {
			Set<String> armyConsumeIds = armyConsumeDAO.getSetCollection(userRaceId, battleId, battleType);
			Iterator<String> iterator = armyConsumeIds.iterator();
			while(iterator.hasNext()) {
				String next = iterator.next();
				long id = Long.parseLong(next);
				ArmyConsume armyConsume = armyConsumeDAO.getArmyConsume(id);
				armyConsumes.add(armyConsume);
			}
		} catch (DBException e) {
			throw new CoreException(e);
		}
		return armyConsumes;
	}

}
