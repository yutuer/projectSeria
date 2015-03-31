package com.pureland.core.service.impl;

import com.google.common.collect.Lists;
import com.pureland.common.db.data.Army;
import com.pureland.common.db.data.Resource;
import com.pureland.common.db.data.Skill;
import com.pureland.common.db.data.UserRace;
import com.pureland.common.db.data.battle.*;
import com.pureland.common.enums.BattleType;
import com.pureland.common.enums.ResourceServerTypeEnum;
import com.pureland.common.error.CoreException;
import com.pureland.common.service.ArmyExpCommonService;
import com.pureland.common.service.BuildingCommonService;
import com.pureland.common.service.SkillCommonService;
import com.pureland.common.service.UserRaceCommonService;
import com.pureland.common.service.impl.*;
import com.pureland.common.util.SpringContextUtil;
import com.pureland.core.service.ArmyService;
import com.pureland.core.service.BattleService;
import com.pureland.common.service.ResourceCommonService;
import com.pureland.core.service.UserExtService;
import com.pureland.core.service.battle.AttackService;
import com.pureland.core.service.battle.DefendService;
import com.pureland.core.service.battle.ReplayService;
import com.pureland.core.service.battle.impl.AttackServiceImpl;
import com.pureland.core.service.battle.impl.DefendServiceImpl;
import com.pureland.core.service.battle.impl.ReplayServiceImpl;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.List;

/**
 * @author qinpeirong
 */
public class BattleServiceImpl implements BattleService {
    private AttackService attackService = (AttackService) SpringContextUtil.getBean(AttackServiceImpl.class.getSimpleName());
    private DefendService defendService = (DefendService) SpringContextUtil.getBean(DefendServiceImpl.class.getSimpleName());
    private ReplayService replayService = (ReplayService) SpringContextUtil.getBean(ReplayServiceImpl.class.getSimpleName());
    private ArmyService armyService = (ArmyService) SpringContextUtil.getBean(ArmyServiceImpl.class.getSimpleName());
    private ResourceCommonService resourceCommonService = (ResourceCommonService) SpringContextUtil.getBean(ResourceCommonServiceImpl.class.getSimpleName());
    private UserExtService userExtService = (UserExtService) SpringContextUtil.getBean(UserExtServiceImpl.class.getSimpleName());
    private ThreadPoolTaskExecutor taskExecutor = (ThreadPoolTaskExecutor) SpringContextUtil.getBean("taskExecutor");
    private ArmyExpCommonService armyExpCommonService = (ArmyExpCommonService) SpringContextUtil.getBean(ArmyExpCommonServiceImpl.class.getSimpleName());
    private BuildingCommonService buildingCommonService = (BuildingCommonService) SpringContextUtil.getBean(BuildingCommonServiceImpl.class.getSimpleName());
    private SkillCommonService skillCommonService = (SkillCommonService) SpringContextUtil.getBean(SkillCommonServiceImpl.class.getSimpleName());
    private UserRaceCommonService userRaceCommonService = (UserRaceCommonService) SpringContextUtil.getBean(UserRaceCommonServiceImpl.class.getSimpleName());

