package com.pureland.core.handler.api;

import com.google.common.collect.Lists;
import com.pureland.common.db.data.UserRace;
import com.pureland.common.db.data.battle.*;
import com.pureland.common.enums.BattleType;
import com.pureland.common.enums.ResourceServerTypeEnum;
import com.pureland.common.error.CoreException;
import com.pureland.common.log.PurelandLog;
import com.pureland.common.protocal.BattleResultReqProtocal.BattleResultReq;
import com.pureland.common.protocal.ReqWrapperProtocal.ReqWrapper;
import com.pureland.common.protocal.RespWrapperProtocal;
import com.pureland.common.protocal.RespWrapperProtocal.RespWrapper;
import com.pureland.common.protocal.vo.ArmyVOProtocal.ArmyVO;
import com.pureland.common.protocal.vo.BattleReplayVOProtocal.BattleReplayVO;
import com.pureland.common.protocal.vo.BattleResultVOProtocal.BattleResultVO;
import com.pureland.common.protocal.vo.ResourceTypeProtocal.ResourceType;
import com.pureland.common.protocal.vo.ResourceVOProtocal.ResourceVO;
import com.pureland.common.protocal.vo.SkillVOProtocal;
import com.pureland.common.service.UserRaceCommonService;
import com.pureland.common.service.impl.UserRaceCommonServiceImpl;
import com.pureland.common.util.SpringContextUtil;
import com.pureland.core.handler.RequestAPIHandler;
import com.pureland.core.service.BattleService;
import com.pureland.core.service.impl.BattleServiceImpl;
import org.apache.commons.collections.CollectionUtils;

import java.util.List;

public class BattleResultHandler extends RequestAPIHandler {
	private BattleService battleService = (BattleService) SpringContextUtil.getBean(BattleServiceImpl.class.getSimpleName());
	private UserRaceCommonService userRaceCommonService = (UserRaceCommonService) SpringContextUtil.getBean(UserRaceCommonServiceImpl.class.getSimpleName());

	@Override
	public RespWrapper handler(ReqWrapper reqWrapper, String authToken, Long timestamp) throws CoreException {
		Long userId = authUser(authToken);
		Long userRaceId = getLastUserRaceId(userId);
		BattleResultReq battleResultReq = reqWrapper.getBattleResultReq();
		BattleResultVO battleResultVO = battleResultReq.getBattleResultVO();
		BattleReplayVO battleReplayVO = battleResultReq.getBattleReplayVO();

		Attack attack = buildAttack(userRaceId, battleResultVO);
		userRaceCommonService.updateUserRaceFightStatus(attack.getPeerId(), false);
		Replay replay = buildReplay(userRaceId, battleReplayVO);

		battleService.battleResult(attack, replay);

		RespWrapper respWrapper = RespWrapperProtocal.RespWrapper.newBuilder().build();
		PurelandLog.info(reqWrapper.toString());
		return respWrapper;
	}

	private Attack buildAttack(Long userRaceId, BattleResultVO battleResultVO) throws CoreException {
		Attack attack = new Attack();
		attack.setUserRaceId(userRaceId);
		attack.setPercentage(battleResultVO.getPercentage());
		attack.setStar(battleResultVO.getStar());
		attack.setUseDonatedArmy(battleResultVO.getUseDonatedArmy());
		attack.setBrokenTraps(battleResultVO.getBrokenTrapsList());

		List<SkillConsume> skillConsumeList = Lists.newArrayList();
		List<SkillVOProtocal.SkillVO> skillVOList = battleResultVO.getUsedSkillsList();
		if (CollectionUtils.isNotEmpty(skillVOList)) {
			for (SkillVOProtocal.SkillVO skillVO : skillVOList) {
				SkillConsume skillConsume = buildSkillConsume(userRaceId, skillVO);
				skillConsumeList.add(skillConsume);
			}
		}

		List<ArmyConsume> usedArmies = Lists.newArrayList();
		List<ArmyVO> usedArmiesList = battleResultVO.getUsedArmiesList();
		if (CollectionUtils.isNotEmpty(usedArmiesList)) {
			for (ArmyVO armyVO : usedArmiesList) {
				ArmyConsume armyConsume = buildArmyConsume(userRaceId, armyVO);
				usedArmies.add(armyConsume);
			}
		}

		List<ArmyConsume> killedArmies = Lists.newArrayList();
		List<ArmyVO> killedArmiesList = battleResultVO.getKilledDefenderDonatedArmiesList();
		if (CollectionUtils.isNotEmpty(killedArmiesList)) {
			for (ArmyVO armyVO : killedArmiesList) {
				ArmyConsume armyConsume = buildArmyConsume(userRaceId, armyVO);
				killedArmies.add(armyConsume);
			}
		}

		List<ResourceRecord> resourceRecords = Lists.newArrayList();
		List<ResourceVO> stolenResourcesList = battleResultVO.getStolenResourcesList();
		if (CollectionUtils.isNotEmpty(stolenResourcesList)) {
			for (ResourceVO resourceVO : stolenResourcesList) {
				ResourceRecord resourceRecord = buildResourceRecord(userRaceId, resourceVO);
				resourceRecords.add(resourceRecord);
			}
		}

		attack.setUsedSkills(skillConsumeList);
		attack.setUsedArmies(usedArmies);
		attack.setKilledDefenderDonatedArmies(killedArmies);
		attack.setStolenResources(resourceRecords);
		attack.setRewardCrown(battleResultVO.getRewardCrown());
		attack.setRewardGoldByCrownLevel(battleResultVO.getRewardGoldByCrownLevel());
		attack.setRewardOilByCrownLevel(battleResultVO.getRewardOilByCrownLevel());
		attack.setTimestamp(battleResultVO.getTimestamp());
		attack.setPeerId(battleResultVO.getPeerId());
		UserRace userRace = userRaceCommonService.getUserRace(attack.getPeerId());
		attack.setPeerName(userRace.getNickName());
		return attack;
	}

	private SkillConsume buildSkillConsume(Long userRaceId, SkillVOProtocal.SkillVO skillVO) {
		SkillConsume skillConsume = new SkillConsume();
		skillConsume.setUserRaceId(userRaceId);
		skillConsume.setCid(skillVO.getCid());
		skillConsume.setAmount(skillVO.getAmount());
		return skillConsume;
	}

	private Replay buildReplay(Long userRaceId, BattleReplayVO battleReplayVO) throws CoreException {
		if (battleReplayVO == null) {
			return null;
		}
		Replay replay = new Replay();
		replay.setUserRaceId(userRaceId);
		replay.setBattleType(BattleType.ATTACK);
		replay.setReplayByte(battleReplayVO.toByteArray());
		return replay;
	}

	private ArmyConsume buildArmyConsume(Long userRaceId, ArmyVO armyVO) throws CoreException {
		ArmyConsume armyConsume = new ArmyConsume();
		armyConsume.setUserRaceId(userRaceId);
		armyConsume.setCid(armyVO.getCid());
		armyConsume.setAmount(armyVO.getAmount());
		return armyConsume;
	}

	private ResourceRecord buildResourceRecord(Long userRaceId, ResourceVO resourceVO) throws CoreException {
		ResourceRecord resourceRecord = new ResourceRecord();
		resourceRecord.setUserRaceId(userRaceId);
		ResourceType resourceType = resourceVO.getResourceType();
		ResourceServerTypeEnum resourceServcerType = ResourceServerTypeEnum.getResourceServerTypeEnumByName(resourceType.toString());
		resourceRecord.setResourceType(resourceServcerType);
		resourceRecord.setCount(resourceVO.getResourceCount());
		return resourceRecord;
	}

}
