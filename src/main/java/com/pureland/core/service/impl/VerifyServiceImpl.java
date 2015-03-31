package com.pureland.core.service.impl;

import com.google.common.collect.Lists;
import com.pureland.common.db.data.UserRace;
import com.pureland.common.error.CoreException;
import com.pureland.common.protocal.vo.*;
import com.pureland.common.protocal.vo.DonateArmyVOProtocal.DonateArmyVO;
import com.pureland.common.service.CampCommonService;
import com.pureland.common.service.impl.CampCommonServiceImpl;
import com.pureland.common.util.SpringContextUtil;
import com.pureland.core.service.UserRaceService;
import com.pureland.core.service.VerifyService;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Administrator on 2015/3/3.
 */
public class VerifyServiceImpl implements VerifyService {
	private UserRaceService userRaceService = (UserRaceService) SpringContextUtil.getBean(UserRaceServiceImpl.class.getSimpleName());
	private CampCommonService campCommonService = (CampCommonService) SpringContextUtil.getBean(CampCommonServiceImpl.class.getSimpleName());

	public void dealCompareResult(CampVOProtocal.CampVO clientCampVO) throws Exception {
		if (clientCampVO != null && clientCampVO.getPlayer().getUserId() > 0) {
			CampVOProtocal.CampVO serverCampVO = null;
			try {
				UserRace ur = userRaceService.getUserRace(clientCampVO.getPlayer().getUserId());
				serverCampVO = campCommonService.buildCampResp(ur).getCampVO();
				isCampVOEqual(clientCampVO, serverCampVO);
				// if (!clientCampVO.equals(serverCampVO)) {}
			} catch (Exception e) {
				System.out.println("不一致:-----------------------------------------------------");
				System.out.println(clientCampVO);
				System.out.println(serverCampVO);
				throw e;
			}
		}
	}

	private boolean isCampVOEqual(CampVOProtocal.CampVO one, CampVOProtocal.CampVO two) throws CoreException {
		comparePlayerVO(one, two);
		CompareBuildingVO(one, two);
		CompareArmyListVO(one, two);
		CompareDonatedArmyListVO(one, two);
		compareSkillVO(one, two);

		// if(compareByte(one, two)){
		// throw new
		// CoreException(String.format("CampVOProtocal.CampVO 不一致 \none:%s,two:%s\n",
		// one.toString(), two.toString()));
		// }
		return true;
	}

	private void compareSkillVO(CampVOProtocal.CampVO one, CampVOProtocal.CampVO two) throws CoreException {
		List<SkillVOProtocal.SkillVO> skillVOs_one = one.getSkillsList();
		List<SkillVOProtocal.SkillVO> skillVOs_two = two.getSkillsList();

		if (skillVOs_one.size() != skillVOs_two.size()) {
			throw new CoreException(String.format("SkillVOProtocal.SkillVO 大小不一致 \nskillVOs_one.size():%d,skillVOs_two.size():%d\n", skillVOs_one.size(),
					skillVOs_two.size()));
		}

		Comparator<SkillVOProtocal.SkillVO> skillVOCompator = new Comparator<SkillVOProtocal.SkillVO>() {
			@Override
			public int compare(SkillVOProtocal.SkillVO s1, SkillVOProtocal.SkillVO s2) {
				return s1.getCid() - s2.getCid();
			}
		};

		List<SkillVOProtocal.SkillVO> skillVOsList_one = Lists.newArrayList();
		List<SkillVOProtocal.SkillVO> skillVOsList_two = Lists.newArrayList();
		skillVOsList_one.addAll(skillVOs_one);
		skillVOsList_two.addAll(skillVOs_two);

		Collections.sort(skillVOsList_one, skillVOCompator);
		Collections.sort(skillVOsList_two, skillVOCompator);

		for (int i = 0; i < skillVOsList_one.size(); i++) {
			SkillVOProtocal.SkillVO s1 = skillVOsList_one.get(i);
			SkillVOProtocal.SkillVO s2 = skillVOsList_two.get(i);
			if (compareByte(s1, s2)) {
				throw new CoreException(String.format("SkillVOProtocal.SkillVO 不一致  \ns1:%s , s2:%s \n ", s1.toString(), s2.toString()));
			}
		}
	}

