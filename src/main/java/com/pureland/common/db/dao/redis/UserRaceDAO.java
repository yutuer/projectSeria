package com.pureland.common.db.dao.redis;

import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

import com.pureland.common.component.cache.api.RSet;
import com.pureland.common.component.cache.api.RSortedset;
import com.pureland.common.component.cache.api.RString;
import com.pureland.common.component.cache.error.RedisException;
import com.pureland.common.db.data.UserRace;
import com.pureland.common.db.error.DBException;
import com.pureland.common.enums.Entity;
import com.pureland.common.util.GameUtil;
import com.pureland.common.util.SpringContextUtil;

/**
 * @author qinpeirong
 */
public class UserRaceDAO extends RedisDAO {

	private UserRaceMappingDAO userRaceMappingDAO = (UserRaceMappingDAO) SpringContextUtil.getBean(UserRaceMappingDAO.class.getSimpleName());

	public Long addUserRace(UserRace userRace) throws DBException {
		Long id = userRace.getId();
		Long userId = userRace.getUserId();
		Integer raceId = userRace.getRaceId();
		String nickName = userRace.getNickName();
		Long lastLoginTime = userRace.getLastLoginTime();
		Integer campCid = userRace.getCampCid();
		Set<String> armyShop = userRace.getArmyShop();
		Set<String> skillShop = userRace.getSkillShop();
		Integer upgradeSkillCid = userRace.getUpgradeSkillCid();
		Long upgradeSkillCompleteTime = userRace.getUpgradeSkillCompleteTime();

		try {
			id = RString.generator(Entity.PLAYER.getName());
			String keyUserId = UserRace.generatorFieldKey(id, Entity.UserRace.USERID.getName());
			String keyRaceId = UserRace.generatorFieldKey(id, Entity.UserRace.RACEID.getName());
			String keyNickName = UserRace.generatorFieldKey(id, Entity.UserRace.NICKNAME.getName());
			String keyLastLogin = UserRace.generatorFieldKey(id, Entity.UserRace.LASTLOGIN.getName());
			String keyCampCid = UserRace.generatorFieldKey(id, Entity.UserRace.CAMPCID.getName());
			String keySkillShop = UserRace.generatorFieldKey(id, Entity.UserRace.SKILLSHOP.getName());
			String keyUpgradeSkillCid = UserRace.generatorFieldKey(id, Entity.UserRace.UPGRADESKILLCID.getName());
			String keyUpgradeSkillCompleteTime = UserRace.generatorFieldKey(id, Entity.UserRace.UPGRADESKILLCOMPLETETIME.getName());
			String keyBuildingSpaceInfo = UserRace.generatorFieldKey(id, Entity.UserRace.BUILDINGSPACEINFO.getName());

			Jedis masterClient = null;
			try {
				masterClient = createMasterClient();
				Transaction tx = masterClient.multi();

				tx.set(keyUserId, String.valueOf(userId));
				tx.set(keyRaceId, String.valueOf(raceId));
				tx.set(keyLastLogin, String.valueOf(lastLoginTime));
				if (upgradeSkillCid != null) {
					tx.set(keyUpgradeSkillCid, String.valueOf(upgradeSkillCid));
				}
				if (upgradeSkillCompleteTime != null) {
					tx.set(keyUpgradeSkillCompleteTime, String.valueOf(upgradeSkillCompleteTime));
				}
				tx.sadd(keySkillShop, skillShop.toArray(new String[0]));
				if (campCid != null) {
					tx.set(keyCampCid, String.valueOf(campCid));
				}
				if (StringUtils.isNotEmpty(nickName)) {
					tx.set(keyNickName, nickName);
				}

				int length = GameUtil.LENGTH;
				// 初始化88*88
				char[][] c = new char[length][length];
				for (int i = 0; i < length; i++) {
					for (int j = 0; j < length; j++) {
						c[i][j] = GameUtil.NOEXIST;
					}
				}

				String valueBuildingSpaceInfo = GameUtil.transferChar2String(c);
				tx.set(keyBuildingSpaceInfo, valueBuildingSpaceInfo);
				tx.exec();
			} finally {
				if (masterClient != null) {
					jedisPoolMaster.returnResource(masterClient);
				}
			}

			addListCollection(String.valueOf(id));
			userRaceMappingDAO.addSetCollection(userId, String.valueOf(id));
		} catch (RedisException e) {
			throw new DBException(e.getMessage());
		}
		return id;
	}

