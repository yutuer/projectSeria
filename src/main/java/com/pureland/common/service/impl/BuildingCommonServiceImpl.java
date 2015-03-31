package com.pureland.common.service.impl;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.common.collect.Lists;
import com.pureland.common.db.dao.redis.BuildingDAO;
import com.pureland.common.db.dao.redis.UserRaceDAO;
import com.pureland.common.db.data.Building;
import com.pureland.common.db.data.Product;
import com.pureland.common.db.data.UserRace;
import com.pureland.common.db.error.DBException;
import com.pureland.common.db.statics.EntityModel;
import com.pureland.common.enums.BuildingServerStatus;
import com.pureland.common.enums.EntityServerTypeEnum;
import com.pureland.common.enums.ResourceServerTypeEnum;
import com.pureland.common.enums.SubServerTypeEnum;
import com.pureland.common.error.CoreException;
import com.pureland.common.service.BaseService;
import com.pureland.common.service.BuildingCommonService;
import com.pureland.common.service.ProductCommonService;
import com.pureland.common.service.WorkerQueueCommonService;
import com.pureland.common.service.bean.MoveBuildingBean;
import com.pureland.common.service.bean.SkillInfo;
import com.pureland.common.util.GameUtil;
import com.pureland.common.util.RuleEx;
import com.pureland.common.util.SpringContextUtil;
import com.pureland.core.init.EntityModelHelper;

public class BuildingCommonServiceImpl extends BaseService implements BuildingCommonService {

	protected ProductCommonService productCommonService = (ProductCommonService) SpringContextUtil.getBean(ProductCommonServiceImpl.class.getSimpleName());
	protected WorkerQueueCommonService queueCommonService = (WorkerQueueCommonService) SpringContextUtil.getBean(WorkerQueueCommonServiceImpl.class
			.getSimpleName());
	private BuildingDAO buildingDAO = (BuildingDAO) SpringContextUtil.getBean(BuildingDAO.class.getSimpleName());
	private UserRaceDAO userRaceDAO = (UserRaceDAO) SpringContextUtil.getBean(UserRaceDAO.class.getSimpleName());

	@Override
	public void initBuilding(Long userRaceId, Integer raceId) throws CoreException {
		try {
			Map<SubServerTypeEnum, Integer> buildings = EntityModelHelper.buildings.get(raceId);

			Building camp = null;
			int line = 10;
			int coor = EntityModelHelper.InitCoor;
			for (Integer cid : buildings.values()) {
				EntityModel em = EntityModelHelper.ENTITIES.get(cid);
				SubServerTypeEnum bt = SubServerTypeEnum.valueOf(em.getSubType());
				Building build = new Building();
				build.setUserRaceId(userRaceId);
				build.setCid(buildings.get(bt));
				if (bt == SubServerTypeEnum.Base) {
					camp = build;
				} else if (bt == SubServerTypeEnum.Market || bt == SubServerTypeEnum.Factory) {
					build.setGatherTime(System.currentTimeMillis() - EntityModelHelper.InitGatherTime);
					build.setStorageCount(EntityModelHelper.InitStorageCount);
				}
				build.setStatus(EntityModelHelper.InitBuildStatus);
				build.setBuildingType(bt);
				build.setAbscissa(coor);
				build.setOrdinate(line);
				char[][] c = userRaceDAO.getBuildingSpaceInfo(userRaceId);
				boolean isConflict = GameUtil.isBuildConflict(c, coor, line, em.getTileSize() * 2);
				if (isConflict) {
					throw new CoreException("建造位置冲突, cid:%d", build.getCid());
				}
				buildingDAO.addBuilding(build);
				coor += 10;
				if (coor / 10 == 6) {
					coor = EntityModelHelper.InitCoor;
					line += 10;
				}
			}

			UserRace userRace = new UserRace();
			userRace.setId(userRaceId);
			userRace.setCampCid(camp.getCid());
			userRaceDAO.updateUserRace(userRace);
		} catch (DBException e) {
			error(e);
		}
	}