	private void CompareDonatedArmyListVO(CampVOProtocal.CampVO one, CampVOProtocal.CampVO two) throws CoreException {
		List<DonateArmyVO> armyVO_one = one.getDonatedArmiesList();
		List<DonateArmyVO> armyVO_two = two.getDonatedArmiesList();

		if (armyVO_one.size() != armyVO_two.size()) {
			throw new CoreException(String.format("getDonatedArmiesList ArmyVOProtocal.ArmyVO 大小不一致 \narmyVO_one.size():%d,armyVO_two.size():%d\n",
					armyVO_one.size(), armyVO_two.size()));
		}

		Comparator<DonateArmyVO> armyVOCompator = new Comparator<DonateArmyVO>() {
			@Override
			public int compare(DonateArmyVO a1, DonateArmyVO a2) {
				return (int) (a1.getPlayerid() - a2.getPlayerid());
			}
		};

		List<DonateArmyVO> armyVOsList_one = Lists.newArrayList();
		List<DonateArmyVO> armyVOsList_two = Lists.newArrayList();
		armyVOsList_one.addAll(armyVO_one);
		armyVOsList_two.addAll(armyVO_two);

		Collections.sort(armyVOsList_one, armyVOCompator);
		Collections.sort(armyVOsList_two, armyVOCompator);

		for (int i = 0; i < armyVOsList_one.size(); i++) {
			DonateArmyVO a1 = armyVOsList_one.get(i);
			DonateArmyVO a2 = armyVOsList_two.get(i);
			if (compareByte(a1, a2)) {
				throw new CoreException(String.format("getDonatedArmiesList() ArmyVOProtocal.ArmyVO 不一致  \na1:%s , a2:%s \n", a1.toString(), a2.toString()));
			}
		}
	}

	private void CompareArmyListVO(CampVOProtocal.CampVO one, CampVOProtocal.CampVO two) throws CoreException {
		List<ArmyVOProtocal.ArmyVO> armyVO_one = one.getArmiesList();
		List<ArmyVOProtocal.ArmyVO> armyVO_two = two.getArmiesList();

		if (armyVO_one.size() != armyVO_two.size()) {
			throw new CoreException(String.format("getArmiesList ArmyVOProtocal.ArmyVO 大小不一致 \narmyVO_one.size():%d,armyVO_two.size():%d \n",
					armyVO_one.size(), armyVO_two.size()));
		}

		Comparator<ArmyVOProtocal.ArmyVO> armyVOCompator = new Comparator<ArmyVOProtocal.ArmyVO>() {
			@Override
			public int compare(ArmyVOProtocal.ArmyVO a1, ArmyVOProtocal.ArmyVO a2) {
				return a1.getCid() - a2.getCid();
			}
		};

		List<ArmyVOProtocal.ArmyVO> armyVOsList_one = Lists.newArrayList();
		List<ArmyVOProtocal.ArmyVO> armyVOsList_two = Lists.newArrayList();
		armyVOsList_one.addAll(armyVO_one);
		armyVOsList_two.addAll(armyVO_two);

		Collections.sort(armyVOsList_one, armyVOCompator);
		Collections.sort(armyVOsList_two, armyVOCompator);

		for (int i = 0; i < armyVOsList_one.size(); i++) {
			ArmyVOProtocal.ArmyVO a1 = armyVOsList_one.get(i);
			ArmyVOProtocal.ArmyVO a2 = armyVOsList_two.get(i);
			if (compareByte(a1, a2)) {
				throw new CoreException(String.format("getArmiesList() ArmyVOProtocal.ArmyVO 不一致 \na1:%s , a2:%s \n ", a1.toString(), a2.toString()));
			}
		}
	}

	private void CompareBuildingVO(CampVOProtocal.CampVO one, CampVOProtocal.CampVO two) throws CoreException {
		List<BuildingVOProtocal.BuildingVO> buildVOs_one = one.getBuildingsList();
		List<BuildingVOProtocal.BuildingVO> buildVOs_two = two.getBuildingsList();

		if (buildVOs_one.size() != buildVOs_two.size()) {
			throw new CoreException(String.format("BuildingVOProtocal.BuildingVO 大小不一致 \nbuildVOs_one.size():%d,buildVOs_two.size():%d \n",
					buildVOs_one.size(), buildVOs_two.size()));
		}

		Comparator<BuildingVOProtocal.BuildingVO> BuildingVOCompator = new Comparator<BuildingVOProtocal.BuildingVO>() {
			@Override
			public int compare(BuildingVOProtocal.BuildingVO o1, BuildingVOProtocal.BuildingVO o2) {
				return (int) (o1.getSid() - o2.getSid());
			}
		};

		List<BuildingVOProtocal.BuildingVO> buildingVOsList_one = Lists.newArrayList();
		List<BuildingVOProtocal.BuildingVO> buildingVOsList_two = Lists.newArrayList();
		buildingVOsList_one.addAll(buildVOs_one);
		buildingVOsList_two.addAll(buildVOs_two);

		Collections.sort(buildingVOsList_one, BuildingVOCompator);
		Collections.sort(buildingVOsList_two, BuildingVOCompator);

		for (int i = 0; i < buildingVOsList_one.size(); i++) {
			BuildingVOProtocal.BuildingVO b1 = buildingVOsList_one.get(i);
			BuildingVOProtocal.BuildingVO b2 = buildingVOsList_two.get(i);
			try {
				compareBuildingVO(b1, b2);
			} catch (CoreException e) {
				throw new CoreException(String.format("BuildingVOProtocal.BuildingVO 不一致 \nb1:%s ,b2:%s \n reason:%s\n", b1.toString(), b2.toString(),
						e.getMessage()));
			}
		}
	}

