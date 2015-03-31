package com.pureland.common.service.impl;

import java.util.List;
import java.util.Map;

import com.pureland.common.db.dao.redis.explore.ShipDAO;
import com.pureland.common.db.data.Resource;
import com.pureland.common.db.data.Ship;
import com.pureland.common.db.error.DBException;
import com.pureland.common.enums.ShipStateEnum;
import com.pureland.common.error.CoreException;
import com.pureland.common.service.ExploreCommonService;
import com.pureland.common.util.SpringContextUtil;

/**
 * Created by Administrator on 2015/3/7.
 */
public class ExploreCommonServiceImpl implements ExploreCommonService {

	private ShipDAO shipDao = (ShipDAO) SpringContextUtil.getBean(ShipDAO.class.getSimpleName());

	@Override
	public void addShip(Ship ship) throws CoreException {
		try {
			shipDao.addShip(ship);
		} catch (DBException e) {
			throw new CoreException(e.getMessage());
		}
	}

	@Override
	public List<Ship> getShips(Long userRaceId) throws CoreException {
		try {
			List<String> shipIds = shipDao.getShipIds(userRaceId);
			return shipDao.getShips(userRaceId, shipIds);
		} catch (DBException e) {
			throw new CoreException(e.getMessage());
		}
	}

	@Override
	public Ship getShip(long userRaceId, long sid) throws CoreException {
		try {
			return shipDao.getShip(userRaceId, sid);
		} catch (DBException e) {
			throw new CoreException(e.getMessage());
		}
	}

	@Override
	public void updateShip(Ship ship) throws CoreException {
		try {
			shipDao.updateShip(ship);
		} catch (DBException e) {
			throw new CoreException(e.getMessage());
		}
	}

	@Override
	public Long getShipsCount(Long userRaceId) throws CoreException {
		try {
			return shipDao.getShipsCount(userRaceId);
		} catch (DBException e) {
			throw new CoreException(e.getMessage());
		}
	}

	/**
	 * 在升级完成或者取消后设置ship状态
	 *
	 * @param ship
	 */
	public void setStateAfterUpgradeCompleteOrCancel(Ship ship) {
		Map<String, Resource> map = ship.getResourcesForMap();
		boolean isHasResource = false;
		if (map != null && map.size() > 0) {
			for (Resource resource : map.values()) {
				if (resource.getCount() > 0) {
					isHasResource = true;
					break;
				}
			}
		}
		if (isHasResource) {
			ship.setState(ShipStateEnum.COMPLETE.ordinal());
		} else {
			ship.setState(ShipStateEnum.IDLE.ordinal());
		}
	}

}
