package com.pureland.common.db.statics;

public class EntityModel{
		//ID
	private Integer baseId;
	//升级后ID
	private Integer upgradeId;
	//种族
	private String raceType;
	//类别
	private String entityType;
	//性质类型
	private String subType;
	//资源名称
	private String nameForResource;
	//兵种名称显示用
	private String nameForView;
	//等级
	private Integer level;
	//攻击评估值
	private Integer attackWeight;
	//防御评估值
	private Integer defenseWeight;
	//解锁条件类型
	private String buildNeedType;
	//解锁生产条件等级
	private Integer buildNeedLevel ;
	//升级条件类型
	private String upgradeNeedType;
	//解锁升级条件等级
	private Integer upgradeNeedLevel;
	//生命
	private Integer hp;
	//费用类型
	private String costResourceType;
	//费用数量
	private Integer costResourceCount;
	//建造时间
	private Long buildTime;
	//攻击距离
	private Double range;
	//攻击盲区/半径格
	private Integer blindRange;
	//伤害间隔时间/秒
	private Double rate;
	//伤害
	private Integer damage;
	//伤害/秒（显示用）
	private Integer damageForView;
	//溅射范围/半径格
	private Integer splashRange;
	//溅射范围/角度
	private Integer attackAngle;
	//占地表空间/格
	private Integer tileSize;
	//优先目标类型
	private String targetType;
	//攻击那一类目标
	private String onlyAttackTargetType;
	//提取资源类型
	private String resourceType;
	//提取资源每秒
	private Double resourcePerSecond;
	//提取资源/小时显示用
	private Integer resourcePerSecondForView;
	//资源库存容量上限
	private Integer maxResourceStorage;
	//升级后产生经验值量
	private Integer buildExp;
	//军营空间大小
	private Integer spaceProvide;
	//生产队列长度
	private Integer queueSize;
	//提供工人数量
	private Integer workerProvide;
	//修复费用
	private Integer refillCostResourceCount;
	//是否瞄准目标
	private String aimTarget;
	//子弹速度
	private Integer bulletSpeed;
	//子弹类型
	private String bulletType;
	//目标个数
	private Integer numTarget;
	//子弹名称
	private String bulletName;
	//发射效果名字
	private String fireEffectName;
	//命中效果名字
	private String hitEffectName;
	//buff效果名字
	private String buffEffectName;
	//buff类别
	private String buffType;
	//buff伤害
	private Double buffDamage;
	//buff作用次数
	private Integer buffActiveTimes;
	//buff作用间隔
	private Double buffIntervalTime;
	//护甲值
	private Integer defense;
	//护甲值显示
	private Integer defenseForView;
	//克制名称
	private String additionDamageSubType;
	//伤害倍率
	private Integer additionDamageRatio;
	//训练费用类型
	private String trainCostResourceType;
	//训练费用数量
	private Integer trainCostResourceCount;
	//训练时间
	private Long trainTime;
	//移动速度/格
	private Double speed;
	//治疗量
	private Integer cure;
	//治疗量/秒（显示用）
	private Integer cureForView;
	//医疗目标识别范围/半径格
	private Integer cureRange;
	//占兵营空间/格
	private Integer spaceUse;
	//描述
	private String desc;


	public EntityModel(){}

