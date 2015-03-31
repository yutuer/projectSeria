package com.pureland.common.service.impl;

import java.util.Collection;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import com.google.common.collect.Table;
import com.google.common.collect.Table.Cell;
import com.pureland.common.db.data.Army;
import com.pureland.common.db.data.ArmyExp;
import com.pureland.common.db.data.Building;
import com.pureland.common.db.data.Product;
import com.pureland.common.db.data.Resource;
import com.pureland.common.db.data.Skill;
import com.pureland.common.db.data.UserExt;
import com.pureland.common.db.data.UserRace;
import com.pureland.common.db.data.clan.Clan;
import com.pureland.common.db.data.clan.ClanMember;
import com.pureland.common.db.data.clan.DonateArmy;
import com.pureland.common.db.data.clan.DonateInfo;
import com.pureland.common.db.statics.EntityModel;
import com.pureland.common.enums.BuildingServerStatus;
import com.pureland.common.enums.EnumHelper;
import com.pureland.common.enums.SubServerTypeEnum;
import com.pureland.common.error.CoreException;
import com.pureland.common.log.PurelandLog;
import com.pureland.common.protocal.CampRespProtocal;
import com.pureland.common.protocal.vo.ArmyExpVOProtocal;
import com.pureland.common.protocal.vo.ArmyVOProtocal;
import com.pureland.common.protocal.vo.ArmyVOProtocal.ArmyVO;
import com.pureland.common.protocal.vo.BuildingVOProtocal;
import com.pureland.common.protocal.vo.CampVOProtocal;
import com.pureland.common.protocal.vo.CampVOProtocal.CampVO;
import com.pureland.common.protocal.vo.DonateArmyVOProtocal.DonateArmyVO;
import com.pureland.common.protocal.vo.FederalBuildingVOProtocal.FederalBuildingVO;
import com.pureland.common.protocal.vo.PlayerVOProtocal.PlayerVO;
import com.pureland.common.protocal.vo.ProductionBuildingVOProtocal;
import com.pureland.common.protocal.vo.ProductionItemVOProtocal;
import com.pureland.common.protocal.vo.ResearchBuildingVOProtocal;
import com.pureland.common.protocal.vo.ResourceBuildingVOProtocal;
import com.pureland.common.protocal.vo.ResourceTypeProtocal;
import com.pureland.common.protocal.vo.ResourceVOProtocal;
import com.pureland.common.protocal.vo.SkillVOProtocal;
import com.pureland.common.protocal.vo.TrapBuildingVOProtocal;
import com.pureland.common.service.ArmyExpCommonService;
import com.pureland.common.service.CampCommonService;
import com.pureland.common.service.ClanCommonService;
import com.pureland.common.service.WorkerQueueCommonService;
import com.pureland.common.util.SpringContextUtil;
import com.pureland.core.init.EntityModelHelper;

/**
 * Created by Administrator on 2015/2/10.
 */
public class CampCommonServiceImpl implements CampCommonService {

	private WorkerQueueCommonService workerQueueCommonService = (WorkerQueueCommonService) SpringContextUtil.getBean(WorkerQueueCommonServiceImpl.class
			.getSimpleName());
	private ArmyExpCommonService armyExpCommonService = (ArmyExpCommonService) SpringContextUtil.getBean(ArmyExpCommonServiceImpl.class.getSimpleName());
	private ClanCommonService clanCommonService = (ClanCommonService) SpringContextUtil.getBean(ClanCommonServiceImpl.class.getSimpleName());

	public CampRespProtocal.CampResp buildCampResp(UserRace userRace) throws CoreException {
		CampVOProtocal.CampVO campVO = buildCampVO(userRace);
		CampRespProtocal.CampResp campResp = CampRespProtocal.CampResp.newBuilder().setCampVO(campVO).build();
		return campResp;
	}

