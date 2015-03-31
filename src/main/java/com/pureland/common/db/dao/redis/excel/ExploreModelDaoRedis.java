package com.pureland.common.db.dao.redis.excel;

import com.pureland.common.db.dao.daoInterface.ExploreModelDAOInterface;
import com.pureland.common.db.error.DBException;
import com.pureland.common.db.statics.ExploreModel;
import com.pureland.common.util.parseExcel.ReadExcelData;

import java.util.List;

public class ExploreModelDaoRedis implements ExploreModelDAOInterface {

	@Override
	public List<ExploreModel> queryExploreModelList() throws DBException {
		return ReadExcelData.GetExcelData("ExploreModel", "ExploreModel");
	}
}
