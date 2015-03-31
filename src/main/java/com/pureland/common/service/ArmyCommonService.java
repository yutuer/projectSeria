package com.pureland.common.service;

import java.util.List;

import com.pureland.common.db.data.Army;
import com.pureland.common.error.CoreException;

public interface ArmyCommonService {

	public String addArmy(Long userRaceId, Integer cid, Integer amount, Integer totalAmount) throws CoreException;

	public Army getArmy(Long armyId) throws CoreException;

	public List<Army> getArmies(Long userRaceId) throws CoreException;

	public void updateArmy(Army army) throws CoreException;

	public void deleteArmy(Army army) throws CoreException;

	public int getArmySpace(Long userRaceId) throws CoreException;

	Army getArmyByCid(final Long userRaceId, final Integer cid) throws CoreException;
}
