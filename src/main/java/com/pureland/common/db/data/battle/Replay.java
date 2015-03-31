package com.pureland.common.db.data.battle;

import com.pureland.common.enums.BattleType;
import com.pureland.common.enums.Entity;
import com.pureland.common.util.DataObject;
/**
 * 
 * @author qinpeirong
 *
 */
public class Replay extends DataObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 939625076944013822L;
	private Long id;
	private Long userRaceId;
	private Long battleId;
	private BattleType battleType;
	private byte[] replayByte;
	
	public static String generatorIdKey(String singleMark) {
		return generatorIdKey(Entity.USER, Entity.User.ID.getName(), singleMark);
	}
	
	public static String generatorFieldKey(Long id, String field) {
		return generatorFieldKey(Entity.REPLAY, id, field);
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
	 * @return the replayByte
	 */
	public byte[] getReplayByte() {
		return replayByte;
	}

	/**
	 * @param replayByte the replayByte to set
	 */
	public void setReplayByte(byte[] replayByte) {
		this.replayByte = replayByte;
	}
    
	
	
}
