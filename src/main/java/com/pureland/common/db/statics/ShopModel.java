package com.pureland.common.db.statics;

public class ShopModel{
		//序列
	private String orderId;
	//种族
	private String raceType;
	//商店类型
	private String shopType;
	//商店名字
	private String shopName;
	//物品名字
	private String itemName;
	//商店排序
	private Integer shopOrder;
	//商店图标icon
	private String shopIcon;
	//物品ID
	private Integer baseId;
	//物品排序
	private Integer itemOrder;


	public ShopModel(){}

	public ShopModel(String orderId,String raceType,String shopType,String shopName,String itemName,Integer shopOrder,String shopIcon,Integer baseId,Integer itemOrder){
			this.orderId=orderId;
	this.raceType=raceType;
	this.shopType=shopType;
	this.shopName=shopName;
	this.itemName=itemName;
	this.shopOrder=shopOrder;
	this.shopIcon=shopIcon;
	this.baseId=baseId;
	this.itemOrder=itemOrder;

	}

	public ShopModel(String orderId,String raceType,String shopType,String shopName,String itemName,String shopOrder,String shopIcon,String baseId,String itemOrder){
		this(orderId,raceType,shopType,shopName,itemName,Integer.parseInt(shopOrder),shopIcon,Integer.parseInt(baseId),Integer.parseInt(itemOrder));
	}
	
	public String getOrderId(){
 	return orderId;
}
public String getRaceType(){
 	return raceType;
}
public String getShopType(){
 	return shopType;
}
public String getShopName(){
 	return shopName;
}
public String getItemName(){
 	return itemName;
}
public Integer getShopOrder(){
 	return shopOrder;
}
public String getShopIcon(){
 	return shopIcon;
}
public Integer getBaseId(){
 	return baseId;
}
public Integer getItemOrder(){
 	return itemOrder;
}

	
	public void setOrderId(String orderId){
 	this.orderId=orderId;
}
public void setRaceType(String raceType){
 	this.raceType=raceType;
}
public void setShopType(String shopType){
 	this.shopType=shopType;
}
public void setShopName(String shopName){
 	this.shopName=shopName;
}
public void setItemName(String itemName){
 	this.itemName=itemName;
}
public void setShopOrder(Integer shopOrder){
 	this.shopOrder=shopOrder;
}
public void setShopIcon(String shopIcon){
 	this.shopIcon=shopIcon;
}
public void setBaseId(Integer baseId){
 	this.baseId=baseId;
}
public void setItemOrder(Integer itemOrder){
 	this.itemOrder=itemOrder;
}

	
	public String buildAddSql() {
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append("insert into `pureland`.`shop_model`(`order_id`,`race_type`,`shop_type`,`shop_name`,`item_name`,`shop_order`,`shop_icon`,`base_id`,`item_order`) values('").append(this.getOrderId()).append("','").append(this.getRaceType()).append("','").append(this.getShopType()).append("','").append(this.getShopName()).append("','").append(this.getItemName()).append("',").append(this.getShopOrder()).append(",'").append(this.getShopIcon()).append("',").append(this.getBaseId()).append(",").append(this.getItemOrder()).append(")");
		return sqlBuffer.toString();
	}
	
	@Override
	public String toString() {
		return "ShopModel[orderId="+orderId+", raceType="+raceType+", shopType="+shopType+", shopName="+shopName+", itemName="+itemName+", shopOrder="+shopOrder+", shopIcon="+shopIcon+", baseId="+baseId+", itemOrder="+itemOrder+"]";
	}
		
}
