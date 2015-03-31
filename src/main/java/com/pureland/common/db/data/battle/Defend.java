package com.pureland.common.db.data.battle;

import java.util.List;

import com.pureland.common.enums.Entity;
import com.pureland.common.util.DataObject;
import org.apache.commons.lang3.ArrayUtils;

/**
 * @author qinpeirong
 */
public class Defend extends DataObject {

    /**
     *
     */
    private static final long serialVersionUID = -4066909175415789418L;
    private Long id;
    private Long userRaceId;
    private Integer percentage;
    private Integer star;
    private Boolean useDonatedArmy;
    private List<Long> brokenTraps;
    private List<SkillConsume> usedSkills;
    private List<ArmyConsume> usedArmies;
    private List<ArmyConsume> killedDefenderDonatedArmies;
    private List<ResourceRecord> stolenResources;
    private Integer rewardMedal;
    private Integer rewardCrown;
    private Integer rewardGoldByCrownLevel;
    private Integer rewardOilByCrownLevel;
    private Long timestamp;
    private Long peerId;
    private String peerName;

    public static String generatorFieldKey(Long id, String field) {
        return generatorFieldKey(Entity.DEFEND, id, field);
    }

    public String getPeerName() {
        return peerName;
    }

    public void setPeerName(String peerName) {
        this.peerName = peerName;
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the userRaceId
     */
    public Long getUserRaceId() {
        return userRaceId;
    }

    public List<Long> getBrokenTraps() {
        return brokenTraps;
    }

    public void setBrokenTraps(List<Long> brokenTraps) {
        this.brokenTraps = brokenTraps;
    }

    public List<SkillConsume> getUsedSkills() {
        return usedSkills;
    }

    public void setUsedSkills(List<SkillConsume> usedSkills) {
        this.usedSkills = usedSkills;
    }

    /**
     * @param userRaceId the userRaceId to set
     */
    public void setUserRaceId(Long userRaceId) {
        this.userRaceId = userRaceId;
    }

    /**
     * @return the percentage
     */
    public Integer getPercentage() {
        return percentage;
    }

    /**
     * @param percentage the percentage to set
     */
    public void setPercentage(Integer percentage) {
        this.percentage = percentage;
    }

    /**
     * @return the star
     */
    public Integer getStar() {
        return star;
    }

    /**
     * @param star the star to set
     */
    public void setStar(Integer star) {
        this.star = star;
    }

    /**
     * @return the useDonatedArmy
     */
    public Boolean getUseDonatedArmy() {
        return useDonatedArmy;
    }

    /**
     * @param useDonatedArmy the useDonatedArmy to set
     */
    public void setUseDonatedArmy(Boolean useDonatedArmy) {
        this.useDonatedArmy = useDonatedArmy;
    }

    /**
     * @return the usedArmies
     */
    public List<ArmyConsume> getUsedArmies() {
        return usedArmies;
    }

    /**
     * @param usedArmies the usedArmies to set
     */
    public void setUsedArmies(List<ArmyConsume> usedArmies) {
        this.usedArmies = usedArmies;
    }

    public void setUsedArmies(Long userRaceId, List<ArmyConsume> usedArmies) {
        this.setUsedArmies(usedArmies);
        List<ArmyConsume> armyConsumes = this.getUsedArmies();
        for (ArmyConsume armyConsume : armyConsumes) {
            armyConsume.setUserRaceId(userRaceId);
        }
    }

    /**
     * @return the killedDefenderDonatedArmies
     */
    public List<ArmyConsume> getKilledDefenderDonatedArmies() {
        return killedDefenderDonatedArmies;
    }

    /**
     * @param killedDefenderDonatedArmies the killedDefenderDonatedArmies to set
     */
    public void setKilledDefenderDonatedArmies(
            List<ArmyConsume> killedDefenderDonatedArmies) {
        this.killedDefenderDonatedArmies = killedDefenderDonatedArmies;
    }

    public void setKilledDefenderDonatedArmies(Long userRaceId, List<ArmyConsume> killedDefenderDonatedArmies) {
        this.setKilledDefenderDonatedArmies(killedDefenderDonatedArmies);
        List<ArmyConsume> armyConsumes = this.getKilledDefenderDonatedArmies();
        for (ArmyConsume armyConsume : armyConsumes) {
            armyConsume.setUserRaceId(userRaceId);
        }
    }

    /**
     * @return the stolenResources
     */
    public List<ResourceRecord> getStolenResources() {
        return stolenResources;
    }

    /**
     * @param stolenResources the stolenResources to set
     */
    public void setStolenResources(List<ResourceRecord> stolenResources) {
        this.stolenResources = stolenResources;
    }

    /**
     * @return the rewardMedal
     */
    public Integer getRewardMedal() {
        return rewardMedal;
    }

    /**
     * @param rewardMedal the rewardMedal to set
     */
    public void setRewardMedal(Integer rewardMedal) {
        this.rewardMedal = rewardMedal;
    }

    /**
     * @return the rewardCrown
     */
    public Integer getRewardCrown() {
        return rewardCrown;
    }

    /**
     * @param rewardCrown the rewardCrown to set
     */
    public void setRewardCrown(Integer rewardCrown) {
        this.rewardCrown = rewardCrown;
    }

    /**
     * @return the rewardGoldByCrownLevel
     */
    public Integer getRewardGoldByCrownLevel() {
        return rewardGoldByCrownLevel;
    }

    /**
     * @param rewardGoldByCrownLevel the rewardGoldByCrownLevel to set
     */
    public void setRewardGoldByCrownLevel(Integer rewardGoldByCrownLevel) {
        this.rewardGoldByCrownLevel = rewardGoldByCrownLevel;
    }

    /**
     * @return the rewardOilByCrownLevel
     */
    public Integer getRewardOilByCrownLevel() {
        return rewardOilByCrownLevel;
    }

    /**
     * @param rewardOilByCrownLevel the rewardOilByCrownLevel to set
     */
    public void setRewardOilByCrownLevel(Integer rewardOilByCrownLevel) {
        this.rewardOilByCrownLevel = rewardOilByCrownLevel;
    }

    /**
     * @return the timestamp
     */
    public Long getTimestamp() {
        return timestamp;
    }

    /**
     * @param timestamp the timestamp to set
     */
    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * @return the peerId
     */
    public Long getPeerId() {
        return peerId;
    }

    /**
     * @param peerId the peerId to set
     */
    public void setPeerId(Long peerId) {
        this.peerId = peerId;
    }


}