	public EntityModel(Integer baseId,Integer upgradeId,String raceType,String entityType,String subType,String nameForResource,String nameForView,Integer level,Integer attackWeight,Integer defenseWeight,String buildNeedType,Integer buildNeedLevel ,String upgradeNeedType,Integer upgradeNeedLevel,Integer hp,String costResourceType,Integer costResourceCount,Long buildTime,Double range,Integer blindRange,Double rate,Integer damage,Integer damageForView,Integer splashRange,Integer attackAngle,Integer tileSize,String targetType,String onlyAttackTargetType,String resourceType,Double resourcePerSecond,Integer resourcePerSecondForView,Integer maxResourceStorage,Integer buildExp,Integer spaceProvide,Integer queueSize,Integer workerProvide,Integer refillCostResourceCount,String aimTarget,Integer bulletSpeed,String bulletType,Integer numTarget,String bulletName,String fireEffectName,String hitEffectName,String buffEffectName,String buffType,Double buffDamage,Integer buffActiveTimes,Double buffIntervalTime,Integer defense,Integer defenseForView,String additionDamageSubType,Integer additionDamageRatio,String trainCostResourceType,Integer trainCostResourceCount,Long trainTime,Double speed,Integer cure,Integer cureForView,Integer cureRange,Integer spaceUse,String desc){
			this.baseId=baseId;
	this.upgradeId=upgradeId;
	this.raceType=raceType;
	this.entityType=entityType;
	this.subType=subType;
	this.nameForResource=nameForResource;
	this.nameForView=nameForView;
	this.level=level;
	this.attackWeight=attackWeight;
	this.defenseWeight=defenseWeight;
	this.buildNeedType=buildNeedType;
	this.buildNeedLevel =buildNeedLevel ;
	this.upgradeNeedType=upgradeNeedType;
	this.upgradeNeedLevel=upgradeNeedLevel;
	this.hp=hp;
	this.costResourceType=costResourceType;
	this.costResourceCount=costResourceCount;
	this.buildTime=buildTime;
	this.range=range;
	this.blindRange=blindRange;
	this.rate=rate;
	this.damage=damage;
	this.damageForView=damageForView;
	this.splashRange=splashRange;
	this.attackAngle=attackAngle;
	this.tileSize=tileSize;
	this.targetType=targetType;
	this.onlyAttackTargetType=onlyAttackTargetType;
	this.resourceType=resourceType;
	this.resourcePerSecond=resourcePerSecond;
	this.resourcePerSecondForView=resourcePerSecondForView;
	this.maxResourceStorage=maxResourceStorage;
	this.buildExp=buildExp;
	this.spaceProvide=spaceProvide;
	this.queueSize=queueSize;
	this.workerProvide=workerProvide;
	this.refillCostResourceCount=refillCostResourceCount;
	this.aimTarget=aimTarget;
	this.bulletSpeed=bulletSpeed;
	this.bulletType=bulletType;
	this.numTarget=numTarget;
	this.bulletName=bulletName;
	this.fireEffectName=fireEffectName;
	this.hitEffectName=hitEffectName;
	this.buffEffectName=buffEffectName;
	this.buffType=buffType;
	this.buffDamage=buffDamage;
	this.buffActiveTimes=buffActiveTimes;
	this.buffIntervalTime=buffIntervalTime;
	this.defense=defense;
	this.defenseForView=defenseForView;
	this.additionDamageSubType=additionDamageSubType;
	this.additionDamageRatio=additionDamageRatio;
	this.trainCostResourceType=trainCostResourceType;
	this.trainCostResourceCount=trainCostResourceCount;
	this.trainTime=trainTime;
	this.speed=speed;
	this.cure=cure;
	this.cureForView=cureForView;
	this.cureRange=cureRange;
	this.spaceUse=spaceUse;
	this.desc=desc;

	}

	public EntityModel(String baseId,String upgradeId,String raceType,String entityType,String subType,String nameForResource,String nameForView,String level,String attackWeight,String defenseWeight,String buildNeedType,String buildNeedLevel ,String upgradeNeedType,String upgradeNeedLevel,String hp,String costResourceType,String costResourceCount,String buildTime,String range,String blindRange,String rate,String damage,String damageForView,String splashRange,String attackAngle,String tileSize,String targetType,String onlyAttackTargetType,String resourceType,String resourcePerSecond,String resourcePerSecondForView,String maxResourceStorage,String buildExp,String spaceProvide,String queueSize,String workerProvide,String refillCostResourceCount,String aimTarget,String bulletSpeed,String bulletType,String numTarget,String bulletName,String fireEffectName,String hitEffectName,String buffEffectName,String buffType,String buffDamage,String buffActiveTimes,String buffIntervalTime,String defense,String defenseForView,String additionDamageSubType,String additionDamageRatio,String trainCostResourceType,String trainCostResourceCount,String trainTime,String speed,String cure,String cureForView,String cureRange,String spaceUse,String desc){
		this(Integer.parseInt(baseId),Integer.parseInt(upgradeId),raceType,entityType,subType,nameForResource,nameForView,Integer.parseInt(level),Integer.parseInt(attackWeight),Integer.parseInt(defenseWeight),buildNeedType,Integer.parseInt(buildNeedLevel ),upgradeNeedType,Integer.parseInt(upgradeNeedLevel),Integer.parseInt(hp),costResourceType,Integer.parseInt(costResourceCount),Long.parseLong(buildTime),Double.parseDouble(range),Integer.parseInt(blindRange),Double.parseDouble(rate),Integer.parseInt(damage),Integer.parseInt(damageForView),Integer.parseInt(splashRange),Integer.parseInt(attackAngle),Integer.parseInt(tileSize),targetType,onlyAttackTargetType,resourceType,Double.parseDouble(resourcePerSecond),Integer.parseInt(resourcePerSecondForView),Integer.parseInt(maxResourceStorage),Integer.parseInt(buildExp),Integer.parseInt(spaceProvide),Integer.parseInt(queueSize),Integer.parseInt(workerProvide),Integer.parseInt(refillCostResourceCount),aimTarget,Integer.parseInt(bulletSpeed),bulletType,Integer.parseInt(numTarget),bulletName,fireEffectName,hitEffectName,buffEffectName,buffType,Double.parseDouble(buffDamage),Integer.parseInt(buffActiveTimes),Double.parseDouble(buffIntervalTime),Integer.parseInt(defense),Integer.parseInt(defenseForView),additionDamageSubType,Integer.parseInt(additionDamageRatio),trainCostResourceType,Integer.parseInt(trainCostResourceCount),Long.parseLong(trainTime),Double.parseDouble(speed),Integer.parseInt(cure),Integer.parseInt(cureForView),Integer.parseInt(cureRange),Integer.parseInt(spaceUse),desc);
	}
	
