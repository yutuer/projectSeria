package com.pureland.common.db.dao.daoInterface;

import com.pureland.common.db.error.DBException;
import com.pureland.common.db.statics.BuildingLimitModel;

import java.util.List;

/**
 * Created by Administrator on 2015/3/3.
 */
public interface BuildingLimitModelDAOInterface {
    public List<BuildingLimitModel> queryBuildingLimitModelList() throws DBException;
}
