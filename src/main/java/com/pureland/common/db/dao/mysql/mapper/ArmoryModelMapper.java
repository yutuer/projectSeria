package com.pureland.common.db.dao.mysql.mapper;

import java.util.List;

import com.pureland.common.db.statics.ArmoryModel;


public interface ArmoryModelMapper extends SqlMapper {

	public List<ArmoryModel> queryArmoryModelList();
}
