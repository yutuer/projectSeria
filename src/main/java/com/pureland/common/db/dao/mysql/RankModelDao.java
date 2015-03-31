package com.pureland.common.db.dao.mysql;

import com.google.common.collect.Lists;
import com.pureland.common.db.dao.daoInterface.RankModelDaoInterface;
import com.pureland.common.db.error.DBException;
import com.pureland.common.db.statics.RankModel;
import com.pureland.common.util.parseExcel.ReadExcelData;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

/**
 * Created by Administrator on 2015/1/27.
 */
public class RankModelDao extends MysqlDAO implements RankModelDaoInterface {
    @Override
    public List<RankModel> queryRankModelList() throws DBException {
        List<RankModel> rankModels = Lists.newArrayList();
        SqlSession session = null;
        try {
            session = getSession();
            rankModels = session.selectList("queryRankModelList");
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return rankModels;
    }

}
