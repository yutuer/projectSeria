package com.pureland.common.db.statics;

public class ArmoryModel{
		//id
	private Integer id;
	//种族
	private String raceType;
	//类型(1兵种,2技能)
	private Integer buildType;
	//baseId
	private Integer baseId;


	public ArmoryModel(){}

	public ArmoryModel(Integer id,String raceType,Integer buildType,Integer baseId){
			this.id=id;
	this.raceType=raceType;
	this.buildType=buildType;
	this.baseId=baseId;

	}

	public ArmoryModel(String id,String raceType,String buildType,String baseId){
		this(Integer.parseInt(id),raceType,Integer.parseInt(buildType),Integer.parseInt(baseId));
	}
	
	public Integer getId(){
 	return id;
}
public String getRaceType(){
 	return raceType;
}
public Integer getBuildType(){
 	return buildType;
}
public Integer getBaseId(){
 	return baseId;
}

	
	public void setId(Integer id){
 	this.id=id;
}
public void setRaceType(String raceType){
 	this.raceType=raceType;
}
public void setBuildType(Integer buildType){
 	this.buildType=buildType;
}
public void setBaseId(Integer baseId){
 	this.baseId=baseId;
}

	
	public String buildAddSql() {
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append("insert into `pureland`.`armory_model`(`id`,`race_type`,`build_type`,`base_id`) values(").append(this.getId()).append(",'").append(this.getRaceType()).append("',").append(this.getBuildType()).append(",").append(this.getBaseId()).append(")");
		return sqlBuffer.toString();
	}
	
	@Override
	public String toString() {
		return "ArmoryModel[id="+id+", raceType="+raceType+", buildType="+buildType+", baseId="+baseId+"]";
	}
		
}
