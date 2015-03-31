package com.pureland.common.service;

import com.pureland.common.db.data.ArmyExp;
import com.pureland.common.error.CoreException;

import java.util.Set;

/**
 * Created by Administrator on 2015/2/10.
 */
public interface ArmyExpCommonService {

    public Long getArmyExpId(Long userRaceId, Integer cid) throws CoreException;

    public ArmyExp getArmyExp(Long userRaceId, Integer cid) throws CoreException;

    public Long getArmyExpIdByCid(Long userRaceId, Integer cid) throws CoreException;

    public ArmyExp getArmyExp(Long armyExpId) throws CoreException;

    public Set<ArmyExp> getArmyExps(Long userRaceId) throws CoreException;

    public int addArmyExpExp(long userRaceId, Integer cid, Integer exp) throws CoreException;

    public int addArmyExpExp(Long armyExpId, Integer exp) throws CoreException;

    void upgradeCid(Long userRaceId, Integer cid, Integer upgradeId) throws CoreException;
}