	private CampVOProtocal.CampVO buildCampVO(UserRace userRace) throws CoreException {
		UserExt userExt = userRace.getUserExt();
		Integer campCid = userRace.getCampCid();
		List<Building> buildings = userRace.getBuildings();
		List<Army> armies = userRace.getArmies();
		List<Skill> skills = userRace.getSkills();
		List<Building> workHouses = userRace.getBuildingsForMap().get(SubServerTypeEnum.WorkHouse);
		DonateArmy donateArmy = userRace.getDonateArmy();

		List<BuildingVOProtocal.BuildingVO> buildBuildingVOS = buildBuildingVOS(buildings);
		List<ArmyVOProtocal.ArmyVO> buildArmyVOS = buildArmyVOS(armies);

		List<SkillVOProtocal.SkillVO> buildSkillVOS = buildSkillVOS(skills);

		CampVO.Builder campVOBuilder = CampVO.newBuilder();

		PlayerVO playVO = buildPlayerVO(userRace, userExt, Integer.parseInt(userRace.getRaceId().toString()), campCid, userRace.getNickName(),
				workHouses.size());
		campVOBuilder.setPlayer(playVO);

		for (BuildingVOProtocal.BuildingVO buildingVO : buildBuildingVOS) {
			campVOBuilder.addBuildings(buildingVO);
		}
		for (ArmyVOProtocal.ArmyVO armyVO : buildArmyVOS) {
			campVOBuilder.addArmies(armyVO);
		}

		for (SkillVOProtocal.SkillVO skillVO : buildSkillVOS) {
			campVOBuilder.addSkills(skillVO);
		}

		if (playVO.getClanId() > 0 && donateArmy != null) {
			Table<Long, Integer, Integer> table = HashBasedTable.create();
			for (DonateInfo donateInfo : donateArmy.getDonateInfoMap()) {
				Integer num = table.get(donateInfo.getUserRaceId(), donateInfo.getCid());
				if (num == null) {
					num = 0;
				}
				table.put(donateInfo.getUserRaceId(), donateInfo.getCid(), donateInfo.getNum() + num);
			}

			Multimap<Long, A> maps = ArrayListMultimap.create();
			for (Cell<Long, Integer, Integer> cell : table.cellSet()) {
				maps.put(cell.getRowKey(), new A(cell.getColumnKey(), cell.getValue()));
			}

			for (Entry<Long, Collection<A>> entry : maps.asMap().entrySet()) {
				DonateArmyVO.Builder donateArmyVOBuilder = DonateArmyVO.newBuilder();
				donateArmyVOBuilder.setPlayerid(entry.getKey());
				for (A a : entry.getValue()) {
					ArmyVO.Builder armyBuilder = ArmyVO.newBuilder();
					armyBuilder.setCid(a.cid).setAmount(a.num);
					donateArmyVOBuilder.addArmyVOs(armyBuilder.build());
				}
				campVOBuilder.addDonatedArmies(donateArmyVOBuilder.build());
			}
		}

		return campVOBuilder.build();
	}

	private static class A {
		public final Integer cid;
		public final Integer num;

		private A(Integer cid, Integer num) {
			super();
			this.cid = cid;
			this.num = num;
		}
	}

	private List<ArmyExpVOProtocal.ArmyExpVO> buildArmyExpVOs(Set<ArmyExp> armyExps) throws CoreException {
		List<ArmyExpVOProtocal.ArmyExpVO> armyVOS = Lists.newArrayList();
		for (ArmyExp armyExp : armyExps) {
			ArmyExpVOProtocal.ArmyExpVO armyExpVO = ArmyExpVOProtocal.ArmyExpVO.newBuilder().setCid(armyExp.getCid()).setExp(armyExp.getExp()).build();
			armyVOS.add(armyExpVO);
		}
		return armyVOS;
	}

	private PlayerVO buildPlayerVO(UserRace userRace, UserExt userExt, Integer raceId, Integer campId, String nickName, Integer workHouseCount)
			throws CoreException {
		List<Resource> resources = userExt.getResources();
		Long userRaceId = userExt.getUserRaceId();
		WorkerQueueCommonService.WorkerQuene workerQuenes = workerQueueCommonService.getWorkerQuenes(userRaceId);
		Integer busyCount = workerQuenes.getBuildingSids().size();
		Integer freeCount = workHouseCount - busyCount;
		Set<ArmyExp> armyExps = armyExpCommonService.getArmyExps(userRaceId);

		PlayerVO.Builder playerVOBuilder = PlayerVO.newBuilder().setUserId(userRaceId).setRaceType(raceId).setLevel(userExt.getLevel()).setName(nickName)
				.setBaseId(campId).setExperience(userExt.getExperience()).setCrown(userExt.getCrown()).setMaxWorker(workHouseCount).setFreeWorker(freeCount);

		Clan clan = clanCommonService.getClanInfoByUserRaceId(userRaceId);
		if (clan != null) {
			playerVOBuilder.setClanId(clan.getClanBase().getClanId()).setClanName(clan.getClanBase().getClanName())
					.setClanIcon(clan.getClanBase().getClanIcon());
			ClanMember clanMember = clanCommonService.getClanMemberInfo(userRaceId);
			playerVOBuilder.setClanPostion(clanMember.getClanPosition());
		}
		for (Resource resource : resources) {
			ResourceTypeProtocal.ResourceType resourceType = EnumHelper.getProtocalResourceType(resource.getResourceType());
			if (resourceType == null) {
				PurelandLog.error("resourceType is null, " + resource.getResourceType().ordinal());
				continue;

			}
			ResourceVOProtocal.ResourceVO resourceVO = ResourceVOProtocal.ResourceVO.newBuilder().setResourceType(resourceType)
					.setResourceCount(resource.getCount()).build();
			playerVOBuilder.addResources(resourceVO);
		}

		List<ArmyExpVOProtocal.ArmyExpVO> buildArmyExpVOs = buildArmyExpVOs(armyExps);
		for (ArmyExpVOProtocal.ArmyExpVO armyExpVO : buildArmyExpVOs) {
			playerVOBuilder.addArmyShop(armyExpVO);
		}

		for (String skillCid : userRace.getSkillShop()) {
			playerVOBuilder.addSkillShop(Integer.parseInt(skillCid));
		}

		return playerVOBuilder.build();
	}

