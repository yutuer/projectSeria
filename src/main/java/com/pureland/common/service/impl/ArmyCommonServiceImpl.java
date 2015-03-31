package com.pureland.common.service.impl;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.google.common.collect.Lists;
import com.pureland.common.db.dao.redis.ArmyDAO;
import com.pureland.common.db.dao.redis.ArmyExpDAO;
import com.pureland.common.db.data.Army;
import com.pureland.common.db.data.ArmyExp;
import com.pureland.common.db.error.DBException;
import com.pureland.common.db.statics.EntityModel;
import com.pureland.common.error.CoreException;
import com.pureland.common.service.ArmyCommonService;
import com.pureland.common.service.BaseService;
import com.pureland.common.util.SpringContextUtil;
import com.pureland.core.init.EntityModelHelper;

public class ArmyCommonServiceImpl extends BaseService implements ArmyCommonService {
	private ArmyDAO armyDAO = (ArmyDAO) SpringContextUtil.getBean(ArmyDAO.class.getSimpleName());
	private ArmyExpDAO armyExpDAO = (ArmyExpDAO) SpringContextUtil.getBean(ArmyExpDAO.class.getSimpleName());

	@Override
	public String addArmy(Long userRaceId, Integer cid, Integer amount, Integer totalAmount) throws CoreException {
		String id = null;
		try {
			Army army = new Army();
			army.setUserRaceId(userRaceId);
			army.setAmount(amount);
			army.setArmyExpId(armyExpDAO.getArmyExpId(userRaceId, cid));
			id = armyDAO.addArmy(army);
		} catch (DBException e) {
			error(e);
		}
		return id;
	}

	@Override
	public Army getArmy(Long armyId) throws CoreException {
		if (armyId == null) {
			throw new CoreException("armyId is null");
		}

		Army army = null;
		try {
			army = armyDAO.getArmy(armyId);
		} catch (DBException e) {
			error(e);
		}

		return army;
	}

	@Override
	public List<Army> getArmies(Long userRaceId) throws CoreException {
		List<Army> armies = Lists.newArrayList();
		try {
			Set<String> armyIds = armyDAO.getSetCollection(userRaceId);
			Iterator<String> iterator = armyIds.iterator();
			while (iterator.hasNext()) {
				String next = iterator.next();
				Army army = armyDAO.getArmy(Long.parseLong(next));
				armies.add(army);
			}
		} catch (DBException e) {
			error(e);
		}
		return armies;
	}

	@Override
	public void updateArmy(Army army) throws CoreException {
		try {
			armyDAO.updateArmy(army);
		} catch (DBException e) {
			error(e);
		}
	}

	@Override
	public void deleteArmy(Army army) throws CoreException {
		if (army == null) {
			throw new CoreException("army is null");
		}
		try {
			armyDAO.deleteArmy(army);
		} catch (DBException e) {
			error(e);
		}
	}

	@Override
	public int getArmySpace(Long userRaceId) throws CoreException {
		// 当前军队人口
		int totalNow = 0;
		try {
			List<Army> armyList = getArmies(userRaceId);
			for (Army army : armyList) {
				int curCount = army.getAmount();
				if (curCount > 0) {
					ArmyExp armyExp = armyExpDAO.getArmyExp(army.getArmyExpId());
					EntityModel em = EntityModelHelper.ENTITIES.get(armyExp.getCid());
					totalNow += em.getSpaceUse() * curCount;
				}
			}
		} catch (DBException e) {
			error(e);
		}
		return totalNow;
	}

	@Override
	public Army getArmyByCid(Long userRaceId, Integer cid) throws CoreException {
		try {
			return armyDAO.getArmyByCid(userRaceId, cid);
		} catch (DBException e) {
			throw new CoreException(e);
		}
	}

}
