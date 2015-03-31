package com.pureland.common.db.statics;

public class ExploreModel{
		//探索舰等级/区域
	private Integer section;
	//1级
	private Integer lv1;
	//2级
	private Integer lv2;
	//3级
	private Integer lv3;
	//4级
	private Integer lv4;
	//5级
	private Integer lv5;
	//6级
	private Integer lv6;
	//7级
	private Integer lv7;
	//8级
	private Integer lv8;
	//9级
	private Integer lv9;
	//10级
	private Integer lv10;
	//星币
	private Integer gold;
	//太阳水
	private Integer oil;
	//生命能源
	private Integer newOil;
	//钻石
	private Integer diamond;


	public ExploreModel(){}

	public ExploreModel(Integer section,Integer lv1,Integer lv2,Integer lv3,Integer lv4,Integer lv5,Integer lv6,Integer lv7,Integer lv8,Integer lv9,Integer lv10,Integer gold,Integer oil,Integer newOil,Integer diamond){
			this.section=section;
	this.lv1=lv1;
	this.lv2=lv2;
	this.lv3=lv3;
	this.lv4=lv4;
	this.lv5=lv5;
	this.lv6=lv6;
	this.lv7=lv7;
	this.lv8=lv8;
	this.lv9=lv9;
	this.lv10=lv10;
	this.gold=gold;
	this.oil=oil;
	this.newOil=newOil;
	this.diamond=diamond;

	}

	public ExploreModel(String section,String lv1,String lv2,String lv3,String lv4,String lv5,String lv6,String lv7,String lv8,String lv9,String lv10,String gold,String oil,String newOil,String diamond){
		this(Integer.parseInt(section),Integer.parseInt(lv1),Integer.parseInt(lv2),Integer.parseInt(lv3),Integer.parseInt(lv4),Integer.parseInt(lv5),Integer.parseInt(lv6),Integer.parseInt(lv7),Integer.parseInt(lv8),Integer.parseInt(lv9),Integer.parseInt(lv10),Integer.parseInt(gold),Integer.parseInt(oil),Integer.parseInt(newOil),Integer.parseInt(diamond));
	}
	
	public Integer getSection(){
 	return section;
}
public Integer getLv1(){
 	return lv1;
}
public Integer getLv2(){
 	return lv2;
}
public Integer getLv3(){
 	return lv3;
}
public Integer getLv4(){
 	return lv4;
}
public Integer getLv5(){
 	return lv5;
}
public Integer getLv6(){
 	return lv6;
}
public Integer getLv7(){
 	return lv7;
}
public Integer getLv8(){
 	return lv8;
}
public Integer getLv9(){
 	return lv9;
}
public Integer getLv10(){
 	return lv10;
}
public Integer getGold(){
 	return gold;
}
public Integer getOil(){
 	return oil;
}
public Integer getNewOil(){
 	return newOil;
}
public Integer getDiamond(){
 	return diamond;
}

	
	public void setSection(Integer section){
 	this.section=section;
}
public void setLv1(Integer lv1){
 	this.lv1=lv1;
}
public void setLv2(Integer lv2){
 	this.lv2=lv2;
}
public void setLv3(Integer lv3){
 	this.lv3=lv3;
}
public void setLv4(Integer lv4){
 	this.lv4=lv4;
}
public void setLv5(Integer lv5){
 	this.lv5=lv5;
}
public void setLv6(Integer lv6){
 	this.lv6=lv6;
}
public void setLv7(Integer lv7){
 	this.lv7=lv7;
}
public void setLv8(Integer lv8){
 	this.lv8=lv8;
}
public void setLv9(Integer lv9){
 	this.lv9=lv9;
}
public void setLv10(Integer lv10){
 	this.lv10=lv10;
}
public void setGold(Integer gold){
 	this.gold=gold;
}
public void setOil(Integer oil){
 	this.oil=oil;
}
public void setNewOil(Integer newOil){
 	this.newOil=newOil;
}
public void setDiamond(Integer diamond){
 	this.diamond=diamond;
}

	
	public String buildAddSql() {
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append("insert into `pureland`.`explore_model`(`section`,`lv1`,`lv2`,`lv3`,`lv4`,`lv5`,`lv6`,`lv7`,`lv8`,`lv9`,`lv10`,`gold`,`oil`,`new_oil`,`diamond`) values(").append(this.getSection()).append(",").append(this.getLv1()).append(",").append(this.getLv2()).append(",").append(this.getLv3()).append(",").append(this.getLv4()).append(",").append(this.getLv5()).append(",").append(this.getLv6()).append(",").append(this.getLv7()).append(",").append(this.getLv8()).append(",").append(this.getLv9()).append(",").append(this.getLv10()).append(",").append(this.getGold()).append(",").append(this.getOil()).append(",").append(this.getNewOil()).append(",").append(this.getDiamond()).append(")");
		return sqlBuffer.toString();
	}
	
	@Override
	public String toString() {
		return "ExploreModel[section="+section+", lv1="+lv1+", lv2="+lv2+", lv3="+lv3+", lv4="+lv4+", lv5="+lv5+", lv6="+lv6+", lv7="+lv7+", lv8="+lv8+", lv9="+lv9+", lv10="+lv10+", gold="+gold+", oil="+oil+", newOil="+newOil+", diamond="+diamond+"]";
	}
		
}
