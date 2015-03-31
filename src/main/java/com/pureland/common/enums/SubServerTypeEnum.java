package com.pureland.common.enums;

public enum SubServerTypeEnum {
	None,Wall,Army,Laboratory,GTower,Federal,Eskill,WorkHouse,Oil,CTower,Bsoldier,Vault,Gold,Research,TAsoldier,Bskill,TDsoldier,HTower,TArmy,TJsoldier,TBsoldier,Medal,FTower,Factory,Cskill,THsoldier,Isoldier,Mine,ATower,TEsoldier,Shield,Dsoldier,TIsoldier,Market,Gsoldier,Esoldier,Base,Warehouse,Jsoldier,Fsoldier,Csoldier,BTower,FlyMineB,DTower,Worker,ETower,Hero,TFsoldier,Fskill,FlyMineA,Barracks,Diamond,Monument,TKsoldier,BigBomb,Askill,Nenergy,TrapA,PVE,TCsoldier,Hsoldier,TGsoldier,Dskill,NewJar,Asoldier,Crown;
	
	public static SubServerTypeEnum getSubServerTypeEnumById(int index) {
		return SubServerTypeEnum.values()[index];
	}
	
	public static SubServerTypeEnum getSubServerTypeEnumByName(String name){
		return SubServerTypeEnum.valueOf(name);
	}

}
