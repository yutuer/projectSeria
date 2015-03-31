package com.pureland.core.handler.api;

import java.util.List;

import com.pureland.common.enums.EnumHelper;
import com.pureland.common.enums.ResourceServerTypeEnum;
import org.apache.commons.collections.CollectionUtils;

import com.google.common.collect.Lists;
import com.pureland.common.db.data.battle.ArmyConsume;
import com.pureland.common.db.data.battle.Attack;
import com.pureland.common.db.data.battle.Defend;
import com.pureland.common.db.data.battle.ResourceRecord;
import com.pureland.common.enums.BattleType;
import com.pureland.common.error.CoreException;
import com.pureland.common.log.PurelandLog;
import com.pureland.common.protocal.BattleHistoryRespProtocal.BattleHistoryResp;
import com.pureland.common.protocal.BattleHistoryRespProtocal;
import com.pureland.common.protocal.RespWrapperProtocal;
import com.pureland.common.protocal.ReqWrapperProtocal.ReqWrapper;
import com.pureland.common.protocal.RespWrapperProtocal.RespWrapper;
import com.pureland.common.protocal.vo.ArmyVOProtocal.ArmyVO;
import com.pureland.common.protocal.vo.ArmyVOProtocal;
import com.pureland.common.protocal.vo.BattleResultVOProtocal;
import com.pureland.common.protocal.vo.ResourceTypeProtocal.ResourceType;
import com.pureland.common.protocal.vo.ResourceVOProtocal;
import com.pureland.common.protocal.vo.BattleResultVOProtocal.BattleResultVO;
import com.pureland.common.protocal.vo.BattleResultVOProtocal.BattleResultVO.Builder;
import com.pureland.common.protocal.vo.ResourceVOProtocal.ResourceVO;
import com.pureland.common.util.SpringContextUtil;
import com.pureland.core.handler.RequestAPIHandler;
import com.pureland.core.service.BattleService;
import com.pureland.core.service.battle.ReplayService;
import com.pureland.core.service.battle.impl.ReplayServiceImpl;
import com.pureland.core.service.impl.BattleServiceImpl;

/**
 * @author qinpeirong
 */
public class BattleHistoryHandler extends RequestAPIHandler {
    private BattleService battleService = (BattleService) SpringContextUtil.getBean(BattleServiceImpl.class.getSimpleName());
    private ReplayService replayService = (ReplayService) SpringContextUtil.getBean(ReplayServiceImpl.class.getSimpleName());

    @Override
    public RespWrapper handler(ReqWrapper reqWrapper, String authToken,
                               Long timestamp) throws CoreException {
        Long userId = authUser(authToken);
        Long userRaceId = getLastUserRaceId(userId);
        List<Attack> attacks = battleService.getAttacks(userRaceId);
        List<Defend> defends = battleService.getDefends(userRaceId);
        BattleHistoryResp battleHistoryResp = buildBattleHistoryResp(attacks, defends);
        RespWrapper respWrapper = RespWrapperProtocal.RespWrapper.newBuilder().setBattleHistoryResp(battleHistoryResp).build();
        PurelandLog.info(respWrapper.toString());
        return respWrapper;
    }

    private BattleHistoryResp buildBattleHistoryResp(List<Attack> attacks, List<Defend> defends) throws CoreException {
        List<BattleResultVO> attackVOS = buildAttackVO(attacks);
        List<BattleResultVO> defendVOS = buildDefendVO(defends);
        com.pureland.common.protocal.BattleHistoryRespProtocal.BattleHistoryResp.Builder builder = BattleHistoryRespProtocal.BattleHistoryResp.newBuilder();
        for (int i = 0; i < attackVOS.size(); i++) {
            BattleResultVO battleResultVO = attackVOS.get(i);
            builder.addAttackBattleHistories(battleResultVO);
        }

        for (int i = 0; i < defendVOS.size(); i++) {
            BattleResultVO battleResultVO = defendVOS.get(i);
            builder.addDefenseBattleHistories(battleResultVO);
        }
        return builder.build();
    }

