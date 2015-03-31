package com.pureland.common.service.impl;

import com.pureland.common.db.dao.redis.ArmyExpDAO;
import com.pureland.common.db.data.ArmyExp;
import com.pureland.common.db.error.DBException;
import com.pureland.common.error.CoreException;
import com.pureland.common.service.ArmyExpCommonService;
import com.pureland.common.service.BaseService;
import com.pureland.common.util.SpringContextUtil;

import java.util.Set;

/**
 * Created by Administrator on 2015/2/10.
 */
public class ArmyExpCommonServiceImpl extends BaseService implements ArmyExpCommonService {

    private ArmyExpDAO armyExpDAO = (ArmyExpDAO) SpringContextUtil.getBean(ArmyExpDAO.class.getSimpleName());

    @Override
    public Long getArmyExpId(Long userRaceId, Integer cid) throws CoreException {
        try {
            return armyExpDAO.getArmyExpId(userRaceId, cid);
        } catch (DBException e) {
            error(e);
        }
        return null;
    }

    @Override
    public ArmyExp getArmyExp(Long userRaceId, Integer cid) throws CoreException {
        try {
            return armyExpDAO.getArmyExp(userRaceId, cid);
        } catch (DBException e) {
            error(e);
        }
        return null;
    }

    @Override
    public Long getArmyExpIdByCid(Long userRaceId, Integer cid) throws CoreException {
        try {
            return armyExpDAO.getArmyExpIdByCid(userRaceId, cid);
        } catch (DBException e) {
            error(e);
        }
        return null;
    }

    @Override
    public ArmyExp getArmyExp(Long armyExpId) throws CoreException {
        try {
            return armyExpDAO.getArmyExp(armyExpId);
        } catch (DBException e) {
            error(e);
        }
        return null;
    }

    @Override
    public Set<ArmyExp> getArmyExps(Long userRaceId) throws CoreException {
        try {
            return armyExpDAO.getArmyExps(userRaceId);
        } catch (DBException e) {
            error(e);
        }
        return null;
    }

    @Override
    public int addArmyExpExp(long userRaceId, Integer cid, Integer exp) throws CoreException {
        try {
            ArmyExp armyExp = armyExpDAO.getArmyExp(userRaceId, cid);
            return armyExpDAO.addArmyExpExp(armyExp.getId(), exp);
        } catch (DBException e) {
            error(e);
        }
        return 0;
    }

    @Override
    public int addArmyExpExp(Long armyExpId, Integer exp) throws CoreException {
        try {
            return armyExpDAO.addArmyExpExp(armyExpId, exp);
        } catch (DBException e) {
            error(e);
        }
        return 0;
    }

    @Override
    public void upgradeCid(Long userRaceId, Integer cid, Integer upgradeId) throws CoreException {
        try {
            armyExpDAO.upgradeCid(userRaceId, cid, upgradeId);
        } catch (DBException e) {
            error(e);
        }
    }
}
