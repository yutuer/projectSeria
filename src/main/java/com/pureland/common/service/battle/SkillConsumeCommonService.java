package com.pureland.common.service.battle;

import com.pureland.common.db.data.battle.SkillConsume;
import com.pureland.common.enums.BattleType;
import com.pureland.common.error.CoreException;

import java.util.List;

public interface SkillConsumeCommonService {

    public Long addSkillConsume(SkillConsume skillConsume) throws CoreException;

    public void addBatchSkillConsume(List<SkillConsume> skillConsumes) throws CoreException;

    public List<SkillConsume> getSkillConsumes(Long userRaceId, Long battleId, BattleType battleType) throws CoreException;
}
