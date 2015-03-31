package com.pureland.common.db.dao.daoInterface;

import com.pureland.common.db.error.DBException;
import com.pureland.common.db.statics.RankModel;

import java.util.List;

/**
 * Created by Administrator on 2015/3/3.
 */
public interface RankModelDaoInterface {
    public List<RankModel> queryRankModelList() throws DBException;
}
