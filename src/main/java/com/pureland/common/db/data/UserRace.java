package com.pureland.common.db.data;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.common.collect.Maps;
import com.pureland.common.db.data.clan.DonateArmy;
import com.pureland.common.enums.SubServerTypeEnum;
import com.pureland.common.enums.Entity;
import com.pureland.common.util.DataObject;
import com.pureland.common.util.GuavaFunctions;
import com.pureland.common.util.GuavaFunctions.GroupFunction;

public class UserRace extends DataObject {

	private static final long serialVersionUID = 4921496401061763220L;

	private Long id;
	private Long userId;
	private Integer raceId;
	private Long lastLoginTime;
	private Long lastOperateTime; // 最后一次发送消息的时间
	private String nickName;
	private Integer campCid;
	private Set<String> armyShop;
	private Set<String> skillShop;

	private Integer upgradeSkillCid; // 空的时候为0
	private Long upgradeSkillCompleteTime;
	private UserExt userExt;
	private List<Building> buildings;
	private List<Army> armies;
	private List<Skill> skills;
	private DonateArmy donateArmy;

	private Map<SubServerTypeEnum, List<Building>> buildingsForMap;

	public static enum Field {
		SHIPS
	}

	public static String generatorFieldKey(Long id, String field) {
		return generatorFieldKey(Entity.USERRACE, id, field);
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	public DonateArmy getDonateArmy() {
		return donateArmy;
	}

	public void setDonateArmy(DonateArmy donateArmy) {
		this.donateArmy = donateArmy;
	}

	/**
	 * @return the userId
	 */
	public Long getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Integer getRaceId() {
		return raceId;
	}

	public void setRaceId(Integer raceId) {
		this.raceId = raceId;
	}

	public void setUpgradeSkillCid(Integer upgradeSkillCid) {
		this.upgradeSkillCid = upgradeSkillCid;
	}

	public void setUpgradeSkillCompleteTime(Long upgradeSkillCompleteTime) {
		this.upgradeSkillCompleteTime = upgradeSkillCompleteTime;
	}

	public Long getLastOperateTime() {
		return lastOperateTime;
	}

	public void setLastOperateTime(Long lastOperateTime) {
		this.lastOperateTime = lastOperateTime;
	}

	/**
	 * @return the lastLoginTime
	 */
	public Long getLastLoginTime() {
		return lastLoginTime;
	}

	/**
	 * @param lastLoginTime
	 *            the lastLoginTime to set
	 */
	public void setLastLoginTime(Long lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	/**
	 * @return the campCid
	 */
	public Integer getCampCid() {
		return campCid;
	}

	/**
	 * @param campCid
	 *            the campCid to set
	 */
	public void setCampCid(Integer campCid) {
		this.campCid = campCid;
	}

	/**
	 * @return the userExt
	 */
	public UserExt getUserExt() {
		return userExt;
	}

	/**
	 * @param userExt
	 *            the userExt to set
	 */
	public void setUserExt(UserExt userExt) {
		this.userExt = userExt;
	}

	/**
	 * @return the buildings
	 */
	public List<Building> getBuildings() {
		return buildings;
	}

	/**
	 * @param buildings
	 *            the buildings to set
	 */
	public void setBuildings(List<Building> buildings) {
		this.buildings = buildings;
		this.translateForMap(this.buildings);
	}

	/**
	 * @return the armies
	 */
	public List<Army> getArmies() {
		return armies;
	}

	/**
	 * @param armies
	 *            the armies to set
	 */
	public void setArmies(List<Army> armies) {
		this.armies = armies;
	}

	public List<Skill> getSkills() {
		return skills;
	}

	public void setSkills(List<Skill> skills) {
		this.skills = skills;
	}

	/**
	 * @return the buildingsForMap
	 */
	public Map<SubServerTypeEnum, List<Building>> getBuildingsForMap() {
		return buildingsForMap;
	}

	public Set<String> getSkillShop() {
		return skillShop;
	}

	public void setSkillShop(Set<String> skillShop) {
		this.skillShop = skillShop;
	}

	public Integer getUpgradeSkillCid() {
		return upgradeSkillCid;
	}

	public void setUpgradeSkillCid(int upgradeSkillCid) {
		this.upgradeSkillCid = upgradeSkillCid;
	}

	public Long getUpgradeSkillCompleteTime() {
		return upgradeSkillCompleteTime;
	}

	public void setUpgradeSkillCompleteTime(long upgradeSkillCompleteTime) {
		this.upgradeSkillCompleteTime = upgradeSkillCompleteTime;
	}

	public Set<String> getArmyShop() {
		return armyShop;
	}

	public void setArmyShop(Set<String> armyShop) {
		this.armyShop = armyShop;
	}

	/**
	 * @param buildingsForMap
	 *            the buildingsForMap to set
	 */
	public void setBuildingsForMap(Map<SubServerTypeEnum, List<Building>> buildingsForMap) {
		this.buildingsForMap = buildingsForMap;
	}

	private void translateForMap(List<Building> buildings) {
		List<List<Building>> group = GuavaFunctions.group(buildings, new GroupFunction<Building>() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * com.pureland.common.util.GuavaFunctions.GroupFunction#sameGroup
			 * (java.lang.Object, java.lang.Object)
			 */
			@Override
			public boolean sameGroup(Building element1, Building element2) {
				int id1 = element1.getBuildingType().ordinal();
				int id2 = element2.getBuildingType().ordinal();
				if (id1 == id2) {
					return true;
				}
				return false;
			}

		});

		Map<SubServerTypeEnum, List<Building>> buildingsForMap = Maps.newHashMap();
		for (List<Building> subBuildings : group) {
			Building building = subBuildings.get(0);
			SubServerTypeEnum buildingType = building.getBuildingType();
			buildingsForMap.put(buildingType, subBuildings);
		}

		this.setBuildingsForMap(buildingsForMap);
	}

}