    private List<BattleResultVO> buildAttackVO(List<Attack> attacks) throws CoreException {
        List<BattleResultVO> battleResultVOS = Lists.newArrayList();
        if (CollectionUtils.isNotEmpty(attacks)) {
            for (Attack attack : attacks) {
                Builder builder = BattleResultVOProtocal.BattleResultVO.newBuilder().setPercentage(attack.getPercentage())
                        .setStar(attack.getStar())
                        .setUseDonatedArmy(attack.getUseDonatedArmy())
                        .setRewardCrown(attack.getRewardCrown())
                        .setRewardGoldByCrownLevel(attack.getRewardGoldByCrownLevel())
                        .setRewardOilByCrownLevel(attack.getRewardOilByCrownLevel())
                        .setTimestamp(attack.getTimestamp())
                        .setPeerId(attack.getPeerId()).setPeerName(attack.getPeerName());
                Long replayId = replayService.getReplayId(attack.getUserRaceId(), attack.getId(), BattleType.ATTACK);
                builder.setBattleReplayId(replayId);

                List<ArmyConsume> usedArmies = attack.getUsedArmies();
                List<ArmyConsume> killedDefenderDonatedArmies = attack.getKilledDefenderDonatedArmies();
                List<ResourceRecord> stolenResources = attack.getStolenResources();
                List<ArmyVO> usedArmyVOS = buildArmyVOS(usedArmies);
                List<ArmyVO> killedDefenderDonatedArmyVOS = buildArmyVOS(killedDefenderDonatedArmies);
                List<ResourceVO> stolenResourceVOS = buildResourceVOS(stolenResources);

                for (int i = 0; i < usedArmyVOS.size(); i++) {
                    ArmyVO armyVO = usedArmyVOS.get(i);
                    builder.addUsedArmies(armyVO);
                }

                for (int i = 0; i < killedDefenderDonatedArmyVOS.size(); i++) {
                    ArmyVO armyVO = killedDefenderDonatedArmyVOS.get(i);
                    builder.addKilledDefenderDonatedArmies(armyVO);
                }

                for (int i = 0; i < stolenResourceVOS.size(); i++) {
                    ResourceVO resourceVO = stolenResourceVOS.get(i);
                    builder.addStolenResources(resourceVO);
                }

                BattleResultVO battleResultVO = builder.build();
                battleResultVOS.add(battleResultVO);
            }
        }
        return battleResultVOS;
    }

    private List<BattleResultVO> buildDefendVO(List<Defend> defends) throws CoreException {
        List<BattleResultVO> battleResultVOS = Lists.newArrayList();
        if (CollectionUtils.isNotEmpty(defends)) {
            for (Defend defend : defends) {
                Builder builder = BattleResultVOProtocal.BattleResultVO.newBuilder().setPercentage(defend.getPercentage())
                        .setStar(defend.getStar())
                        .setUseDonatedArmy(defend.getUseDonatedArmy())
                        .setRewardCrown(defend.getRewardCrown())
                        .setRewardGoldByCrownLevel(defend.getRewardGoldByCrownLevel())
                        .setRewardOilByCrownLevel(defend.getRewardOilByCrownLevel())
                        .setTimestamp(defend.getTimestamp())
                        .setPeerId(defend.getPeerId()).setPeerName(defend.getPeerName());
                Long replayId = replayService.getReplayId(defend.getUserRaceId(), defend.getId(), BattleType.DEFEND);
                builder.setBattleReplayId(replayId);

                List<ArmyConsume> usedArmies = defend.getUsedArmies();
                List<ArmyConsume> killedDefenderDonatedArmies = defend.getKilledDefenderDonatedArmies();
                List<ResourceRecord> stolenResources = defend.getStolenResources();
                List<ArmyVO> usedArmyVOS = buildArmyVOS(usedArmies);
                List<ArmyVO> killedDefenderDonatedArmyVOS = buildArmyVOS(killedDefenderDonatedArmies);
                List<ResourceVO> stolenResourceVOS = buildResourceVOS(stolenResources);

                for (int i = 0; i < usedArmyVOS.size(); i++) {
                    ArmyVO armyVO = usedArmyVOS.get(i);
                    builder.addUsedArmies(armyVO);
                }

                for (int i = 0; i < killedDefenderDonatedArmyVOS.size(); i++) {
                    ArmyVO armyVO = killedDefenderDonatedArmyVOS.get(i);
                    builder.addKilledDefenderDonatedArmies(armyVO);
                }

                for (int i = 0; i < stolenResourceVOS.size(); i++) {
                    ResourceVO resourceVO = stolenResourceVOS.get(i);
                    builder.addStolenResources(resourceVO);
                }

                BattleResultVO battleResultVO = builder.build();
                battleResultVOS.add(battleResultVO);
            }
        }
        return battleResultVOS;
    }

    private List<ArmyVO> buildArmyVOS(List<ArmyConsume> armys) throws CoreException {
        List<ArmyVO> armyVOS = Lists.newArrayList();
        if (CollectionUtils.isEmpty(armys)) {
            return armyVOS;
        }
        for (ArmyConsume armyConsume : armys) {
            ArmyVO armyVO = ArmyVOProtocal.ArmyVO.newBuilder().setCid(armyConsume.getCid())
                    .setAmount(armyConsume.getAmount())
                    .build();
            armyVOS.add(armyVO);
        }
        return armyVOS;
    }

    private List<ResourceVO> buildResourceVOS(List<ResourceRecord> stolenResources) throws CoreException {
        List<ResourceVO> resourceVOS = Lists.newArrayList();
        for (ResourceRecord record : stolenResources) {
            ResourceServerTypeEnum resourceServerType = record.getResourceType();
            ResourceType resourceType = EnumHelper.getProtocalResourceType(resourceServerType);
            ResourceVO resourceVO = ResourceVOProtocal.ResourceVO.newBuilder().setResourceType(resourceType)
                    .setResourceCount(record.getCount())
                    .build();
            resourceVOS.add(resourceVO);
        }
        return resourceVOS;
    }

}
