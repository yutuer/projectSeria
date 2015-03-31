package com.pureland.common.db.data;

import org.apache.commons.lang3.StringUtils;

import com.pureland.common.component.cache.api.RString;
import com.pureland.common.component.cache.error.RedisException;
import com.pureland.common.enums.Entity;
import com.pureland.common.util.DataObject;

public class ArmyExp extends DataObject {

	private static final long serialVersionUID = 5767525806975762498L;
	private Long id;
	private Long userRaceId;
	private Integer cid;
	private Integer exp;

	public static String generatorArmyExpKeyId(Long userRaceId, Integer cid) {
		return generatorIdKey(Entity.ARMYEXP, Entity.ArmyExp.ID.getName(), StringUtils.join(new String[] { String.valueOf(userRaceId), String.valueOf(cid) }));
	}

	public static Long generatorArmyExpId() throws RedisException {
		return RString.generator(Entity.ARMYEXP.getName());
	}

	public static String generatorFieldKey(Long armyExpId, String field) {
		return generatorFieldKey(Entity.ARMYEXP, field, String.valueOf(armyExpId));
	}

	public Long getUserRaceId() {
		return userRaceId;
	}

	public void setUserRaceId(Long userRaceId) {
		this.userRaceId = userRaceId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getCid() {
		return cid;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}

	public Integer getExp() {
		return exp;
	}

	public void setExp(Integer exp) {
		this.exp = exp;
	}
}
