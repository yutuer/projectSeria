package com.pureland.common.db.dao.daoInterface;

import com.pureland.common.db.error.DBException;
import com.pureland.common.db.statics.ArmoryModel;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015/3/3.
 */
public interface ArmoryModelDAOInterface {
	public Map<Integer, List<ArmoryModel>> queryArmoryModeForMap() throws DBException;
}
