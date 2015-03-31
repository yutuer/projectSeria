package com.pureland.common.db.dao.daoInterface;

import com.pureland.common.db.error.DBException;
import com.pureland.common.db.statics.ExploreModel;

import java.util.List;

/**
 * Created by Administrator on 2015/3/9.
 */
public interface ExploreModelDAOInterface {
    public List<ExploreModel> queryExploreModelList() throws DBException;
}
