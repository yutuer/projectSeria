package com.pureland.core.init;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import com.pureland.common.db.dao.daoInterface.BuildingLimitModelDAOInterface;
import com.pureland.common.db.dao.mysql.BuildingLimitModelDAO;
import com.pureland.common.db.error.DBException;
import com.pureland.common.db.statics.BuildingLimitModel;
import com.pureland.common.util.SpringContextUtil;

import java.util.List;

/**
 * Created by Administrator on 2015/1/27.
 */
public class BuildingLimitModelHelper {
    private BuildingLimitModelDAOInterface buildingLimitModelDAO = (BuildingLimitModelDAOInterface) SpringContextUtil.getBean(BuildingLimitModelDAO.class.getSimpleName());
    public static final Table<Integer, Integer, BuildingLimitModel> ENTITIES = HashBasedTable.create();

    public void init() {
        try {
            List<BuildingLimitModel> buildingLimitModelList = buildingLimitModelDAO.queryBuildingLimitModelList();
            for (BuildingLimitModel buildingLimitModel : buildingLimitModelList) {
                ENTITIES.put(buildingLimitModel.getBaseId(), buildingLimitModel.getBuildingBaseId(), buildingLimitModel);
            }
        } catch (DBException e) {
            e.printStackTrace();
        }
    }
}
