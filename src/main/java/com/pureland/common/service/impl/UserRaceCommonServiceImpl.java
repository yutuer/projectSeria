package com.pureland.common.service.impl;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.pureland.common.db.dao.redis.*;
import com.pureland.common.db.dao.redis.explore.ShipDAO;
import com.pureland.common.db.data.*;
import com.pureland.common.db.data.clan.DonateArmy;
import com.pureland.common.enums.RaceServerTypeEnum;
import com.pureland.common.enums.ResourceServerTypeEnum;
import com.pureland.common.service.*;
import com.pureland.common.service.bean.ResourceCostBean;
import com.pureland.common.service.helper.ShipDbHelper;

import org.apache.commons.collections.CollectionUtils;

import com.google.common.collect.Lists;
import com.pureland.common.db.error.DBException;
import com.pureland.common.error.CoreException;
import com.pureland.common.log.PurelandLog;
import com.pureland.common.util.SpringContextUtil;

/**
 * @author qinpeirong
 */
public class UserRaceCommonServiceImpl extends BaseService implements UserRaceCommonService {

	private String TAG = PurelandLog.getClassTag(UserRaceCommonServiceImpl.class);

	private BuildingCommonService buildingCommonService = (BuildingCommonService) SpringContextUtil.getBean(BuildingCommonServiceImpl.class.getSimpleName());
	private UserExtCommonService userExtCommonService = (UserExtCommonService) SpringContextUtil.getBean(UserExtCommonServiceImpl.class.getSimpleName());
	private ArmyCommonService armyCommonService = (ArmyCommonService) SpringContextUtil.getBean(ArmyCommonServiceImpl.class.getSimpleName());
	private SkillCommonService skillCommonService = (SkillCommonService) SpringContextUtil.getBean(SkillCommonServiceImpl.class.getSimpleName());
	private UserRaceDAO userRaceDAO = (UserRaceDAO) SpringContextUtil.getBean(UserRaceDAO.class.getSimpleName());
	private UserRaceMappingDAO userRaceMappingDAO = (UserRaceMappingDAO) SpringContextUtil.getBean(UserRaceMappingDAO.class.getSimpleName());
	private ResourceDAO resourceDAO = (ResourceDAO) SpringContextUtil.getBean(ResourceDAO.class.getSimpleName());
	private ArmyExpDAO armyExpDAO = (ArmyExpDAO) SpringContextUtil.getBean(ArmyExpDAO.class.getSimpleName());
	private ShipDAO shipDao = (ShipDAO) SpringContextUtil.getBean(ShipDAO.class.getSimpleName());
	private ClanCommonService clanCommonService = (ClanCommonService) SpringContextUtil.getBean(ClanCommonServiceImpl.class.getSimpleName());

	@Override
	public Long addUserRace(UserRace userRace) throws CoreException {
		Long userRaceId = null;
		try {
			userRaceId = userRaceDAO.addUserRace(userRace);
			for (String armyExpCid : userRace.getArmyShop()) {
				ArmyExp armyExp = new ArmyExp();
				armyExp.setCid(Integer.parseInt(armyExpCid));
				armyExp.setUserRaceId(userRaceId);
				armyExp.setExp(0);
				armyExpDAO.addArmyExp(armyExp);
			}
			shipDao.addShip(ShipDbHelper.GetNewShip(userRaceId,
					RaceServerTypeEnum.getRaceServerTypeEnumById(Integer.parseInt(String.valueOf(userRace.getRaceId())))));
		} catch (DBException e) {
			error(e);
		}
		return userRaceId;
	}

	@Override
	public List<UserRace> getUserRaces(Long userId, Boolean sort) throws CoreException {
		List<UserRace> userRaces = Lists.newArrayList();

		try {
			Set<String> userRaceIds = userRaceMappingDAO.getSetCollection(userId);

			Iterator<String> iterator = userRaceIds.iterator();
			while (iterator.hasNext()) {
				String userRaceId = iterator.next();
				UserRace userRace = getUserRace(Long.parseLong(userRaceId));
				userRaces.add(userRace);
			}

		} catch (DBException e) {
			error(e);
		}

		if (sort != null && sort == true) {
			if (CollectionUtils.isNotEmpty(userRaces)) {
				Collections.sort(userRaces, new Comparator<UserRace>() {

					@Override
					public int compare(UserRace arg0, UserRace arg1) {
						if (arg0.getLastLoginTime() <= arg1.getLastLoginTime())
							return 1;
						return 0;
					}
				});
			}
		}
		return userRaces;
	}

	@Override
	public UserRace getUserRace(Long userRaceId) throws CoreException {
		UserRace userRace = null;
		try {
			userRace = userRaceDAO.getUserRace(userRaceId);
			UserExt userExt = userExtCommonService.getUserExt(userRaceId);
			List<Building> buildings = buildingCommonService.getBuildings(userRaceId);
			List<Army> armies = armyCommonService.getArmies(userRaceId);
			List<Skill> skills = skillCommonService.getSkills(userRaceId);
			Set<String> armyShop = armyExpDAO.getArmyExpCids(userRaceId);
			DonateArmy donateArmy = clanCommonService.getUserRaceDonateArmy(userRaceId);
			userRace.setUserExt(userExt);
			userRace.setBuildings(buildings);
			userRace.setArmies(armies);
			userRace.setSkills(skills);
			userRace.setArmyShop(armyShop);
			userRace.setDonateArmy(donateArmy);
		} catch (DBException e) {
			error(e);
		}
		return userRace;
	}

