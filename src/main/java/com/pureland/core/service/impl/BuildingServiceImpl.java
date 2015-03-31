package com.pureland.core.service.impl;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;

import com.google.common.collect.Lists;
import com.pureland.common.db.data.Building;
import com.pureland.common.db.data.Product;
import com.pureland.common.db.data.Resource;
import com.pureland.common.db.data.UserExt;
import com.pureland.common.db.statics.EntityModel;
import com.pureland.common.enums.BuildingServerStatus;
import com.pureland.common.enums.BuildingServerUpgradeEnum;
import com.pureland.common.enums.EntityServerTypeEnum;
import com.pureland.common.enums.RefillServerType;
import com.pureland.common.enums.ResourceServerTypeEnum;
import com.pureland.common.error.CoreException;
import com.pureland.common.log.PurelandLog;
import com.pureland.common.service.ProductCommonService;
import com.pureland.common.service.ResourceCommonService;
import com.pureland.common.service.UserExtCommonService;
import com.pureland.common.service.UserRaceCommonService;
import com.pureland.common.service.bean.BuildingBean;
import com.pureland.common.service.bean.BuildingCompleteBean;
import com.pureland.common.service.bean.BuildingUpgradeBean;
import com.pureland.common.service.bean.ResourceCostBean;
import com.pureland.common.service.impl.BuildingCommonServiceImpl;
import com.pureland.common.service.impl.ProductCommonServiceImpl;
import com.pureland.common.service.impl.ResourceCommonServiceImpl;
import com.pureland.common.service.impl.UserExtCommonServiceImpl;
import com.pureland.common.service.impl.UserRaceCommonServiceImpl;
import com.pureland.common.util.RuleEx;
import com.pureland.common.util.SpringContextUtil;
import com.pureland.core.init.EntityModelHelper;
import com.pureland.core.service.ArmyService;
import com.pureland.core.service.BuildingService;
import com.pureland.core.service.SkillService;
import com.pureland.core.service.building.BuildCompleteLogic;
import com.pureland.core.service.building.BuildUpgradeLogic;
import com.pureland.core.service.conditions.BuildConditionsLinkedSet;
import com.pureland.core.service.conditions.Conditions;
import com.pureland.core.service.conditions.UpgradeBuildConditionsLinkedSet;

public class BuildingServiceImpl extends BuildingCommonServiceImpl implements BuildingService {

	private UserRaceCommonService userRaceCommonService = (UserRaceCommonService) SpringContextUtil.getBean(UserRaceCommonServiceImpl.class.getSimpleName());
	private ProductCommonService productCommonService = (ProductCommonService) SpringContextUtil.getBean(ProductCommonServiceImpl.class.getSimpleName());
	private UserExtCommonService userExtCommonService = (UserExtCommonService) SpringContextUtil.getBean(UserExtCommonServiceImpl.class.getSimpleName());
	private SkillService skillService = (SkillService) SpringContextUtil.getBean(SkillServiceImpl.class.getSimpleName());
	private ArmyService armyService = (ArmyService) SpringContextUtil.getBean(ArmyServiceImpl.class.getSimpleName());
	private ResourceCommonService resourceCommonService = (ResourceCommonService) SpringContextUtil.getBean(ResourceCommonServiceImpl.class.getSimpleName());
	private Map<String, BuildCompleteLogic> buildCompleteLogicMap;
	private Map<String, BuildUpgradeLogic> buildUpgradeLogicMap;

	/**
	 * 校验逻辑 1:建造依赖大本营id 2:可用工人数 3:资源数量
	 */
	@Override
	public void addRuleBuilding(Long sid, Long userRaceId, Integer cid, String costType, Integer abs, Integer ord, long endTime) throws CoreException {
		BuildingBean vm = new BuildingBean(userRaceId, sid, cid, costType);
		Boolean flag = super.addBuilding(sid, userRaceId, cid, abs, ord, endTime, new BuildingRuler(vm));
		if (!flag) {
			throw new CoreException("add building failed, sid = " + sid + ", cid = " + cid);
		}

		EntityModel entityModel = EntityModelHelper.ENTITIES.get(cid);
		// 资源扣减
		userRaceCommonService.resourceAdd(new ResourceCostBean(userRaceId, costType, -1 * entityModel.getCostResourceCount()));
		queueCommonService.addWorkerQuene(userRaceId, sid);
	}

