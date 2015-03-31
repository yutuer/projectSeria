package com.pureland.common.db.statics;

public class BuildingLimitModel{
		//id
	private Integer id;
	//基地id
	private Integer baseId;
	//建筑id
	private Integer buildingBaseId;
	//建筑最大数量
	private Integer buildingCount;


	public BuildingLimitModel(){}

	public BuildingLimitModel(Integer id,Integer baseId,Integer buildingBaseId,Integer buildingCount){
			this.id=id;
	this.baseId=baseId;
	this.buildingBaseId=buildingBaseId;
	this.buildingCount=buildingCount;

	}

	public BuildingLimitModel(String id,String baseId,String buildingBaseId,String buildingCount){
		this(Integer.parseInt(id),Integer.parseInt(baseId),Integer.parseInt(buildingBaseId),Integer.parseInt(buildingCount));
	}
	
	public Integer getId(){
 	return id;
}
public Integer getBaseId(){
 	return baseId;
}
public Integer getBuildingBaseId(){
 	return buildingBaseId;
}
public Integer getBuildingCount(){
 	return buildingCount;
}

	
	public void setId(Integer id){
 	this.id=id;
}
public void setBaseId(Integer baseId){
 	this.baseId=baseId;
}
public void setBuildingBaseId(Integer buildingBaseId){
 	this.buildingBaseId=buildingBaseId;
}
public void setBuildingCount(Integer buildingCount){
 	this.buildingCount=buildingCount;
}

	
	public String buildAddSql() {
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append("insert into `pureland`.`building_limit_model`(`id`,`base_id`,`building_base_id`,`building_count`) values(").append(this.getId()).append(",").append(this.getBaseId()).append(",").append(this.getBuildingBaseId()).append(",").append(this.getBuildingCount()).append(")");
		return sqlBuffer.toString();
	}
	
	@Override
	public String toString() {
		return "BuildingLimitModel[id="+id+", baseId="+baseId+", buildingBaseId="+buildingBaseId+", buildingCount="+buildingCount+"]";
	}
		
}
