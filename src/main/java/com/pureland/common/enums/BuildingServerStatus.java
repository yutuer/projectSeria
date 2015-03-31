package com.pureland.common.enums;

import com.pureland.common.protocal.vo.BuildingVOProtocal.BuildingVO.BuildingStatus;

public enum BuildingServerStatus implements BaseEnum {
	ON(new Long(1), "On"),
	CONSTRUCT(new Long(2), "Construct"),
	UPGRADE(new Long(3), "Upgrade");
	
	private Long id;
	private String code;
	private String name;
	private String label;
	

	private BuildingServerStatus(Long id, String name) {
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
	
	public static BuildingServerStatus getBuildingServerStatusById(Long id) {
		for(BuildingServerStatus status : BuildingServerStatus.values()) {
			if(status.getId().equals(id))
				return status;
		}
		return null;
	}
	
	public static BuildingStatus getBuildingStatus(BuildingServerStatus status) {
		for(BuildingStatus buildingStatus : BuildingStatus.values()) {
			if(buildingStatus.toString().equals(status.getName())) {
				return buildingStatus;
			}
		}
		return null;
	}

}
