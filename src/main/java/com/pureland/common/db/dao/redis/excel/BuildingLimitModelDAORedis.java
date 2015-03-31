package com.pureland.common.db.dao.redis.excel;

import java.util.List;

import com.pureland.common.db.dao.daoInterface.BuildingLimitModelDAOInterface;
import com.pureland.common.db.error.DBException;
import com.pureland.common.db.statics.BuildingLimitModel;
import com.pureland.common.util.parseExcel.ReadExcelData;

public class BuildingLimitModelDAORedis implements BuildingLimitModelDAOInterface {

	@Override
	public List<BuildingLimitModel> queryBuildingLimitModelList() throws DBException {
		return ReadExcelData.GetExcelData("BuildingLimitModel", "BuildingLimitModel");
	}
}