	@Override
	public Boolean addBuilding(Long sid, Long userRaceId, Integer cid, Integer abs, Integer ord, long endTime, RuleEx ruleEx) throws CoreException {
		Boolean flag = false;
		try {
			Building building = new Building();
			building.setSid(sid);
			building.setUserRaceId(userRaceId);
			building.setCid(cid);
			building.setStatus(BuildingServerStatus.CONSTRUCT);
			EntityModel em = EntityModelHelper.ENTITIES.get(cid);
			Integer subType = SubServerTypeEnum.getSubServerTypeEnumByName(em.getSubType()).ordinal();
			if (em.getResourcePerSecond() > 0) {
				building.setGatherTime(endTime);
				building.setStorageCount(0);
			}
			building.setBuildingType(SubServerTypeEnum.getSubServerTypeEnumById(subType));
			building.setEndTime(endTime);
			building.setAbscissa(abs);
			building.setOrdinate(ord);

			if (ruleEx.rule()) {
				char[][] c = userRaceDAO.getBuildingSpaceInfo(userRaceId);
				boolean isConflict = GameUtil.isBuildConflict(c, abs, ord, em.getTileSize() * 2);
				if (isConflict) {
					throw new CoreException("建造位置冲突,sid:%d", sid);
				}
				buildingDAO.addBuilding(building);
				flag = true;
			}
		} catch (DBException e) {
			error(e);
		}

		return flag;
	}

	@Override
	public Building getBuilding(String buildingId) throws CoreException {
		if (buildingId == null) {
			throw new CoreException("buildingId is null");
		}

		Building building = null;
		try {
			building = buildingDAO.getBuilding(buildingId);
			if (building != null) {
				List<Product> products = productCommonService.getProducts(building.getUserRaceId(), building.getSid());
				building.setProducts(products);
			}

		} catch (DBException e) {
			error(e);
		}
		return building;
	}

	@Override
	public Integer getResourceUpperLimit(Long userRaceId, ResourceServerTypeEnum resoureType) {
		int ret = 0;
		try {
			List<Building> buildings = getBuildings(userRaceId);
			for (Building b : buildings) {
				Integer cid = b.getCid();
				EntityModel entityModel = EntityModelHelper.ENTITIES.get(cid);
				if (resoureType.name().equals(entityModel.getResourceType()) && entityModel.getResourcePerSecond() == 0
						&& entityModel.getMaxResourceStorage() > 0) {
					ret += entityModel.getMaxResourceStorage();
				}
			}
		} catch (CoreException e) {
			e.printStackTrace();
		}
		return ret;
	}

	@Override
	public List<Building> getBuildings(Long userRaceId) throws CoreException {
		List<Building> buildings = Lists.newArrayList();
		try {
			Set<String> buildingIds = buildingDAO.getSetCollection(userRaceId);
			Iterator<String> iterator = buildingIds.iterator();

			while (iterator.hasNext()) {
				String buildingId = iterator.next();
				Building building = getBuilding(buildingId);
				if (building.getStatus().getId().equals(BuildingServerStatus.UPGRADE.getId())) {

				}
				buildings.add(building);
			}
		} catch (DBException e) {
			error(e);
		}
		return buildings;
	}

	@Override
	public void updateBuilding(Building building) throws CoreException {
		try {
			buildingDAO.updateBuilding(building);
		} catch (DBException e) {
			throw new CoreException(e);
		}
	}

	@Override
	public List<Building> getBuildingByEntityType(long userRaceId, EntityServerTypeEnum type) throws CoreException {
		List<Building> ret = Lists.newArrayList();
		Set<String> buildingIds = null;
		try {
			buildingIds = buildingDAO.getSetCollection(userRaceId);
			Iterator<String> iterator = buildingIds.iterator();
			while (iterator.hasNext()) {
				String buildingId = iterator.next();
				Building building = buildingDAO.getBuilding(buildingId);
				EntityModel em = EntityModelHelper.ENTITIES.get(building.getCid());
				if (em.getEntityType().equals(type.name())) {
					ret.add(building);
				}
			}
		} catch (DBException e) {
			e.printStackTrace();
		}
		return ret;
	}

	@Override
	public List<Building> getBuildingBySubType(long userRaceId, SubServerTypeEnum type) throws CoreException {
		List<Building> ret = Lists.newArrayList();
		Set<String> buildingIds = null;
		try {
			buildingIds = buildingDAO.getSetCollection(userRaceId);
			Iterator<String> iterator = buildingIds.iterator();
			while (iterator.hasNext()) {
				String buildingId = iterator.next();
				Building building = buildingDAO.getBuilding(buildingId);
				if (building.getBuildingType() == type) {
					ret.add(building);
				}
			}
		} catch (DBException e) {
			e.printStackTrace();
		}
		return ret;
	}

	@Override
	public int getSameTypeBuildingsLeftSpace(Long userRaceId, SubServerTypeEnum buildingType) throws CoreException {
		// 需要判断空间 spaceProvide
		List<Building> buildingList = getBuildingBySubType(userRaceId, buildingType);
		// 军营存放上限
		int totalMax = 0;
		for (Building building : buildingList) {
			EntityModel em = EntityModelHelper.ENTITIES.get(building.getCid());
			totalMax += em.getSpaceProvide();
		}
		return totalMax;
	}

