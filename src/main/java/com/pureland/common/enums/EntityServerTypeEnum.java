package com.pureland.common.enums;

public enum EntityServerTypeEnum {
	None,Wall,Resource,Tower,Actor,OtherBuilding,Skill,Trap,ActorFly,Functional;
	
	public static EntityServerTypeEnum getEntityServerTypeEnumById(int index) {
		return EntityServerTypeEnum.values()[index];
	}
	
	public static EntityServerTypeEnum getEntityServerTypeEnumByName(String name){
		return EntityServerTypeEnum.valueOf(name);
	}

}