    @Override
    public void battleResult(final Attack attack, final Replay replay) throws CoreException {
        Long attackId = attackService.addAttackResult(attack);
        replayService.addRelay(attackId, replay);
        resultForAttack(attack);
        taskExecutor.execute(new Runnable() {

            @Override
            public void run() {
                try {
                    Defend defend = buildDefend(attack);
                    Long defendId = defendService.addDefendResult(defend);
                    replay.setUserRaceId(defend.getUserRaceId());
                    replay.setBattleType(BattleType.DEFEND);
                    replayService.addRelay(defendId, replay);
                    resultForDefend(defend);
                } catch (CoreException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });
    }

    private Defend buildDefend(Attack attack) throws CoreException {
        Defend defend = new Defend();
        defend.setUserRaceId(attack.getPeerId());
        defend.setPercentage(attack.getPercentage());
        defend.setStar(attack.getStar());
        defend.setUseDonatedArmy(attack.getUseDonatedArmy());
        defend.setUsedArmies(attack.getPeerId(), attack.getUsedArmies());
        defend.setKilledDefenderDonatedArmies(attack.getPeerId(), attack.getKilledDefenderDonatedArmies());
        defend.setRewardMedal(attack.getRewardMedal());
        defend.setRewardCrown(attack.getRewardCrown());
        defend.setRewardGoldByCrownLevel(attack.getRewardGoldByCrownLevel());
        defend.setRewardOilByCrownLevel(attack.getRewardOilByCrownLevel());
        defend.setTimestamp(attack.getTimestamp());
        defend.setPeerId(attack.getUserRaceId());
        UserRace userRace = userRaceCommonService.getUserRace(defend.getPeerId());
        defend.setPeerName(userRace.getNickName());
        defend.setBrokenTraps(attack.getBrokenTraps());

        List<ResourceRecord> resources = Lists.newArrayList();
        List<ResourceRecord> stolenResources = attack.getStolenResources();
        for (ResourceRecord record : stolenResources) {
            record.setUserRaceId(attack.getPeerId());
            Integer count = record.getCount();
            record.setCount(-count);
            resources.add(record);
        }
        defend.setStolenResources(resources);
        return defend;
    }

    private List<Army> buildArmy(List<ArmyConsume> armyConsumes) throws CoreException {
        List<Army> armies = Lists.newArrayList();
        if (CollectionUtils.isNotEmpty(armyConsumes)) {
            for (ArmyConsume armyConsume : armyConsumes) {
                Army army = new Army();
                army.setUserRaceId(armyConsume.getUserRaceId());
                Long armyExpId = armyExpCommonService.getArmyExpId(armyConsume.getUserRaceId(), armyConsume.getCid());
                army.setArmyExpId(armyExpId);
                army.setAmount(armyConsume.getAmount());
                armies.add(army);
            }
        }
        return armies;
    }

    private List<Resource> buildResource(List<ResourceRecord> resourceRecords,
                                         Integer rewardGoldByCrownLevel, Integer rewardOilByCrownLevel) throws CoreException {
        List<Resource> resources = Lists.newArrayList();
        boolean isHasGold = false;
        boolean isHasOil = false;
        if (CollectionUtils.isNotEmpty(resourceRecords)) {
            for (ResourceRecord resourceRecord : resourceRecords) {
                ResourceServerTypeEnum resourceType = resourceRecord.getResourceType();
                Integer count = resourceRecord.getCount();
                Resource resource = new Resource();
                resource.setResourceType(resourceType);

                if (rewardGoldByCrownLevel != null && ResourceServerTypeEnum.Gold.name().equals(resourceType.name())) {
                    resource.setCount(count + rewardGoldByCrownLevel);
                    isHasGold = true;
                } else if (rewardOilByCrownLevel != null && ResourceServerTypeEnum.Oil.name().equals(resourceType.name())) {
                    resource.setCount(count + rewardOilByCrownLevel);
                    isHasOil = true;
                } else {
                    resource.setCount(count);
                }
                resources.add(resource);
            }
        }
        if (rewardGoldByCrownLevel != null && !isHasGold) {
            Resource resource = new Resource();
            resource.setResourceType(ResourceServerTypeEnum.Gold);
            resource.setCount(rewardGoldByCrownLevel);
            resources.add(resource);
        }
        if (rewardOilByCrownLevel != null && !isHasOil) {
            Resource resource = new Resource();
            resource.setResourceType(ResourceServerTypeEnum.Oil);
            resource.setCount(rewardOilByCrownLevel);
            resources.add(resource);
        }
        return resources;
    }

    @Override
    public List<Attack> getAttacks(Long userRaceId) throws CoreException {
        return attackService.getAttacks(userRaceId);
    }

    @Override
    public List<Defend> getDefends(Long userRaceId) throws CoreException {
        return defendService.getDefends(userRaceId);
    }

    @Override
    public void resultForAttack(Attack attack) throws CoreException {
        Long userRaceId = attack.getUserRaceId();
        Boolean useDonatedArmy = attack.getUseDonatedArmy();
        Integer rewardMedal = attack.getRewardMedal();
        Integer star = attack.getStar();
        Integer rewardCrown = star > 0 ? attack.getRewardCrown() : -1 * attack.getRewardCrown();
        Integer rewardGoldByCrownLevel = attack.getRewardGoldByCrownLevel();
        Integer rewardOilByCrownLevel = attack.getRewardOilByCrownLevel();
        List<ResourceRecord> stolenResources = attack.getStolenResources();
        List<SkillConsume> skillConsumeList = attack.getUsedSkills();
        List<ArmyConsume> usedArmies = attack.getUsedArmies();
        List<Skill> skills = buildSkill(skillConsumeList);
        List<Army> armies = buildArmy(usedArmies);
        List<Resource> resources = buildResource(stolenResources, rewardGoldByCrownLevel, rewardOilByCrownLevel);

        if (useDonatedArmy) {
            //TODO clear donated army queue
        }
        skillCommonService.updateBattleSkill(skills);
        armyService.updateBattleArmy(armies);
        resourceCommonService.updateBattleResouce(userRaceId, resources);
        userExtService.updateCrown(userRaceId, rewardCrown);
    }

    private List<Skill> buildSkill(List<SkillConsume> skillConsumeList) {
        List<Skill> skills = Lists.newArrayList();
        if (CollectionUtils.isNotEmpty(skillConsumeList)) {
            for (SkillConsume skillConsume : skillConsumeList) {
                Skill skill = new Skill();
                skill.setUserRaceId(skillConsume.getUserRaceId());
                skill.setCid(skillConsume.getCid());
                skill.setAmount(skillConsume.getAmount());
                skills.add(skill);
            }
        }
        return skills;
    }

    @Override
    public void resultForDefend(Defend defend) throws CoreException {
        Long userRaceId = defend.getUserRaceId();
        Integer rewardCrown = -defend.getRewardCrown();
        List<ResourceRecord> stolenResources = defend.getStolenResources();
        List<ArmyConsume> killedDefenderDonatedArmies = defend.getKilledDefenderDonatedArmies();
        List<Long> brokenTraps = defend.getBrokenTraps();

        if (CollectionUtils.isNotEmpty(killedDefenderDonatedArmies)) {
            //TODO clear donated army queue
        }
        List<Resource> resources = buildResource(stolenResources, null, null);
        resourceCommonService.updateBattleResouce(userRaceId, resources);
        userExtService.updateCrown(userRaceId, rewardCrown);
        buildingCommonService.updateBrokenTraps(userRaceId, brokenTraps);
    }

}