	private List<BuildingVOProtocal.BuildingVO> buildBuildingVOS(List<Building> buildings) throws CoreException {
		List<BuildingVOProtocal.BuildingVO> buildBuildingVOS = Lists.newArrayList();
		for (Building building : buildings) {
			EntityModel em = EntityModelHelper.ENTITIES.get(building.getCid());
			if (em == null)
				throw new CoreException("!!!! ------ " + building.getCid());
			BuildingVOProtocal.BuildingVO.BuildingStatus buildingStatus = BuildingServerStatus.getBuildingStatus(building.getStatus());
			BuildingVOProtocal.BuildingVO.Builder buildingVOBuilder = BuildingVOProtocal.BuildingVO.newBuilder().setSid(building.getSid())
					.setCid(building.getCid()).setX(building.getAbscissa()).setY(building.getOrdinate()).setBuildingStatus(buildingStatus);

			if (building.getEndTime() != null) {
				if (building.getBuildingType() != SubServerTypeEnum.Research) {
					buildingVOBuilder.setEndTime(building.getEndTime());
				}
			}

			if (building.getGatherTime() != null && building.getStorageCount() != null) {
				ResourceBuildingVOProtocal.ResourceBuildingVO resourceBuildingVO = buildResourceBuildingVO(building.getGatherTime(), building.getStorageCount());
				buildingVOBuilder.setResourceBuildingVO(resourceBuildingVO);
			}

			if (EntityModelHelper.IsAnyProductBuilding(em)) {
				ProductionBuildingVOProtocal.ProductionBuildingVO productionBuildingVO = buildProductionBuildingVO(building);
				buildingVOBuilder.setProductionBuildingVO(productionBuildingVO);
			}

			if (EntityModelHelper.IsResearchBuilding(em)) {
				ResearchBuildingVOProtocal.ResearchBuildingVO researchBuildingVO = buildResearhBuildingVO(building);
				buildingVOBuilder.setResearchBuildingVO(researchBuildingVO);
			}
			if (EntityModelHelper.IsTranBuilding(em)) {
				TrapBuildingVOProtocal.TrapBuildingVO trapBuildingVO = buildTrapBuildingVO(building);
				buildingVOBuilder.setTrapBuildingVO(trapBuildingVO);
			}
			if (EntityModelHelper.IsFederalBuilding(em)) {
				FederalBuildingVO federalBuildingVO = buildFederalBuildVO(building.getUserRaceId());
				buildingVOBuilder.setFederalBuildingVO(federalBuildingVO);
			}
			buildBuildingVOS.add(buildingVOBuilder.build());
		}
		return buildBuildingVOS;
	}

	private FederalBuildingVO buildFederalBuildVO(Long userRaceId) throws CoreException {
		FederalBuildingVO.Builder federalBuildingVOBuilder = FederalBuildingVO.newBuilder();
		DonateArmy donateArmy = clanCommonService.getUserRaceDonateArmy(userRaceId);
		federalBuildingVOBuilder.setAskForDonateEndTime(donateArmy.getNextCanDonateTime());
		return federalBuildingVOBuilder.build();
	}

