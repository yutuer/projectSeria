package com.pureland.common.db.data.battle;

import java.util.List;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.pureland.common.enums.BattleType;
import com.pureland.common.enums.Entity;
import com.pureland.common.enums.ResourceServerTypeEnum;
import com.pureland.common.util.DataObject;
/**
 * 
 * @author qinpeirong
 *
 */
public class ResourceRecord extends DataObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3038733841306704665L;
	private Long id;
	private Long userRaceId;
	private Long battleId;
	private BattleType battleType;
	private ResourceServerTypeEnum resourceType;
	private Integer count;
	
	public static String generatorFieldKey(Long id, String field) {
		return generatorFieldKey(Entity.RESOURCERECORD, id, field);
	}
	
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * @return the userRaceId
	 */
	public Long getUserRaceId() {
		return userRaceId;
	}
	/**
	 * @param userRaceId the userRaceId to set
	 */
	public void setUserRaceId(Long userRaceId) {
		this.userRaceId = userRaceId;
	}
	/**
	 * @return the battleId
	 */
	public Long getBattleId() {
		return battleId;
	}
	/**
	 * @param battleId the battleId to set
	 */
	public void setBattleId(Long battleId) {
		this.battleId = battleId;
	}
	/**
	 * @return the battleType
	 */
	public BattleType getBattleType() {
		return battleType;
	}

	/**
	 * @param battleType the battleType to set
	 */
	public void setBattleType(BattleType battleType) {
		this.battleType = battleType;
	}

	/**
	 * @return the resourceType
	 */
	public ResourceServerTypeEnum getResourceType() {
		return resourceType;
	}
	/**
	 * @param resourceType the resourceType to set
	 */
	public void setResourceType(ResourceServerTypeEnum resourceType) {
		this.resourceType = resourceType;
	}
	/**
	 * @return the count
	 */
	public Integer getCount() {
		return count;
	}
	/**
	 * @param count the count to set
	 */
	public void setCount(Integer count) {
		this.count = count;
	}
	
	public static List<ResourceRecord> updateResourceRecord(final Long battleId, final BattleType battleType, List<ResourceRecord> resourceRecordes) {
		return Lists.transform(resourceRecordes, new Function<ResourceRecord, ResourceRecord>() {

			@Override
			public ResourceRecord apply(ResourceRecord resourceRecord) {
                resourceRecord.setBattleId(battleId);
				resourceRecord.setBattleType(battleType);
				return resourceRecord;
			}
		});
	}

	
}
