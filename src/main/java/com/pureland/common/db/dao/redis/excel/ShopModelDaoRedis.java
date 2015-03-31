package com.pureland.common.db.dao.redis.excel;

import java.util.List;

import com.pureland.common.db.dao.daoInterface.ShopModelDaoInterface;
import com.pureland.common.db.error.DBException;
import com.pureland.common.db.statics.ShopModel;
import com.pureland.common.util.parseExcel.ReadExcelData;

public class ShopModelDaoRedis implements ShopModelDaoInterface {

	public List<ShopModel> queryShopModelList() throws DBException {
		return ReadExcelData.GetExcelData("ShopModel", "ShopModel");
	}
}
