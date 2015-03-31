package com.pureland.common.db.data.battle;

import java.util.List;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.pureland.common.enums.BattleType;
import com.pureland.common.enums.Entity;
import com.pureland.common.util.DataObject;
/**
 * 
 * @author qinpeirong
 *
 */
public class ArmyConsume extends DataObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -54844363387452752L;
	private Long id;
	private Long userRaceId;
	private Long battleId;
	private BattleType battleType;
	private Integer cid;
	private Integer amount;
	
	public static String generatorFieldKey(Long id, String field) {
		return generatorFieldKey(Entity.ARMYCONSUME, id, field);
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
	 * @return the cid
	 */
	public Integer getCid() {
		return cid;
	}
	/**
	 * @param cid the cid to set
	 */
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	/**
	 * @return the amount
	 */
	public Integer getAmount() {
		return amount;
	}
	/**
	 * @param amount the amount to set
	 */
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	
	public static List<ArmyConsume> updateArmyConsume(final Long battleId, final BattleType battleType, List<ArmyConsume> armyConsumes) {
		return Lists.transform(armyConsumes, new Function<ArmyConsume, ArmyConsume>() {

			@Override
			public ArmyConsume apply(ArmyConsume armyConsume) {
				armyConsume.setBattleId(battleId);
				armyConsume.setBattleType(battleType);
				return armyConsume;
			}
		});
	}
	
	
}