	private boolean compareBuildingVO(BuildingVOProtocal.BuildingVO b1, BuildingVOProtocal.BuildingVO b2) throws CoreException {
		if (b1.getSid() != b2.getSid())
			throw new CoreException(String.format("b1.getSid() != b2.getSid() , %d , %d\n", b1.getSid(), b2.getSid()));
		if (b1.getCid() != b2.getCid())
			throw new CoreException(String.format("b1.getCid() != b2.getCid() , %d , %d\n", b1.getCid(), b2.getCid()));
		if (b1.getEndTime() != b2.getEndTime())
			throw new CoreException(String.format("b1.getEndTime() != b2.getEndTime() , %d , %d\n", b1.getEndTime(), b2.getEndTime()));
		if (b1.getBuildingStatus() != b2.getBuildingStatus()) {
			throw new CoreException(String.format("b1.getBuildingStatus() != b2.getBuildingStatus() , %s , %s\n", b1.getBuildingStatus().name(), b2
					.getBuildingStatus().name()));
		}
		if (b1.getX() != b2.getX())
			throw new CoreException(String.format("b1.getX() != b2.getX() , %d , %d\n", b1.getX(), b2.getX()));
		if (b1.getY() != b2.getY())
			throw new CoreException(String.format("b1.getY() != b2.getY() , %d , %d\n", b1.getY(), b2.getY()));

		ResourceBuildingVOProtocal.ResourceBuildingVO resourceBuildingVO1 = b1.getResourceBuildingVO();
		ResourceBuildingVOProtocal.ResourceBuildingVO resourceBuildingVO2 = b2.getResourceBuildingVO();
		if (resourceBuildingVO1.getLastGatherTime() != resourceBuildingVO2.getLastGatherTime())
			throw new CoreException(String.format("resourceBuildingVO1.getLastGatherTime() != resourceBuildingVO2.getLastGatherTime() , %d , %d\n",
					resourceBuildingVO1.getLastGatherTime(), resourceBuildingVO2.getLastGatherTime()));
		if (resourceBuildingVO1.getStorage() != resourceBuildingVO2.getStorage())
			throw new CoreException(String.format("resourceBuildingVO1.getStorage() != resourceBuildingVO2.getStorage() , %d , %d\n",
					resourceBuildingVO1.getStorage(), resourceBuildingVO1.getStorage()));
		ProductionBuildingVOProtocal.ProductionBuildingVO productionBuildingVO1 = b1.getProductionBuildingVO();
		ProductionBuildingVOProtocal.ProductionBuildingVO productionBuildingVO2 = b2.getProductionBuildingVO();
		if (productionBuildingVO1.getEndTime() != productionBuildingVO2.getEndTime())
			throw new CoreException(String.format("productionBuildingVO1.getEndTime() != productionBuildingVO2.getEndTime(), %d , %d\n",
					productionBuildingVO1.getEndTime(), productionBuildingVO2.getEndTime()));
		compareVOList(productionBuildingVO1.getProductionItemsList(), productionBuildingVO2.getProductionItemsList(),
				new Comparator<ProductionItemVOProtocal.ProductionItemVO>() {
					@Override
					public int compare(ProductionItemVOProtocal.ProductionItemVO o1, ProductionItemVOProtocal.ProductionItemVO o2) {
						return o1.getCid() - o2.getCid();
					}
				});

		ResearchBuildingVOProtocal.ResearchBuildingVO researchBuildingVO1 = b1.getResearchBuildingVO();
		ResearchBuildingVOProtocal.ResearchBuildingVO researchBuildingVO2 = b2.getResearchBuildingVO();
		if (researchBuildingVO1.getCid() != researchBuildingVO2.getCid())
			throw new CoreException(String.format("researchBuildingVO1.getCid() != researchBuildingVO2.getCid(), %d , %d\n", researchBuildingVO1.getCid(),
					researchBuildingVO2.getCid()));
		if (researchBuildingVO1.getEndTime() != researchBuildingVO2.getEndTime())
			throw new CoreException(String.format("researchBuildingVO1.getEndTime() != researchBuildingVO2.getEndTime(), %d , %d\n",
					researchBuildingVO1.getEndTime(), researchBuildingVO2.getEndTime()));
		TrapBuildingVOProtocal.TrapBuildingVO trapBuildingVO1 = b1.getTrapBuildingVO();
		TrapBuildingVOProtocal.TrapBuildingVO trapBuildingVO2 = b2.getTrapBuildingVO();
		if (trapBuildingVO1.getBroken() != trapBuildingVO2.getBroken())
			throw new CoreException(String.format("trapBuildingVO1.getBroken() != trapBuildingVO2.getBroken(), %b , %b\n", trapBuildingVO1.getBroken(),
					trapBuildingVO2.getBroken()));
		return false;
	}

