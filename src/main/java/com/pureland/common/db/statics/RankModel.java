package com.pureland.common.db.statics;

public class RankModel{
		//名字
	private String name;
	//类型
	private String type;
	//数量
	private Integer exp;
	//资源
	private String icon;
	//等级奖励星币
	private Integer rewardGold;
	//等级奖励太阳水
	private Integer rewardOil;


	public RankModel(){}

	public RankModel(String name,String type,Integer exp,String icon,Integer rewardGold,Integer rewardOil){
			this.name=name;
	this.type=type;
	this.exp=exp;
	this.icon=icon;
	this.rewardGold=rewardGold;
	this.rewardOil=rewardOil;

	}

	public RankModel(String name,String type,String exp,String icon,String rewardGold,String rewardOil){
		this(name,type,Integer.parseInt(exp),icon,Integer.parseInt(rewardGold),Integer.parseInt(rewardOil));
	}
	
	public String getName(){
 	return name;
}
public String getType(){
 	return type;
}
public Integer getExp(){
 	return exp;
}
public String getIcon(){
 	return icon;
}
public Integer getRewardGold(){
 	return rewardGold;
}
public Integer getRewardOil(){
 	return rewardOil;
}

	
	public void setName(String name){
 	this.name=name;
}
public void setType(String type){
 	this.type=type;
}
public void setExp(Integer exp){
 	this.exp=exp;
}
public void setIcon(String icon){
 	this.icon=icon;
}
public void setRewardGold(Integer rewardGold){
 	this.rewardGold=rewardGold;
}
public void setRewardOil(Integer rewardOil){
 	this.rewardOil=rewardOil;
}

	
	public String buildAddSql() {
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append("insert into `pureland`.`rank_model`(`name`,`type`,`exp`,`icon`,`reward_gold`,`reward_oil`) values('").append(this.getName()).append("','").append(this.getType()).append("',").append(this.getExp()).append(",'").append(this.getIcon()).append("',").append(this.getRewardGold()).append(",").append(this.getRewardOil()).append(")");
		return sqlBuffer.toString();
	}
	
	@Override
	public String toString() {
		return "RankModel[name="+name+", type="+type+", exp="+exp+", icon="+icon+", rewardGold="+rewardGold+", rewardOil="+rewardOil+"]";
	}
		
}