	@Override
	public int getSameTypeBuildingsMaxResourceStorage(Long userRaceId, SubServerTypeEnum buildingType) throws CoreException {
		// 需要判断空间 spaceProvide
		List<Building> buildingList = getBuildingBySubType(userRaceId, buildingType);
		// 军营存放上限
		int totalMax = 0;
		for (Building building : buildingList) {
			EntityModel em = EntityModelHelper.ENTITIES.get(building.getCid());
			totalMax += em.getMaxResourceStorage();
		}
		return totalMax;
	}

	@Override
	public void saveUpgradeSkillInfo(Long userRaceId, SkillInfo skillInfo) throws CoreException {
		List<Building> laboratorys = getBuildingBySubType(userRaceId, SubServerTypeEnum.Research);
		Building laboratory = laboratorys.get(0);
		laboratory.setStorageCount(skillInfo.getUpgradeSkillCid());
		laboratory.setEndTime(skillInfo.getUpgradeSkillCompleteTime());
		updateBuilding(laboratory);
	}

	@Override
	public void deleteUpgradeSkillInfo(Long userRaceId) throws CoreException {
		List<Building> laboratorys = getBuildingBySubType(userRaceId, SubServerTypeEnum.Research);
		Building laboratory = laboratorys.get(0);
		laboratory.setStorageCount(0);
		laboratory.setEndTime(0L);
		updateBuilding(laboratory);
	}

	@Override
	public SkillInfo getUpgradeSkillInfo(Long userRaceId) throws CoreException {
		List<Building> laboratorys = getBuildingBySubType(userRaceId, SubServerTypeEnum.Research);
		Building laboratory = laboratorys.get(0);
		return new SkillInfo(laboratory.getStorageCount(), laboratory.getEndTime());
	}

	@Override
	public void moveBuilding(Long userRaceId, List<MoveBuildingBean> moveBuildingBeanList) throws CoreException {
		try {
			char[][] c = userRaceDAO.getBuildingSpaceInfo(userRaceId);
			for (MoveBuildingBean moveBuildingBean : moveBuildingBeanList) {
				Long sid = moveBuildingBean.getSid();
				String buildingId = Building.getIdKeyString(userRaceId, sid);
				Building building = buildingDAO.getBuilding(buildingId);
				EntityModel em = building.getEntityModel();
				int beforeX = building.getAbscissa();
				int beforeY = building.getOrdinate();
				int size = em.getTileSize().intValue() * 2;
				GameUtil.removeBuildSpaceInfo(c, beforeX, beforeY, size);
			}
			for (MoveBuildingBean moveBuildingBean : moveBuildingBeanList) {
				Long sid = moveBuildingBean.getSid();
				String buildingId = Building.getIdKeyString(userRaceId, sid);
				Building building = buildingDAO.getBuilding(buildingId);
				EntityModel em = building.getEntityModel();
				int size = em.getTileSize().intValue() * 2;
				int afterX = moveBuildingBean.getX();
				int afterY = moveBuildingBean.getY();

				if (GameUtil.isBuildConflict(c, afterX, afterY, size)) {
					throw new CoreException("不能移动cid:%d的建筑, afterX:%d, afterY:%d ", building.getCid(), afterX, afterY);
				}
				GameUtil.addBuildSpaceInfo(c, afterX, afterY, size);
				building.setAbscissa(afterX);
				building.setOrdinate(afterY);
				buildingDAO.updateBuilding(building);
			}
			userRaceDAO.setBuildingSpaceInfo(userRaceId, c);
		} catch (DBException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateBrokenTraps(Long userRaceId, List<Long> brokenTraps) throws CoreException {
		for (Long brokenTrapSid : brokenTraps) {
			String buildingId = Building.getIdKeyString(userRaceId, brokenTrapSid);
			Building building = getBuilding(buildingId);
			EntityModel em = building.getEntityModel();
			if (!em.getEntityType().equals(EntityServerTypeEnum.Trap.name())) {
				throw new CoreException("建筑类型不匹配,sid:%d, reqIn:%d,需要%d", brokenTrapSid, em.getEntityType(), EntityServerTypeEnum.Trap.ordinal());
			}

			if (building.getStorageCount() != null && building.getStorageCount().intValue() == 1) {
				throw new CoreException("建筑已经坏了sid:%d", brokenTrapSid);
			}

			building.setStorageCount(1);
			updateBuilding(building);
		}
	}
}
