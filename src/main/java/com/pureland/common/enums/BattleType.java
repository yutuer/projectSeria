package com.pureland.common.enums;

public enum BattleType implements BaseEnum {
	ATTACK(new Long(1), "attack"),
	DEFEND(new Long(2), "defend");
	
	private Long id;
	private String code;
	private String name;
	private String label;
	
	private BattleType(Long id, String name) {
		this.id = id;
		this.name = name;
	}
	
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}



	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}



	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}



	/**
	 * @param label the label to set
	 */
	public void setLabel(String label) {
		this.label = label;
	}



	@Override
	public Long getId() {
		// TODO Auto-generated method stub
		return this.id;
	}

	@Override
	public String getCode() {
		// TODO Auto-generated method stub
		return this.code;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return this.name;
	}

	@Override
	public String getLabel() {
		// TODO Auto-generated method stub
		return this.label;
	}
	
	public static BattleType getBattleTypeById(Long id) {
		for(BattleType battleType : BattleType.values()) {
			if(battleType.getId().equals(id)) {
				return battleType;
			}
		}
		return null;
	}

}
