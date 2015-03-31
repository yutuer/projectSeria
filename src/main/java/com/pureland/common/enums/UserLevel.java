package com.pureland.common.enums;

/**
 * 
 * @author qinpeirong
 *
 */
public enum UserLevel implements BaseEnum {
	LEVEL_1(new Long(1), "the first level"),
	LEVEL_2(new Long(2), "the second level"),
	LEVEL_3(new Long(3), "the third level");
	
	private Long id;
	private String code;
	private String name;
	private String label;
	
	

	private UserLevel(Long id, String name) {
		this.id = id;
		this.name = name;
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
	
	public static UserLevel getUserLevelById(Long id) {
		for(UserLevel level : UserLevel.values()) {
			if(level.getId().equals(id))
				return level;
		}
		return null;
	}

}