	@Override
	public void updateUserRace(UserRace userRace) throws CoreException {
		try {
			userRaceDAO.updateUserRace(userRace);
		} catch (DBException e) {
			throw new CoreException(e);
		}
	}

	@Override
	public void updateNickName(String nickName) throws CoreException {
		// TODO Auto-generated method stub

	}

	/**
	 * @param userRaceDAO
	 *            the userRaceDAO to set
	 */
	public void setUserRaceDAO(UserRaceDAO userRaceDAO) {
		this.userRaceDAO = userRaceDAO;
	}

	/**
	 * @param userRaceMappingDAO
	 *            the userRaceMappingDAO to set
	 */
	public void setUserRaceMappingDAO(UserRaceMappingDAO userRaceMappingDAO) {
		this.userRaceMappingDAO = userRaceMappingDAO;
	}

	@Override
	public Set<String> getAllOfflineUserRaceIds() throws CoreException {
		Set<String> ret = null;
		try {
			ret = userRaceDAO.getAllOfflineUserRaceIds();
		} catch (DBException e) {
			error(e);
		}
		return ret;
	}

	@Override
	public Set<String> getSkillShop(Long userRaceId) throws CoreException {
		try {
			return userRaceDAO.getSkillShop(userRaceId);
		} catch (DBException e) {
			throw new CoreException(e.getMessage());
		}
	}

	@Override
	public void updateSkillShopOneSkillId(Long userRaceId, String skillId, String upgradeSkillId) throws CoreException {
		try {
			userRaceDAO.removeSkillIdFromSkillShop(userRaceId, skillId);
			userRaceDAO.addSkillId2SkillShop(userRaceId, upgradeSkillId);
		} catch (DBException e) {
			throw new CoreException(e.getMessage());
		}
	}

	@Override
	public void resourceAdd(ResourceCostBean resourceCostBean) throws CoreException {
		Long userRaceId = resourceCostBean.getUserRaceId();
		ResourceServerTypeEnum resourceNeededType = ResourceServerTypeEnum.getResourceServerTypeEnumByName(resourceCostBean.getCostType());
		UserRace userRace = getUserRace(userRaceId);
		UserExt userExt = userRace.getUserExt();
		Resource resource = userExt.getResourcesForMap().get(resourceNeededType);
		Integer currentReourceCount = resource.getCount();
		// 待完善加速场景
		resource.setCount(currentReourceCount + resourceCostBean.getCount());
		try {
			resourceDAO.updateResource(resource);
		} catch (DBException e) {
			throw new CoreException(e.getMessage());
		}
	}

	@Override
	public void removeSkillIdFromSkillShop(Long userRaceId, String skillId) throws CoreException {
		try {
			userRaceDAO.removeSkillIdFromSkillShop(userRaceId, skillId);
		} catch (DBException e) {
			throw new CoreException(e.getMessage());
		}
	}

	@Override
	public void addSkillId2SkillShop(Long userRaceId, String skillId) throws CoreException {
		try {
			userRaceDAO.addSkillId2SkillShop(userRaceId, skillId);
		} catch (DBException e) {
			throw new CoreException(e.getMessage());
		}
	}

	@Override
	public char[][] getBuildingSpaceInfo(Long userRaceId) {
		return userRaceDAO.getBuildingSpaceInfo(userRaceId);
	}

	@Override
	public void setBuildingSpaceInfo(Long userRaceId, char[][] buildingSpaceInfo) throws CoreException {
		try {
			userRaceDAO.setBuildingSpaceInfo(userRaceId, buildingSpaceInfo);
		} catch (DBException e) {
			throw new CoreException(e.getMessage());
		}
	}

	@Override
	public void updateLastOperateTime(Long userRaceId) throws CoreException {
		try {
			userRaceDAO.updateLastOperateTime(userRaceId);
		} catch (DBException e) {
			throw new CoreException(e.getMessage());
		}
	}

	@Override
	public Double getLastOperateTime(Long userRaceId) throws CoreException {
		try {
			return userRaceDAO.getLastOperateTime(userRaceId);
		} catch (DBException e) {
			throw new CoreException(e.getMessage());
		}
	}

	@Override
	public void updateUserRaceFightStatus(Long userRaceId, boolean isInFight) throws CoreException {
		try {
			userRaceDAO.updateUserRaceFightStatus(userRaceId, isInFight);
		} catch (DBException e) {
			throw new CoreException(e.getMessage());
		}
	}

	@Override
	public boolean isUserRaceInFight(Long userRaceId) throws CoreException {
		try {
			return userRaceDAO.isUserRaceInFight(userRaceId);
		} catch (DBException e) {
			throw new CoreException(e.getMessage());
		}
	}

	@Override
	public void updateUserRaceFightOpponent(Long userRaceId, Long opponentUserRaceId) throws CoreException {
		try {
			userRaceDAO.updateUserRaceFightOpponent(userRaceId, opponentUserRaceId);
		} catch (DBException e) {
			throw new CoreException(e.getMessage());
		}
	}

	@Override
	public Long getUserRaceFightOpponent(Long userRaceId) throws CoreException {
		try {
			return userRaceDAO.getUserRaceFightOpponent(userRaceId);
		} catch (DBException e) {
			throw new CoreException(e.getMessage());
		}
	}

}
