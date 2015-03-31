package com.pureland.common.service;

import com.pureland.common.db.data.Ship;
import com.pureland.common.error.CoreException;

import java.util.List;

/**
 * Created by Administrator on 2015/3/7.
 */
public interface ExploreCommonService {

	void addShip(Ship ship) throws CoreException;

	List<Ship> getShips(Long userRaceId) throws CoreException;

	Ship getShip(long userRaceId, long sid) throws CoreException;

	public void updateShip(Ship ship) throws CoreException;

	public Long getShipsCount(Long userRaceId) throws CoreException;
}
