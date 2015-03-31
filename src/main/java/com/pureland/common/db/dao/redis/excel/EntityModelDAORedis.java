package com.pureland.common.db.dao.redis.excel;

import java.util.List;

import com.pureland.common.db.dao.daoInterface.EntityModelDAOInterface;
import com.pureland.common.db.error.DBException;
import com.pureland.common.db.statics.EntityModel;
import com.pureland.common.util.parseExcel.ReadExcelData;

public class EntityModelDAORedis implements EntityModelDAOInterface {

	@Override
	public List<EntityModel> queryEntityModelList() throws DBException {
		return ReadExcelData.GetExcelData("EntityModel", "EntityModel");
	}
}