	public void updateUserRace(UserRace userRace) throws DBException {
		Long id = userRace.getId();
		Long lastLoginTime = userRace.getLastLoginTime();
		Integer campCid = userRace.getCampCid();
		if (id == null) {
			throw new DBException("can not update userRace because of id is null");
		}

		Jedis masterClient = null;
		try {
			masterClient = createMasterClient();
			Transaction tx = masterClient.multi();
			if (lastLoginTime != null) {
				String keyLastLogin = UserRace.generatorFieldKey(id, Entity.UserRace.LASTLOGIN.getName());
				tx.set(keyLastLogin, String.valueOf(lastLoginTime));
			}
			if (campCid != null) {
				String keyCampCid = UserRace.generatorFieldKey(id, Entity.UserRace.CAMPCID.getName());
				tx.set(keyCampCid, String.valueOf(campCid));
			}
			tx.exec();
		} finally {
			if (masterClient != null) {
				jedisPoolMaster.returnResource(masterClient);
			}
		}
	}

	public UserRace getUserRace(Long userRaceId) throws DBException {
		UserRace userRace = new UserRace();

		String keyUserId = UserRace.generatorFieldKey(userRaceId, Entity.UserRace.USERID.getName());
		String keyRaceId = UserRace.generatorFieldKey(userRaceId, Entity.UserRace.RACEID.getName());
		String keyNickName = UserRace.generatorFieldKey(userRaceId, Entity.UserRace.NICKNAME.getName());
		String keyLastLoginTime = UserRace.generatorFieldKey(userRaceId, Entity.UserRace.LASTLOGIN.getName());
		String keyCampId = UserRace.generatorFieldKey(userRaceId, Entity.UserRace.CAMPCID.getName());
		String keySkillShop = UserRace.generatorFieldKey(userRaceId, Entity.UserRace.SKILLSHOP.getName());
		String keyUpgradeSkillCid = UserRace.generatorFieldKey(userRaceId, Entity.UserRace.UPGRADESKILLCID.getName());
		String keyUpgradeSkillCompleteTime = UserRace.generatorFieldKey(userRaceId, Entity.UserRace.UPGRADESKILLCOMPLETETIME.getName());

		Jedis slaveClient = null;
		try {
			slaveClient = createSlaveClient();
			List<String> mget = slaveClient.mget(keyUserId, keyRaceId, keyNickName, keyLastLoginTime, keyUpgradeSkillCid, keyUpgradeSkillCompleteTime,
					keyCampId);

			if (CollectionUtils.isNotEmpty(mget)) {
				Integer index = 0;
				userRace.setUserId(Long.parseLong(mget.get(index++)));
				userRace.setRaceId(Integer.parseInt(mget.get(index++)));
				userRace.setNickName(mget.get(index++));
				userRace.setLastLoginTime(Long.parseLong(mget.get(index++)));
				String upgradeSkillCid = mget.get(index++);
				if (StringUtils.isNotEmpty(upgradeSkillCid)) {
					userRace.setUpgradeSkillCid(Integer.parseInt(upgradeSkillCid));
				}
				String upgradeSkillCompleteTime = mget.get(index++);
				if (StringUtils.isNotEmpty(upgradeSkillCompleteTime)) {
					userRace.setUpgradeSkillCompleteTime(Long.parseLong(upgradeSkillCompleteTime));
				}
				String campId = mget.get(index++);
				if (StringUtils.isNotEmpty(campId))
					userRace.setCampCid(Integer.parseInt(campId));
			}
			userRace.setId(userRaceId);
			userRace.setSkillShop(slaveClient.smembers(keySkillShop));
		} finally {
			if (slaveClient != null) {
				jedisPoolSlave.returnResource(slaveClient);
			}
		}
		return userRace;
	}

	public void addListCollection(String value) throws DBException {
		super.addListCollection(Entity.PLAYER, value);
	}

	public Set<String> getAllOfflineUserRaceIds() throws DBException {
		try {
			long now = System.currentTimeMillis();
			double max = now - GameUtil.OFFLINETIME;
			return RSortedset.zrangebyScore(Entity.LASTOPERATETIME.getName(), Double.MIN_VALUE, max);
		} catch (RedisException e) {
			throw new DBException(e.getMessage());
		}
	}

