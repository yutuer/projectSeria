package com.pureland.core.service;

import java.util.List;

import com.pureland.common.db.data.battle.Attack;
import com.pureland.common.db.data.battle.Defend;
import com.pureland.common.db.data.battle.Replay;
import com.pureland.common.error.CoreException;

/**
 * 
 * @author qinpeirong
 *
 */
public interface BattleService {
    public void battleResult(final Attack attack, final Replay replay) throws CoreException;
    
    public List<Attack> getAttacks(Long userRaceId) throws CoreException;
    
    public List<Defend> getDefends(Long userRaceId) throws CoreException;

    public void resultForAttack(Attack attack) throws CoreException;

    public void resultForDefend(Defend defend) throws CoreException;
    
}
