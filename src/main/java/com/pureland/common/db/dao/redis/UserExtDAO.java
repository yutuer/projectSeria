package com.pureland.common.db.dao.redis;

import java.util.Set;

import com.pureland.common.component.cache.api.RString;
import com.pureland.common.component.cache.error.RedisException;
import com.pureland.common.db.data.UserExt;
import com.pureland.common.db.error.DBException;
import com.pureland.common.enums.Entity;
import com.pureland.common.log.PurelandLog;

public class UserExtDAO extends RedisDAO {

	private String TAG = PurelandLog.getClassTag(UserExtDAO.class);

	public Long addUserExt(UserExt userExt) throws DBException {
		Long extId = null;
		try {
			extId = RString.generator(Entity.USEREXT.getName());

			Long userRaceId = userExt.getUserRaceId();
			String keyId = UserExt.generatorIdKey(String.valueOf(userRaceId));
			String keyUserRaceExtId = UserExt.generatorFieldKey(extId, Entity.UserExt.USERRACEID.getName());
			String keyLevel = UserExt.generatorFieldKey(extId, Entity.UserExt.LEVEL.getName());
			String keyExperience = UserExt.generatorFieldKey(extId, Entity.UserExt.EXPERIENCE.getName());
			String keyCrown = UserExt.generatorFieldKey(extId, Entity.UserExt.CROWN.getName());

			RString.set(keyId, String.valueOf(extId));
			RString.set(keyUserRaceExtId, String.valueOf(userRaceId));
			RString.set(keyLevel, String.valueOf(userExt.getLevel()));
			RString.set(keyExperience, String.valueOf(userExt.getExperience()));
			RString.set(keyCrown, String.valueOf(userExt.getCrown()));

		} catch (RedisException e) {
			throw new DBException(e.getMessage());
		}

		return extId;
	}

	public UserExt getUserExt(Long extId) throws DBException {
		UserExt userExt = new UserExt();
		try {
			String keyUserRaceExtId = UserExt.generatorFieldKey(extId, Entity.UserExt.USERRACEID.getName());
			String keyLevel = UserExt.generatorFieldKey(extId, Entity.UserExt.LEVEL.getName());
			String keyExperience = UserExt.generatorFieldKey(extId, Entity.UserExt.EXPERIENCE.getName());
			String keyCrown = UserExt.generatorFieldKey(extId, Entity.UserExt.CROWN.getName());

			userExt.setId(extId);
			userExt.setUserRaceId(Long.parseLong(RString.get(keyUserRaceExtId)));
			userExt.setLevel(Integer.parseInt(RString.get(keyLevel)));
			userExt.setExperience(Integer.parseInt(RString.get(keyExperience)));
			userExt.setCrown(Integer.parseInt(RString.get(keyCrown)));
		} catch (RedisException e) {
			throw new DBException(e.getMessage());
		}
		return userExt;
	}

	public void updateUserExt(UserExt userExt) throws DBException {
		Long extId = userExt.getId();
		Integer experience = userExt.getExperience();
		Integer level = userExt.getLevel();
		Integer crown = userExt.getCrown();

		if (extId == null)
			throw new DBException("can not update userext because of extid is null");

		try {
			if (experience != null) {
				String keyExperience = UserExt.generatorFieldKey(extId, Entity.UserExt.EXPERIENCE.getName());
				RString.set(keyExperience, String.valueOf(experience));
			}
			if (level != null) {
				String keyLevel = UserExt.generatorFieldKey(extId, Entity.UserExt.LEVEL.getName());
				RString.set(keyLevel, String.valueOf(level));
			}
			if (crown != null) {
				String keyCrown = UserExt.generatorFieldKey(extId, Entity.UserExt.CROWN.getName());
				RString.set(keyCrown, String.valueOf(crown));
			}

		} catch (RedisException e) {
			throw new DBException(e.getMessage());
		}
	}

	public void addSetCollection(Long userRaceId, String value) throws DBException {
		super.addSetCollection(Entity.USEREXT, String.valueOf(userRaceId), value);
	}

	public Set<String> getSetCollection(Long userRaceId) throws DBException {
		return super.getSetCollection(Entity.USEREXT, String.valueOf(userRaceId));
	}
}