	public Set<String> getSkillShop(Long userRaceId) throws DBException {
		String keySkillShop = UserRace.generatorFieldKey(userRaceId, Entity.UserRace.SKILLSHOP.getName());
		try {
			return RSet.smembers(keySkillShop);
		} catch (RedisException e) {
			throw new DBException(e.getMessage());
		}
	}

	public void removeSkillIdFromSkillShop(Long userRaceId, String skillId) throws DBException {
		try {
			String keySkillShop = UserRace.generatorFieldKey(userRaceId, Entity.UserRace.SKILLSHOP.getName());
			RSet.srem(keySkillShop, skillId);
		} catch (RedisException e) {
			throw new DBException(e.getMessage());
		}
	}

	public void addSkillId2SkillShop(Long userRaceId, String skillId) throws DBException {
		try {
			String keySkillShop = UserRace.generatorFieldKey(userRaceId, Entity.UserRace.SKILLSHOP.getName());
			RSet.sadd(keySkillShop, skillId);
		} catch (RedisException e) {
			throw new DBException(e.getMessage());
		}
	}

	/**
	 * 88 * 88
	 *
	 * @return
	 */
	public char[][] getBuildingSpaceInfo(Long userRaceId) {
		String keyBuildingSpaceInfo = UserRace.generatorFieldKey(userRaceId, Entity.UserRace.BUILDINGSPACEINFO.getName());
		String buildingSpaceInfoValue = RString.get(keyBuildingSpaceInfo);
		return GameUtil.transferString2Char(buildingSpaceInfoValue);
	}

	public void setBuildingSpaceInfo(Long userRaceId, char[][] c) throws DBException {
		try {
			String keyBuildingSpaceInfo = UserRace.generatorFieldKey(userRaceId, Entity.UserRace.BUILDINGSPACEINFO.getName());
			String buildingSpaceInfoValue = GameUtil.transferChar2String(c);
			RString.set(keyBuildingSpaceInfo, buildingSpaceInfoValue);
		} catch (RedisException e) {
			throw new DBException(e.getMessage());
		}
	}

	public void updateLastOperateTime(Long userRaceId) throws DBException {
		Long now = System.currentTimeMillis();
		try {
			RSortedset.zadd(Entity.LASTOPERATETIME.getName(), now.doubleValue(), String.valueOf(userRaceId));
		} catch (RedisException e) {
			throw new DBException(e.getMessage());
		}
	}

	public Double getLastOperateTime(Long userRaceId) throws DBException {
		try {
			return RSortedset.zscore(Entity.LASTOPERATETIME.getName(), String.valueOf(userRaceId));
		} catch (RedisException e) {
			throw new DBException(e.getMessage());
		}
	}

	public void updateUserRaceFightStatus(Long userRaceId, boolean isInFight) throws DBException {
		try {
			Integer value = isInFight ? 1 : 0;
			String keyIsInFight = UserRace.generatorFieldKey(userRaceId, Entity.UserRace.ISINFIGHT.getName());
			RString.set(keyIsInFight, String.valueOf(value), GameUtil.FIGHTLASTTIME);
		} catch (RedisException e) {
			throw new DBException(e.getMessage());
		}
	}

	public boolean isUserRaceInFight(Long userRaceId) throws DBException {
		try {
			String keyIsInFight = UserRace.generatorFieldKey(userRaceId, Entity.UserRace.ISINFIGHT.getName());
			String value = RString.get(keyIsInFight);
			if (value != null) {
				int valueInt = Integer.parseInt(value);
				return valueInt == 1;
			}
		} catch (RedisException e) {
			throw new DBException(e.getMessage());
		}
		return false;
	}

	public void updateUserRaceFightOpponent(Long userRaceId, Long opponentUserRaceId) throws DBException {
		try {
			String keyFightOpponent = UserRace.generatorFieldKey(userRaceId, Entity.UserRace.FIGHTOPPONENT.getName());
			RString.set(keyFightOpponent, String.valueOf(opponentUserRaceId), GameUtil.FIGHTLASTTIME);
		} catch (RedisException e) {
			throw new DBException(e.getMessage());
		}
	}

	public Long getUserRaceFightOpponent(Long userRaceId) throws DBException {
		try {
			String keyFightOpponent = UserRace.generatorFieldKey(userRaceId, Entity.UserRace.FIGHTOPPONENT.getName());
			String value = RString.get(keyFightOpponent);
			if (value != null) {
				return Long.valueOf(value);
			}
			return null;
		} catch (RedisException e) {
			throw new DBException(e.getMessage());
		}
	}

}