	@Override
	public void upgradeBuilding(BuildingUpgradeBean buildingUpgradeBean) throws CoreException {
		boolean isCancel = buildingUpgradeBean.isCancel();
		BuildingServerUpgradeEnum cancelEnum = isCancel ? BuildingServerUpgradeEnum.Cancel : BuildingServerUpgradeEnum.NotCancel;
		BuildUpgradeLogic buildUpgradeLogic = buildUpgradeLogicMap.get(cancelEnum.getName());
		buildUpgradeLogic.upgradeBuilding(buildingUpgradeBean);
	}

	@Override
	public void completeBuilding(BuildingCompleteBean buildingCompleteBean) throws CoreException {
		BuildCompleteLogic logic = buildCompleteLogicMap.get(buildingCompleteBean.getCompleteType().getName());
		logic.completeBuilding(buildingCompleteBean);
	}

	@Override
	public RuleEx getBuildingRuler(BuildingBean validateModel, BuildingRulerEnum rulerType) throws CoreException {
		if (rulerType == BuildingRulerEnum.Build) {
			return new BuildingRuler(validateModel);
		} else if (rulerType == BuildingRulerEnum.Upgrade) {
			return new UpgradeBuildingRuler(validateModel);
		}
		return null;
	}

	@Override
	public void refillTrap(Long userRaceId, Long sid, RefillServerType refillServerType) throws CoreException {
		UserExt userExt = userExtCommonService.getUserExt(userRaceId);
		Resource goldResource = userExt.getResourcesForMap().get(ResourceServerTypeEnum.Gold);
		int totalConsume = 0;

		if (refillServerType == RefillServerType.All) {
			List<Building> buildingList = getBuildingByEntityType(userRaceId, EntityServerTypeEnum.Trap);
			for (Building building : buildingList) {
				if (building.getStorageCount() != null && building.getStorageCount().intValue() == 1) {
					// 说明需要充能
					EntityModel em = EntityModelHelper.ENTITIES.get(building.getCid());
					totalConsume += em.getRefillCostResourceCount();
				}
			}

			if (totalConsume > goldResource.getCount().intValue()) {
				throw new CoreException("消费不足,need:%d, has:%d", totalConsume, goldResource.getCount().intValue());
			}

			for (Building building : buildingList) {
				if (building.getStorageCount() != null && building.getStorageCount().intValue() == 1) {
					building.setStorageCount(0);
					updateBuilding(building);
				}
			}
		} else if (refillServerType == RefillServerType.Single) {
			String buildingId = Building.getIdKeyString(userRaceId, sid);
			Building building = getBuilding(buildingId);
			// 说明需要充能
			EntityModel em = EntityModelHelper.ENTITIES.get(building.getCid());
			totalConsume = em.getRefillCostResourceCount();
			if (totalConsume > goldResource.getCount().intValue()) {
				throw new CoreException("消费不足,need:%d, has:%d", totalConsume, goldResource.getCount().intValue());
			}

			building.setStorageCount(0);
			updateBuilding(building);
		}

		// 扣钱
		goldResource.setCount(goldResource.getCount() - totalConsume);
		resourceCommonService.updateResource(goldResource);
	}

	public static class UpgradeBuildingRuler implements RuleEx {
		private UpgradeBuildConditionsLinkedSet upgradeBuildConditionsLinkedSet = (UpgradeBuildConditionsLinkedSet) SpringContextUtil
				.getBean(UpgradeBuildConditionsLinkedSet.class.getSimpleName());
		private BuildingBean validateModel;

		public UpgradeBuildingRuler(BuildingBean validateModel) throws CoreException {
			this.validateModel = validateModel;
		}

		@Override
		public Boolean rule() throws CoreException {
			Boolean rule = true;
			LinkedHashSet<Conditions> linkedHashSet = upgradeBuildConditionsLinkedSet.getLinkedHashSet();
			Iterator<Conditions> iterator = linkedHashSet.iterator();
			while (iterator.hasNext()) {
				Conditions condition = iterator.next();
				rule = condition.validation(this.validateModel);
				if (!rule) {
					return false;
				}
			}
			return rule;
		}
	}

