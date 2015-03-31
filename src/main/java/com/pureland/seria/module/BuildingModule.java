package com.pureland.seria.module;

import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.pureland.common.enums.Entity;
import com.pureland.seria.db.seriaData.building.Building;

public class BuildingModule extends AbstractModule {

	private static final long serialVersionUID = -5587728179340831400L;

	private Map<String, Building> buildingMaps = Maps.newHashMap();

	@Override
	public String getName() {
		return ModuleEnum.Building.name();
	}

	public void addBuilding(Building building) {
		buildingMaps.put(building.getBuildingId(), building);
	}

	public List<Building> getBuildingByType(String type) {
		List<Building> ret = Lists.newArrayList();
		for (Building bs : buildingMaps.values()) {
			if (bs.getBuildingType().name().equals(type)) {
				ret.add(bs);
			}
		}
		return ret;
	}

	public List<Building> getBuildings() {
		return Lists.newArrayList(buildingMaps.values());
	}

	public Building getBuilding(String buildingId) {
		return buildingMaps.get(buildingId);
	}

	public boolean isExistBuilding(String buildingId) {
		return buildingMaps.containsKey(buildingId);
	}

	public static String getKeyString(Long playerId) {
		return Entity.PLAYER.name() + Entity.SEPARATOR + playerId + Entity.SEPARATOR + ModuleEnum.Building.name();
	}
}
