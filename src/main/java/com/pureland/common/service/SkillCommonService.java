package com.pureland.common.service;

import com.pureland.common.db.data.Skill;
import com.pureland.common.error.CoreException;

import java.util.List;

public interface SkillCommonService {

    public Long addSkill(Long userRaceId, Integer cid, Integer amount, Integer totalAmount) throws CoreException;

    public Skill getSkill(Long skillId) throws CoreException;

    public List<Skill> getSkills(Long userRaceId) throws CoreException;

    public void updateSkill(Skill skill) throws CoreException;

    void upgradeAllSkills(Long userRaceId, Integer skillCid, Integer upgradeId) throws CoreException;

    void updateBattleSkill(List<Skill> skills) throws CoreException;
}
