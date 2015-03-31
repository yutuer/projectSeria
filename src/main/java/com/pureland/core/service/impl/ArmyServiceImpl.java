package com.pureland.core.service.impl;

import com.pureland.common.component.cache.api.RString;
import com.pureland.common.component.cache.error.RedisException;
import com.pureland.common.db.data.Army;
import com.pureland.common.db.data.ArmyExp;
import com.pureland.common.db.statics.EntityModel;
import com.pureland.common.enums.SubServerTypeEnum;
import com.pureland.common.enums.ResourceServerTypeEnum;
import com.pureland.common.error.CoreException;
import com.pureland.common.log.PurelandLog;
import com.pureland.common.service.ArmyCommonService;
import com.pureland.common.service.ArmyExpCommonService;
import com.pureland.common.service.BuildingCommonService;
import com.pureland.common.service.impl.ArmyCommonServiceImpl;
import com.pureland.common.service.impl.ArmyExpCommonServiceImpl;
import com.pureland.common.service.impl.BuildingCommonServiceImpl;
import com.pureland.common.util.SpringContextUtil;
import com.pureland.core.init.EntityModelHelper;
import com.pureland.core.service.ArmyService;

import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class ArmyServiceImpl extends ArmyCommonServiceImpl implements ArmyService {

	protected BuildingCommonService buildingCommonService = (BuildingCommonService) SpringContextUtil.getBean(BuildingCommonServiceImpl.class.getSimpleName());
	protected ArmyCommonService armyCommonService = (ArmyCommonService) SpringContextUtil.getBean(ArmyCommonServiceImpl.class.getSimpleName());
	protected ArmyExpCommonService armyExpCommonService = (ArmyExpCommonService) SpringContextUtil.getBean(ArmyExpCommonServiceImpl.class.getSimpleName());

	@Override
	public void trainingArmy(final Long userRaceId, final Integer cid, Integer amount) throws CoreException {
		Integer totalAmount = 0;
		try {
			// 需要判断军营空间 spaceProvide
			int maxSpace = buildingCommonService.getSameTypeBuildingsLeftSpace(userRaceId, SubServerTypeEnum.Barracks);
			int armySpace = armyCommonService.getArmySpace(userRaceId);
			int leftSpace = maxSpace - armySpace;

			EntityModel entityModel = EntityModelHelper.ENTITIES.get(cid);
			if (entityModel.getSpaceUse() * amount > leftSpace) {
				// 如果训练军队不能放入军营,则直接返回
				PurelandLog.info("如果训练军队不能放入军营,则直接返回----");
				return;
			}

			String keyId = Army.generatorIdKey(new String[] { userRaceId.toString(), cid.toString() });
			String armyId = RString.get(keyId);
			if (StringUtils.isEmpty(armyId)) {
				super.addArmy(userRaceId, cid, amount, amount);
				totalAmount = armyExpCommonService.addArmyExpExp(userRaceId, cid, amount);
			} else {
				Army army = super.getArmy(Long.parseLong(armyId));
				army.setAmount(army.getAmount() + amount);
				super.updateArmy(army);
				totalAmount = armyExpCommonService.addArmyExpExp(army.getArmyExpId(), amount);
			}

			Integer upgradeId = entityModel.getUpgradeId();
			EntityModel upgradeEntity = EntityModelHelper.ENTITIES.get(upgradeId);
			Integer costResourceCount = upgradeEntity.getCostResourceCount();
			ResourceServerTypeEnum resourceServerType = ResourceServerTypeEnum.getResourceServerTypeEnumByName(upgradeEntity.getCostResourceType());

			if (resourceServerType == null || !resourceServerType.name().equals(ResourceServerTypeEnum.Number.name())) {
				throw new CoreException("costResourceType is illegal, " + resourceServerType.name());
			}

			if (totalAmount >= costResourceCount) {
				upgradeArmy(userRaceId, cid);
			}
		} catch (RedisException e) {
			throw new CoreException(e);
		}
	}

	@Override
	public void upgradeArmy(Long userRaceId, Integer cid) throws CoreException {
		EntityModel entityModel = EntityModelHelper.ENTITIES.get(cid);
		armyExpCommonService.upgradeCid(userRaceId, cid, entityModel.getUpgradeId());
	}

	@Override
	public void updateBattleArmy(List<Army> armies) throws CoreException {
		for (Army army : armies) {
			try {
				Long userRaceId = army.getUserRaceId();
				Long armyExpId = army.getArmyExpId();
				ArmyExp armyExp = armyExpCommonService.getArmyExp(armyExpId);
				Integer cid = armyExp.getCid();
				Integer amount = army.getAmount();

				String keyId = Army.generatorIdKey(new String[] { userRaceId.toString(), cid.toString() });
				String armyId = RString.get(keyId);
				if (StringUtils.isEmpty(armyId)) {
					throw new CoreException("armyId is null");
				}
				Army dbArmy = super.getArmy(Long.parseLong(armyId));
				Integer dbAmount = dbArmy.getAmount();
				dbArmy.setAmount(dbAmount - amount);
				super.updateArmy(dbArmy);
			} catch (RedisException e) {
				throw new CoreException(e);
			}
		}
	}

}