	private TrapBuildingVOProtocal.TrapBuildingVO buildTrapBuildingVO(Building building) {
		TrapBuildingVOProtocal.TrapBuildingVO.Builder trapBuildingVOBuilder = TrapBuildingVOProtocal.TrapBuildingVO.newBuilder();
		if (building.getStorageCount() == null || building.getStorageCount().intValue() == 0) {
			// 好的
			trapBuildingVOBuilder.setBroken(false);
		} else if (building.getStorageCount().intValue() == 1) {
			// 没好的
			trapBuildingVOBuilder.setBroken(true);
		}
		return trapBuildingVOBuilder.build();
	}

	private ResearchBuildingVOProtocal.ResearchBuildingVO buildResearhBuildingVO(Building building) {
		ResearchBuildingVOProtocal.ResearchBuildingVO.Builder researchBuildingVOBuilder = ResearchBuildingVOProtocal.ResearchBuildingVO.newBuilder();
		if (building.getStorageCount() != null) {
			researchBuildingVOBuilder.setCid(building.getStorageCount()).setEndTime(building.getEndTime());
		} else {
			researchBuildingVOBuilder.setCid(0).setEndTime(0);
		}
		return researchBuildingVOBuilder.build();
	}

	private List<ArmyVOProtocal.ArmyVO> buildArmyVOS(List<Army> armies) throws CoreException {
		List<ArmyVOProtocal.ArmyVO> armyVOS = Lists.newArrayList();
		for (Army army : armies) {
			if (army.getAmount() > 0) {
				ArmyExp armyExp = armyExpCommonService.getArmyExp(army.getArmyExpId());
				ArmyVOProtocal.ArmyVO armyVO = ArmyVOProtocal.ArmyVO.newBuilder().setCid(armyExp.getCid()).setAmount(army.getAmount()).build();
				armyVOS.add(armyVO);
			}
		}
		return armyVOS;
	}

	private List<SkillVOProtocal.SkillVO> buildSkillVOS(List<Skill> skills) throws CoreException {
		List<SkillVOProtocal.SkillVO> skillVOs = Lists.newArrayList();
		for (Skill skill : skills) {
			if (skill.getAmount() > 0) {
				SkillVOProtocal.SkillVO skillVO = SkillVOProtocal.SkillVO.newBuilder().setCid(skill.getCid()).setAmount(skill.getAmount()).build();
				skillVOs.add(skillVO);
			}
		}
		return skillVOs;
	}

	private ResourceBuildingVOProtocal.ResourceBuildingVO buildResourceBuildingVO(Long gatherTime, Integer storageCount) throws CoreException {
		ResourceBuildingVOProtocal.ResourceBuildingVO resourceBuildingVO = ResourceBuildingVOProtocal.ResourceBuildingVO.newBuilder()
				.setLastGatherTime(gatherTime).setStorage(storageCount).build();
		return resourceBuildingVO;
	}

	private ProductionBuildingVOProtocal.ProductionBuildingVO buildProductionBuildingVO(Building building) throws CoreException {
		ProductionBuildingVOProtocal.ProductionBuildingVO.Builder productionBuildingVOBuilder = ProductionBuildingVOProtocal.ProductionBuildingVO.newBuilder();

		List<Product> products = building.getProducts();
		if (CollectionUtils.isNotEmpty(products)) {
			Product product = products.get(0);
			Long endTime = product.getNextEndTime();
			if (endTime == null) {
				throw new CoreException("endtime is null");
			}
			productionBuildingVOBuilder.setEndTime(endTime);
		} else {
			productionBuildingVOBuilder.setEndTime(0);
		}

		List<ProductionItemVOProtocal.ProductionItemVO> productionItemVOS = buildProductionItemVOS(products);
		for (int i = 0; i < productionItemVOS.size(); i++) {
			ProductionItemVOProtocal.ProductionItemVO productionItemVO = productionItemVOS.get(i);
			productionBuildingVOBuilder.addProductionItems(productionItemVO);
		}

		return productionBuildingVOBuilder.build();
	}

	private List<ProductionItemVOProtocal.ProductionItemVO> buildProductionItemVOS(List<Product> products) throws CoreException {
		List<ProductionItemVOProtocal.ProductionItemVO> itemVOS = Lists.newArrayList();
		if (CollectionUtils.isEmpty(products)) {
			return itemVOS;
		}

		for (Product product : products) {
			ProductionItemVOProtocal.ProductionItemVO productItemVO = ProductionItemVOProtocal.ProductionItemVO.newBuilder().setCid(product.getCid())
					.setCount(product.getAmount()).build();
			itemVOS.add(productItemVO);
		}
		return itemVOS;
	}

}