	private <T extends com.google.protobuf.GeneratedMessage> void compareVOList(List<T> vo1, List<T> vo2, Comparator<T> comparator) throws CoreException {
		if (vo1.size() != vo2.size()) {
			if (vo1.size() > 0) {
				throw new CoreException(String.format("%s 大小不一致 \n vo1.size():%d,vo2.size():%d \n", vo1.get(0).getClass().getSimpleName(), vo1.size(),
						vo2.size()));
			} else if (vo2.size() > 0) {
				throw new CoreException(String.format("%s 大小不一致 \n vo1.size():%d,vo2.size():%d \n", vo2.get(0).getClass().getSimpleName(), vo1.size(),
						vo2.size()));
			}
		}

		List<T> list1 = Lists.newArrayList();
		List<T> list2 = Lists.newArrayList();
		list1.addAll(vo1);
		list2.addAll(vo2);

		Collections.sort(list1, comparator);
		Collections.sort(list2, comparator);

		for (int i = 0; i < list1.size(); i++) {
			T t1 = list1.get(i);
			T t2 = list2.get(i);
			if (compareByte(t1, t2)) {
				throw new CoreException(String.format("%s 不一致 \nt1:%s , t2:%s \n", t1.getClass().getSimpleName(), t1.toString(), t2.toString()));
			}
		}
	}

	private void comparePlayerVO(CampVOProtocal.CampVO one, CampVOProtocal.CampVO two) throws CoreException {
		PlayerVOProtocal.PlayerVO playerVO_one = one.getPlayer();
		PlayerVOProtocal.PlayerVO playerVO_two = two.getPlayer();

		List<ResourceVOProtocal.ResourceVO> resourceVOs_one = playerVO_one.getResourcesList();
		List<ResourceVOProtocal.ResourceVO> resourceVOs_two = playerVO_two.getResourcesList();

		if (resourceVOs_one.size() != resourceVOs_two.size()) {
			throw new CoreException(String.format("ResourceVOProtocal.ResourceVO 大小不一致 \nresourceVOs_one.size():%d,resourceVOs_two.size():%d \n",
					resourceVOs_one.size(), resourceVOs_two.size()));
		}

		List<ResourceVOProtocal.ResourceVO> resourceVOsList_one = Lists.newArrayList();
		List<ResourceVOProtocal.ResourceVO> resourceVOsList_two = Lists.newArrayList();
		resourceVOsList_one.addAll(resourceVOs_one);
		resourceVOsList_two.addAll(resourceVOs_two);

		Comparator<ResourceVOProtocal.ResourceVO> ResourceVOCompator = new Comparator<ResourceVOProtocal.ResourceVO>() {
			@Override
			public int compare(ResourceVOProtocal.ResourceVO o1, ResourceVOProtocal.ResourceVO o2) {
				return o1.getResourceType().ordinal() - o2.getResourceType().ordinal();
			}
		};

		Collections.sort(resourceVOsList_one, ResourceVOCompator);
		Collections.sort(resourceVOsList_two, ResourceVOCompator);

		for (int i = 0; i < resourceVOsList_one.size(); i++) {
			ResourceVOProtocal.ResourceVO r1 = resourceVOsList_one.get(i);
			ResourceVOProtocal.ResourceVO r2 = resourceVOsList_two.get(i);
			if (compareResourceVO(r1, r2)) {
				throw new CoreException(String.format("ResourceVOProtocal.ResourceVO 不一致 \nr1:%s , r2:%s \n", r1.toString(), r2.toString()));
			}
		}

		comparePlayerSingleField(playerVO_one, playerVO_two);
	}

