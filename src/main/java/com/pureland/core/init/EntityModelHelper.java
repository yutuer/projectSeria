package com.pureland.core.init;

import com.google.common.collect.Maps;
import com.pureland.common.db.dao.daoInterface.EntityModelDAOInterface;
import com.pureland.common.db.dao.mysql.EntityModelDAO;
import com.pureland.common.db.error.DBException;
import com.pureland.common.db.statics.EntityModel;
import com.pureland.common.enums.*;
import com.pureland.common.util.SpringContextUtil;

import java.util.List;
import java.util.Map;

/**
 * 静态数据
 *
 * @author qinpeirong
 */
public class EntityModelHelper {
	private static final int Add = 0;
	private EntityModelDAOInterface entityModelDAO = (EntityModelDAOInterface) SpringContextUtil.getBean(EntityModelDAO.class.getSimpleName());

	public static final Map<Integer, EntityModel> ENTITIES = Maps.newHashMap();

	public static Integer level = 1;
	public static Integer experience = null;
	public static Integer crown = null;
	public static Map<ResourceServerTypeEnum, Integer> resource = Maps.newHashMap();
	public static Map<Integer, Map<SubServerTypeEnum, Integer>> buildings = Maps.newHashMap();

	public static int InitCoor = 10;
	public static int InitStorageCount = 0;
	public static long InitGatherTime = 100 * 1000L;
	public static BuildingServerStatus InitBuildStatus = BuildingServerStatus.ON;

	static {
		// 初始化数据集合
		level = 1;
		experience = 100;
		crown = 100;
		resource.put(ResourceServerTypeEnum.Gold, 500);
		resource.put(ResourceServerTypeEnum.Oil, 500);
		resource.put(ResourceServerTypeEnum.Diamond, 0);

		Map<SubServerTypeEnum, Integer> humanBuildings = Maps.newHashMap();
		Map<SubServerTypeEnum, Integer> predatorBuildings = Maps.newHashMap();

		humanBuildings.put(SubServerTypeEnum.Base, 10101 + Add);
		humanBuildings.put(SubServerTypeEnum.Market, 10201 + Add);
		humanBuildings.put(SubServerTypeEnum.Vault, 10301 + Add);
		humanBuildings.put(SubServerTypeEnum.Factory, 10501 + Add);
		humanBuildings.put(SubServerTypeEnum.Warehouse, 10601 + Add);
		humanBuildings.put(SubServerTypeEnum.WorkHouse, 16601 + Add);
		humanBuildings.put(SubServerTypeEnum.Laboratory, 13901 + Add);
		humanBuildings.put(SubServerTypeEnum.Research, 11301 + Add);
		humanBuildings.put(SubServerTypeEnum.Army, 11201 + Add);
		humanBuildings.put(SubServerTypeEnum.Barracks, 13801 + Add);
		humanBuildings.put(SubServerTypeEnum.TrapA, 11701 + Add);
		humanBuildings.put(SubServerTypeEnum.BigBomb, 16001 + Add);
		humanBuildings.put(SubServerTypeEnum.Federal, 17301 + Add);

		predatorBuildings.put(SubServerTypeEnum.Base, 20101 + Add);
		predatorBuildings.put(SubServerTypeEnum.Market, 20201 + Add);
		predatorBuildings.put(SubServerTypeEnum.Vault, 20301 + Add);
		predatorBuildings.put(SubServerTypeEnum.Factory, 20501 + Add);
		predatorBuildings.put(SubServerTypeEnum.Warehouse, 20601 + Add);
		predatorBuildings.put(SubServerTypeEnum.WorkHouse, 26601 + Add);
		predatorBuildings.put(SubServerTypeEnum.Laboratory, 23901 + Add);
		predatorBuildings.put(SubServerTypeEnum.Research, 21301 + Add);
		predatorBuildings.put(SubServerTypeEnum.Army, 21201 + Add);
		predatorBuildings.put(SubServerTypeEnum.Barracks, 23801 + Add);
		predatorBuildings.put(SubServerTypeEnum.TrapA, 21701 + Add);
		predatorBuildings.put(SubServerTypeEnum.BigBomb, 26001 + Add);
		predatorBuildings.put(SubServerTypeEnum.Federal, 27301 + Add);

		buildings.put(RaceServerTypeEnum.Human.ordinal(), humanBuildings);
		buildings.put(RaceServerTypeEnum.Predator.ordinal(), predatorBuildings);
	}

	public void init() {
		try {
			List<EntityModel> queryEntityModelList = entityModelDAO.queryEntityModelList();
			for (EntityModel entityModel : queryEntityModelList) {
				ENTITIES.put(entityModel.getBaseId(), entityModel);
			}
		} catch (DBException e) {
			e.printStackTrace();
		}
	}

	public static boolean IsResearchBuilding(EntityModel em) {
		return em.getSubType().equals(SubServerTypeEnum.Research.name());
	}

	public static boolean IsArmyShop(EntityModel em) {
		return em.getSubType().equals(SubServerTypeEnum.Army.name());
	}

	public static boolean IsSkillShop(EntityModel em) {
		return em.getSubType().equals(SubServerTypeEnum.Laboratory.name());
	}

	public static boolean IsAnyProductBuilding(EntityModel model) {
		return IsArmyShop(model) || IsSkillShop(model);
	}

	public static boolean IsTranBuilding(EntityModel em) {
		return em.getEntityType().equals(EntityServerTypeEnum.Trap.name());
	}

	public static boolean IsFederalBuilding(EntityModel em) {
		return em.getEntityType().equals(SubServerTypeEnum.Federal.name());
	}
}