	public Integer getBaseId(){
 	return baseId;
}
public Integer getUpgradeId(){
 	return upgradeId;
}
public String getRaceType(){
 	return raceType;
}
public String getEntityType(){
 	return entityType;
}
public String getSubType(){
 	return subType;
}
public String getNameForResource(){
 	return nameForResource;
}
public String getNameForView(){
 	return nameForView;
}
public Integer getLevel(){
 	return level;
}
public Integer getAttackWeight(){
 	return attackWeight;
}
public Integer getDefenseWeight(){
 	return defenseWeight;
}
public String getBuildNeedType(){
 	return buildNeedType;
}
public Integer getBuildNeedLevel (){
 	return buildNeedLevel ;
}
public String getUpgradeNeedType(){
 	return upgradeNeedType;
}
public Integer getUpgradeNeedLevel(){
 	return upgradeNeedLevel;
}
public Integer getHp(){
 	return hp;
}
public String getCostResourceType(){
 	return costResourceType;
}
public Integer getCostResourceCount(){
 	return costResourceCount;
}
public Long getBuildTime(){
 	return buildTime;
}
public Double getRange(){
 	return range;
}
public Integer getBlindRange(){
 	return blindRange;
}
public Double getRate(){
 	return rate;
}
public Integer getDamage(){
 	return damage;
}
public Integer getDamageForView(){
 	return damageForView;
}
public Integer getSplashRange(){
 	return splashRange;
}
public Integer getAttackAngle(){
 	return attackAngle;
}
public Integer getTileSize(){
 	return tileSize;
}
public String getTargetType(){
 	return targetType;
}
public String getOnlyAttackTargetType(){
 	return onlyAttackTargetType;
}
public String getResourceType(){
 	return resourceType;
}
public Double getResourcePerSecond(){
 	return resourcePerSecond;
}
public Integer getResourcePerSecondForView(){
 	return resourcePerSecondForView;
}
public Integer getMaxResourceStorage(){
 	return maxResourceStorage;
}
public Integer getBuildExp(){
 	return buildExp;
}
public Integer getSpaceProvide(){
 	return spaceProvide;
}
public Integer getQueueSize(){
 	return queueSize;
}
public Integer getWorkerProvide(){
 	return workerProvide;
}
public Integer getRefillCostResourceCount(){
 	return refillCostResourceCount;
}
public String getAimTarget(){
 	return aimTarget;
}
public Integer getBulletSpeed(){
 	return bulletSpeed;
}
public String getBulletType(){
 	return bulletType;
}
public Integer getNumTarget(){
 	return numTarget;
}
public String getBulletName(){
 	return bulletName;
}
public String getFireEffectName(){
 	return fireEffectName;
}
public String getHitEffectName(){
 	return hitEffectName;
}
public String getBuffEffectName(){
 	return buffEffectName;
}
public String getBuffType(){
 	return buffType;
}
public Double getBuffDamage(){
 	return buffDamage;
}
public Integer getBuffActiveTimes(){
 	return buffActiveTimes;
}
public Double getBuffIntervalTime(){
 	return buffIntervalTime;
}
public Integer getDefense(){
 	return defense;
}
public Integer getDefenseForView(){
 	return defenseForView;
}
public String getAdditionDamageSubType(){
 	return additionDamageSubType;
}
public Integer getAdditionDamageRatio(){
 	return additionDamageRatio;
}
public String getTrainCostResourceType(){
 	return trainCostResourceType;
}
public Integer getTrainCostResourceCount(){
 	return trainCostResourceCount;
}
public Long getTrainTime(){
 	return trainTime;
}
public Double getSpeed(){
 	return speed;
}
public Integer getCure(){
 	return cure;
}
public Integer getCureForView(){
 	return cureForView;
}
public Integer getCureRange(){
 	return cureRange;
}
public Integer getSpaceUse(){
 	return spaceUse;
}
public String getDesc(){
 	return desc;
}

	
	public void setBaseId(Integer baseId){
 	this.baseId=baseId;
}
public void setUpgradeId(Integer upgradeId){
 	this.upgradeId=upgradeId;
}
public void setRaceType(String raceType){
 	this.raceType=raceType;
}
public void setEntityType(String entityType){
 	this.entityType=entityType;
}
public void setSubType(String subType){
 	this.subType=subType;
}
public void setNameForResource(String nameForResource){
 	this.nameForResource=nameForResource;
}
public void setNameForView(String nameForView){
 	this.nameForView=nameForView;
}
public void setLevel(Integer level){
 	this.level=level;
}
public void setAttackWeight(Integer attackWeight){
 	this.attackWeight=attackWeight;
}
public void setDefenseWeight(Integer defenseWeight){
 	this.defenseWeight=defenseWeight;
}
public void setBuildNeedType(String buildNeedType){
 	this.buildNeedType=buildNeedType;
}
public void setBuildNeedLevel (Integer buildNeedLevel ){
 	this.buildNeedLevel =buildNeedLevel ;
}
public void setUpgradeNeedType(String upgradeNeedType){
 	this.upgradeNeedType=upgradeNeedType;
}
public void setUpgradeNeedLevel(Integer upgradeNeedLevel){
 	this.upgradeNeedLevel=upgradeNeedLevel;
}
public void setHp(Integer hp){
 	this.hp=hp;
}
public void setCostResourceType(String costResourceType){
 	this.costResourceType=costResourceType;
}
public void setCostResourceCount(Integer costResourceCount){
 	this.costResourceCount=costResourceCount;
}
public void setBuildTime(Long buildTime){
 	this.buildTime=buildTime;
}
public void setRange(Double range){
 	this.range=range;
}
public void setBlindRange(Integer blindRange){
 	this.blindRange=blindRange;
}
public void setRate(Double rate){
 	this.rate=rate;
}
public void setDamage(Integer damage){
 	this.damage=damage;
}
public void setDamageForView(Integer damageForView){
 	this.damageForView=damageForView;
}
public void setSplashRange(Integer splashRange){
 	this.splashRange=splashRange;
}
public void setAttackAngle(Integer attackAngle){
 	this.attackAngle=attackAngle;
}
public void setTileSize(Integer tileSize){
 	this.tileSize=tileSize;
}
public void setTargetType(String targetType){
 	this.targetType=targetType;
}
public void setOnlyAttackTargetType(String onlyAttackTargetType){
 	this.onlyAttackTargetType=onlyAttackTargetType;
}
public void setResourceType(String resourceType){
 	this.resourceType=resourceType;
}
public void setResourcePerSecond(Double resourcePerSecond){
 	this.resourcePerSecond=resourcePerSecond;
}
public void setResourcePerSecondForView(Integer resourcePerSecondForView){
 	this.resourcePerSecondForView=resourcePerSecondForView;
}
public void setMaxResourceStorage(Integer maxResourceStorage){
 	this.maxResourceStorage=maxResourceStorage;
}
public void setBuildExp(Integer buildExp){
 	this.buildExp=buildExp;
}
public void setSpaceProvide(Integer spaceProvide){
 	this.spaceProvide=spaceProvide;
}
public void setQueueSize(Integer queueSize){
 	this.queueSize=queueSize;
}
public void setWorkerProvide(Integer workerProvide){
 	this.workerProvide=workerProvide;
}
public void setRefillCostResourceCount(Integer refillCostResourceCount){
 	this.refillCostResourceCount=refillCostResourceCount;
}
public void setAimTarget(String aimTarget){
 	this.aimTarget=aimTarget;
}
public void setBulletSpeed(Integer bulletSpeed){
 	this.bulletSpeed=bulletSpeed;
}
public void setBulletType(String bulletType){
 	this.bulletType=bulletType;
}
public void setNumTarget(Integer numTarget){
 	this.numTarget=numTarget;
}
public void setBulletName(String bulletName){
 	this.bulletName=bulletName;
}
public void setFireEffectName(String fireEffectName){
 	this.fireEffectName=fireEffectName;
}
public void setHitEffectName(String hitEffectName){
 	this.hitEffectName=hitEffectName;
}
public void setBuffEffectName(String buffEffectName){
 	this.buffEffectName=buffEffectName;
}
public void setBuffType(String buffType){
 	this.buffType=buffType;
}
public void setBuffDamage(Double buffDamage){
 	this.buffDamage=buffDamage;
}
public void setBuffActiveTimes(Integer buffActiveTimes){
 	this.buffActiveTimes=buffActiveTimes;
}
public void setBuffIntervalTime(Double buffIntervalTime){
 	this.buffIntervalTime=buffIntervalTime;
}
public void setDefense(Integer defense){
 	this.defense=defense;
}
public void setDefenseForView(Integer defenseForView){
 	this.defenseForView=defenseForView;
}
public void setAdditionDamageSubType(String additionDamageSubType){
 	this.additionDamageSubType=additionDamageSubType;
}
public void setAdditionDamageRatio(Integer additionDamageRatio){
 	this.additionDamageRatio=additionDamageRatio;
}
public void setTrainCostResourceType(String trainCostResourceType){
 	this.trainCostResourceType=trainCostResourceType;
}
public void setTrainCostResourceCount(Integer trainCostResourceCount){
 	this.trainCostResourceCount=trainCostResourceCount;
}
public void setTrainTime(Long trainTime){
 	this.trainTime=trainTime;
}
public void setSpeed(Double speed){
 	this.speed=speed;
}
public void setCure(Integer cure){
 	this.cure=cure;
}
public void setCureForView(Integer cureForView){
 	this.cureForView=cureForView;
}
public void setCureRange(Integer cureRange){
 	this.cureRange=cureRange;
}
public void setSpaceUse(Integer spaceUse){
 	this.spaceUse=spaceUse;
}
public void setDesc(String desc){
 	this.desc=desc;
}

	
	public String buildAddSql() {
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append("insert into `pureland`.`entity_model`(`base_id`,`upgrade_id`,`race_type`,`entity_type`,`sub_type`,`name_for_resource`,`name_for_view`,`level`,`attack_weight`,`defense_weight`,`build_need_type`,`build_need_level `,`upgrade_need_type`,`upgrade_need_level`,`hp`,`cost_resource_type`,`cost_resource_count`,`build_time`,`range`,`blind_range`,`rate`,`damage`,`damage_for_view`,`splash_range`,`attack_angle`,`tile_size`,`target_type`,`only_attack_target_type`,`resource_type`,`resource_per_second`,`resource_per_second_for_view`,`max_resource_storage`,`build_exp`,`space_provide`,`queue_size`,`worker_provide`,`refill_cost_resource_count`,`aim_target`,`bullet_speed`,`bullet_type`,`num_target`,`bullet_name`,`fire_effect_name`,`hit_effect_name`,`buff_effect_name`,`buff_type`,`buff_damage`,`buff_active_times`,`buff_interval_time`,`defense`,`defense_for_view`,`addition_damage_sub_type`,`addition_damage_ratio`,`train_cost_resource_type`,`train_cost_resource_count`,`train_time`,`speed`,`cure`,`cure_for_view`,`cure_range`,`space_use`,`desc`) values(").append(this.getBaseId()).append(",").append(this.getUpgradeId()).append(",'").append(this.getRaceType()).append("','").append(this.getEntityType()).append("','").append(this.getSubType()).append("','").append(this.getNameForResource()).append("','").append(this.getNameForView()).append("',").append(this.getLevel()).append(",").append(this.getAttackWeight()).append(",").append(this.getDefenseWeight()).append(",'").append(this.getBuildNeedType()).append("',").append(this.getBuildNeedLevel ()).append(",'").append(this.getUpgradeNeedType()).append("',").append(this.getUpgradeNeedLevel()).append(",").append(this.getHp()).append(",'").append(this.getCostResourceType()).append("',").append(this.getCostResourceCount()).append(",").append(this.getBuildTime()).append(",").append(this.getRange()).append(",").append(this.getBlindRange()).append(",").append(this.getRate()).append(",").append(this.getDamage()).append(",").append(this.getDamageForView()).append(",").append(this.getSplashRange()).append(",").append(this.getAttackAngle()).append(",").append(this.getTileSize()).append(",'").append(this.getTargetType()).append("','").append(this.getOnlyAttackTargetType()).append("','").append(this.getResourceType()).append("',").append(this.getResourcePerSecond()).append(",").append(this.getResourcePerSecondForView()).append(",").append(this.getMaxResourceStorage()).append(",").append(this.getBuildExp()).append(",").append(this.getSpaceProvide()).append(",").append(this.getQueueSize()).append(",").append(this.getWorkerProvide()).append(",").append(this.getRefillCostResourceCount()).append(",'").append(this.getAimTarget()).append("',").append(this.getBulletSpeed()).append(",'").append(this.getBulletType()).append("',").append(this.getNumTarget()).append(",'").append(this.getBulletName()).append("','").append(this.getFireEffectName()).append("','").append(this.getHitEffectName()).append("','").append(this.getBuffEffectName()).append("','").append(this.getBuffType()).append("',").append(this.getBuffDamage()).append(",").append(this.getBuffActiveTimes()).append(",").append(this.getBuffIntervalTime()).append(",").append(this.getDefense()).append(",").append(this.getDefenseForView()).append(",'").append(this.getAdditionDamageSubType()).append("',").append(this.getAdditionDamageRatio()).append(",'").append(this.getTrainCostResourceType()).append("',").append(this.getTrainCostResourceCount()).append(",").append(this.getTrainTime()).append(",").append(this.getSpeed()).append(",").append(this.getCure()).append(",").append(this.getCureForView()).append(",").append(this.getCureRange()).append(",").append(this.getSpaceUse()).append(",'").append(this.getDesc()).append("')");
		return sqlBuffer.toString();
	}
	