	public static class BuildingRuler implements RuleEx {
		private BuildConditionsLinkedSet buildConditionsLinkedSet = (BuildConditionsLinkedSet) SpringContextUtil.getBean(BuildConditionsLinkedSet.class
				.getSimpleName());
		private BuildingBean validateModel;

		public BuildingRuler(BuildingBean validateModel) {
			this.validateModel = validateModel;
		}

		@Override
		public Boolean rule() throws CoreException {
			Boolean rule = true;
			LinkedHashSet<Conditions> linkedHashSet = buildConditionsLinkedSet.getLinkedHashSet();
			Iterator<Conditions> iterator = linkedHashSet.iterator();
			while (iterator.hasNext()) {
				Conditions condition = iterator.next();
				rule = condition.validation(this.validateModel);
				if (!rule) {
					return false;
				}
			}
			return rule;
		}
	}

	@Override
	public void dealBuildingsComleteProduct(final Long userRaceId) throws CoreException {
		List<Building> buildings = getBuildings(userRaceId);
		final List<Building> completedBuildings = Lists.newArrayList();

		for (Building building : buildings) {
			BuildingServerStatus status = building.getStatus();
			Long buildEndTime = building.getEndTime();
			if (buildEndTime != null && !BuildingServerStatus.ON.getId().equals(status.getId()) && System.currentTimeMillis() >= buildEndTime) {
				building.setStatus(BuildingServerStatus.ON);
				completedBuildings.add(building);
			}

			long now = System.currentTimeMillis();
			if (status == BuildingServerStatus.ON) {
				// 处理生产队列
				String firstProductId = null;
				while ((firstProductId = productCommonService.getFirstQueue(userRaceId, building.getSid())) != null) {
					Product firstProduct = productCommonService.getProduct(firstProductId);
					if (firstProduct.getNextEndTime().longValue() <= now) {
						// 说明已经生产完毕
						EntityModel entityModel = EntityModelHelper.ENTITIES.get(firstProduct.getCid());
						long buildingSid = firstProduct.getBuildingSid();
						int leftAmount = firstProduct.getAmount() - 1;
						firstProduct.setAmount(leftAmount);
						// 保存数量
						productCommonService.updateProduct(firstProduct);
						if (leftAmount <= 0) {
							// 要出队
							productCommonService.deleteQuene(userRaceId, buildingSid, firstProduct.getCid(), Long.valueOf(firstProduct.getId()));
						}
						if (entityModel.getEntityType().equals(EntityServerTypeEnum.Skill.name())) {
							skillService.trainingSkill(userRaceId, firstProduct.getCid(), 1);
						} else {
							armyService.trainingArmy(userRaceId, firstProduct.getCid(), 1);
						}

						// 更新下一个队列的时间
						String secondProductId = productCommonService.getFirstQueue(userRaceId, buildingSid);
						if (secondProductId != null) {
							Product secondProduct = productCommonService.getProduct(secondProductId);
							EntityModel em = EntityModelHelper.ENTITIES.get(secondProduct.getCid());
							secondProduct.setNextEndTime(firstProduct.getNextEndTime() + em.getTrainTime() * 1000L);
							productCommonService.updateProduct(secondProduct);
						}
					} else {
						break;
					}
				}
			}
		}

		if (CollectionUtils.isNotEmpty(completedBuildings)) {
			// 异步update building to redis
			super.taskExecutor.execute(new Runnable() {

				@Override
				public void run() {
					for (Building building : completedBuildings) {
						Long sid = building.getSid();
						Long buildEndTime = building.getEndTime();
						if (buildEndTime != null && System.currentTimeMillis() >= buildEndTime) {
							try {
								updateBuilding(building);
								queueCommonService.removeWorkerQuene(userRaceId, sid);
							} catch (CoreException e) {
								PurelandLog.error(e.getMessage());
								e.printStackTrace();
							}
						}
					}

				}
			});
		}
	}

	public void setBuildUpgradeLogicMap(Map<String, BuildUpgradeLogic> buildUpgradeLogicMap) {
		this.buildUpgradeLogicMap = buildUpgradeLogicMap;
	}

	public void setBuildCompleteLogicMap(Map<String, BuildCompleteLogic> buildCompleteLogicMap) {
		this.buildCompleteLogicMap = buildCompleteLogicMap;
	}

}
