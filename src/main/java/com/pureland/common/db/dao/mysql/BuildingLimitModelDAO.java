package com.pureland.common.db.dao.mysql;

import java.util.List;

import com.google.common.collect.Lists;
import com.pureland.common.db.dao.daoInterface.BuildingLimitModelDAOInterface;
import com.pureland.common.db.error.DBException;
import com.pureland.common.util.parseExcel.ReadExcelData;

import com.pureland.common.db.statics.BuildingLimitModel;
import org.apache.ibatis.session.SqlSession;

public class BuildingLimitModelDAO extends MysqlDAO implements BuildingLimitModelDAOInterface {

    @Override
    public List<BuildingLimitModel> queryBuildingLimitModelList() throws DBException {
        List<BuildingLimitModel> buildingLimitModels = Lists.newArrayList();
        SqlSession session = null;
        try {
            session = getSession();
            buildingLimitModels = session.selectList("queryBuildingLimitModelList");
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return buildingLimitModels;
    }

}