	@Override
	public String toString() {
		return "EntityModel[baseId="+baseId+", upgradeId="+upgradeId+", raceType="+raceType+", entityType="+entityType+", subType="+subType+", nameForResource="+nameForResource+", nameForView="+nameForView+", level="+level+", attackWeight="+attackWeight+", defenseWeight="+defenseWeight+", buildNeedType="+buildNeedType+", buildNeedLevel ="+buildNeedLevel +", upgradeNeedType="+upgradeNeedType+", upgradeNeedLevel="+upgradeNeedLevel+", hp="+hp+", costResourceType="+costResourceType+", costResourceCount="+costResourceCount+", buildTime="+buildTime+", range="+range+", blindRange="+blindRange+", rate="+rate+", damage="+damage+", damageForView="+damageForView+", splashRange="+splashRange+", attackAngle="+attackAngle+", tileSize="+tileSize+", targetType="+targetType+", onlyAttackTargetType="+onlyAttackTargetType+", resourceType="+resourceType+", resourcePerSecond="+resourcePerSecond+", resourcePerSecondForView="+resourcePerSecondForView+", maxResourceStorage="+maxResourceStorage+", buildExp="+buildExp+", spaceProvide="+spaceProvide+", queueSize="+queueSize+", workerProvide="+workerProvide+", refillCostResourceCount="+refillCostResourceCount+", aimTarget="+aimTarget+", bulletSpeed="+bulletSpeed+", bulletType="+bulletType+", numTarget="+numTarget+", bulletName="+bulletName+", fireEffectName="+fireEffectName+", hitEffectName="+hitEffectName+", buffEffectName="+buffEffectName+", buffType="+buffType+", buffDamage="+buffDamage+", buffActiveTimes="+buffActiveTimes+", buffIntervalTime="+buffIntervalTime+", defense="+defense+", defenseForView="+defenseForView+", additionDamageSubType="+additionDamageSubType+", additionDamageRatio="+additionDamageRatio+", trainCostResourceType="+trainCostResourceType+", trainCostResourceCount="+trainCostResourceCount+", trainTime="+trainTime+", speed="+speed+", cure="+cure+", cureForView="+cureForView+", cureRange="+cureRange+", spaceUse="+spaceUse+", desc="+desc+"]";
	}
		
}
