package com.pureland.common.db.dao.redis.excel;

import java.util.List;

import com.pureland.common.db.dao.daoInterface.RankModelDaoInterface;
import com.pureland.common.db.statics.RankModel;
import com.pureland.common.util.parseExcel.ReadExcelData;

public class RankModelDaoRedis implements RankModelDaoInterface {

	@Override
	public List<RankModel> queryRankModelList() {
		return ReadExcelData.GetExcelData("RankModel", "RankModel");
	}
}
