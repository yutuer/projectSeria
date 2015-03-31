package com.pureland.common.db.dao.redis.battle;

import java.util.Set;

import com.pureland.common.enums.ResourceServerTypeEnum;
import org.apache.commons.lang3.StringUtils;

import com.pureland.common.component.cache.api.RString;
import com.pureland.common.component.cache.error.RedisException;
import com.pureland.common.db.dao.redis.RedisDAO;
import com.pureland.common.db.data.battle.ResourceRecord;
import com.pureland.common.db.error.DBException;
import com.pureland.common.enums.BattleType;
import com.pureland.common.enums.Entity;

/**
 *
 * @author qinpeirong
 *
 */
public class ResourceRecordDAO extends RedisDAO {

	public Long addResourceRecord(ResourceRecord record) throws DBException {
		Long id = record.getId();
		try {
			if(id == null) {
				id = RString.generator(Entity.RESOURCERECORD.getName());
			}

			String keyUserRaceId = ResourceRecord.generatorFieldKey(id, Entity.ResourceRecord.USERRACEID.getName());
			String keyBattleId = ResourceRecord.generatorFieldKey(id, Entity.ResourceRecord.BATTLEID.getName());
			String keyResourceServerType = ResourceRecord.generatorFieldKey(id, Entity.ResourceRecord.RESOURCESERVERTYPE.getName());
			String keyCount = ResourceRecord.generatorFieldKey(id, Entity.ResourceRecord.COUNT.getName());

			RString.set(keyUserRaceId, String.valueOf(record.getUserRaceId()));
			RString.set(keyBattleId, String.valueOf(record.getBattleId()));
			RString.set(keyResourceServerType, String.valueOf(record.getResourceType().ordinal()));
			RString.set(keyCount, String.valueOf(record.getCount()));

			addSetCollection(record.getUserRaceId(), record.getBattleId(), record.getBattleType(), String.valueOf(id));

		} catch (RedisException e) {
			throw new DBException(e.getMessage());
		}
		return id;
	}

	public ResourceRecord getResourceRecord(Long id) throws DBException {
		ResourceRecord record = new ResourceRecord();
		try {
			String keyUserRaceId = ResourceRecord.generatorFieldKey(id, Entity.ResourceRecord.USERRACEID.getName());
			String keyBattleId = ResourceRecord.generatorFieldKey(id, Entity.ResourceRecord.BATTLEID.getName());
			String keyResourceServerType = ResourceRecord.generatorFieldKey(id, Entity.ResourceRecord.RESOURCESERVERTYPE.getName());
			String keyCount = ResourceRecord.generatorFieldKey(id, Entity.ResourceRecord.COUNT.getName());

			record.setUserRaceId(Long.parseLong(RString.get(keyUserRaceId)));
			record.setBattleId(Long.parseLong(RString.get(keyBattleId)));
			String resourceServerTypeId = RString.get(keyResourceServerType);
			if(StringUtils.isNotEmpty(resourceServerTypeId)) {
				ResourceServerTypeEnum resourceServerType = ResourceServerTypeEnum.getResourceServerTypeEnumById(Integer.parseInt(resourceServerTypeId));
				record.setResourceType(resourceServerType);
			}
			record.setCount(Integer.parseInt(RString.get(keyCount)));

		} catch(RedisException e) {
			throw new DBException(e.getMessage());
		}
		return record;
	}


	public void addSetCollection(Long userRaceId, Long battleId, BattleType battleType, String value) throws DBException {
		StringBuffer filed = new StringBuffer();
		filed.append(userRaceId)
		     .append(Entity.SEPARATOR)
		     .append(battleId)
		     .append(Entity.SEPARATOR)
		     .append(battleType.getId());
		super.addSetCollection(Entity.RESOURCERECORD, filed.toString(), value);
	}

	public Set<String> getSetCollection(Long userRaceId, Long battleId, BattleType battleType) throws DBException {
		StringBuffer filed = new StringBuffer();
		filed.append(userRaceId)
		     .append(Entity.SEPARATOR)
		     .append(battleId)
		     .append(Entity.SEPARATOR)
		     .append(battleType.getId());
		return super.getSetCollection(Entity.RESOURCERECORD, filed.toString());
	}
}
