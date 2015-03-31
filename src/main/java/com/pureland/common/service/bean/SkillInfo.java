package com.pureland.common.service.bean;

/**
 * Created by Administrator on 2015/2/9.
 */
public class SkillInfo {
    private final Integer upgradeSkillCid; //空的时候为null
    private final Long upgradeSkillCompleteTime;

    public SkillInfo(Integer upgradeSkillCid, Long upgradeSkillCompleteTime) {
        this.upgradeSkillCid = upgradeSkillCid;
        this.upgradeSkillCompleteTime = upgradeSkillCompleteTime;
    }

    public Integer getUpgradeSkillCid() {
        return upgradeSkillCid;
    }

    public Long getUpgradeSkillCompleteTime() {
        return upgradeSkillCompleteTime;
    }
}