	private boolean compareResourceVO(ResourceVOProtocal.ResourceVO r1, ResourceVOProtocal.ResourceVO r2) {
		if (r1.getResourceType() != r2.getResourceType())
			return true;
		if (r1.getResourceCount() != r2.getResourceCount())
			return true;
		return false;
	}

	private void comparePlayerSingleField(PlayerVOProtocal.PlayerVO playerVO_one, PlayerVOProtocal.PlayerVO playerVO_two) throws CoreException {
		if (playerVO_one.getUserId() != playerVO_two.getUserId())
			throw new CoreException(String.format("playerVO_one.getUserId():%d, playerVO_two.getUserId():%d\n", playerVO_one.getUserId(),
					playerVO_two.getUserId()));
		if (playerVO_one.getRaceType() != playerVO_two.getRaceType())
			throw new CoreException(String.format("playerVO_one.getRaceType():%d, playerVO_two.getRaceType():%d\n", playerVO_one.getRaceType(),
					playerVO_two.getRaceType()));
		if (playerVO_one.getLevel() != playerVO_two.getLevel())
			throw new CoreException(String.format("playerVO_one.getLevel():%d, playerVO_two.getLevel():%d\n", playerVO_one.getLevel(), playerVO_two.getLevel()));
		if (!playerVO_one.getName().equals(playerVO_two.getName()))
			throw new CoreException(String.format("playerVO_one.getName():%s, playerVO_two.getName():%s\n", playerVO_one.getName(), playerVO_two.getName()));
		if (playerVO_one.getExperience() != playerVO_two.getExperience())
			throw new CoreException(String.format("playerVO_one.getExperience():%d, playerVO_two.getExperience():%d\n", playerVO_one.getExperience(),
					playerVO_two.getExperience()));
		if (playerVO_one.getCrown() != playerVO_two.getCrown())
			throw new CoreException(String.format("playerVO_one.getExperience():%d, playerVO_two.getExperience():%d\n", playerVO_one.getExperience(),
					playerVO_two.getExperience()));

		compareVOList(playerVO_one.getArmyShopList(), playerVO_two.getArmyShopList(), new Comparator<ArmyExpVOProtocal.ArmyExpVO>() {
			@Override
			public int compare(ArmyExpVOProtocal.ArmyExpVO o1, ArmyExpVOProtocal.ArmyExpVO o2) {
				return o1.getCid() - o2.getCid();
			}
		});

		if (ddd(playerVO_one.getSkillShopList(), playerVO_two.getSkillShopList()))
			throw new CoreException(String.format("playerVO_one.getSkillShopList():%s, playerVO_two.getSkillShopList():%s\n", playerVO_one.getSkillShopList()
					.toString(), playerVO_two.getSkillShopList().toString()));
		if (playerVO_one.getBaseId() != playerVO_two.getBaseId())
			throw new CoreException(String.format("playerVO_one.getBaseId():%d, playerVO_two.getBaseId():%d\n", playerVO_one.getBaseId(),
					playerVO_two.getBaseId()));
		if (playerVO_one.getMaxWorker() != playerVO_two.getMaxWorker())
			throw new CoreException(String.format("playerVO_one.getMaxWorker():%d, playerVO_two.getMaxWorker():%d\n", playerVO_one.getMaxWorker(),
					playerVO_two.getMaxWorker()));
		if (playerVO_one.getFreeWorker() != playerVO_two.getFreeWorker())
			throw new CoreException(String.format("playerVO_one.getFreeWorker():%d, playerVO_two.getFreeWorker():%d\n", playerVO_one.getFreeWorker(),
					playerVO_two.getFreeWorker()));
	}

	private boolean compareByte(com.google.protobuf.GeneratedMessage vo1, com.google.protobuf.GeneratedMessage vo2) {
		byte[] one = vo1.toByteArray();
		byte[] two = vo2.toByteArray();
		return ddd(one, two);
	}

	private boolean ddd(byte[] one, byte[] two) {
		if (one.length != two.length) {
			return true;
		}
		for (int i = 0; i < one.length; i++) {
			if (one[i] != two[i]) {
				return true;
			}
		}
		return false;
	}

	private <T> boolean ddd(List<T> one, List<T> two) {
		if (one.size() != two.size()) {
			return true;
		}
		for (int i = 0; i < one.size(); i++) {
			if (!one.contains(two.get(i))) {
				return true;
			}
		}
		return false;
	}
}
