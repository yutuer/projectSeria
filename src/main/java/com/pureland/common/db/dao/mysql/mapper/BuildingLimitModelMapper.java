package com.pureland.common.db.dao.mysql.mapper;

import java.util.List;

import com.pureland.common.db.statics.BuildingLimitModel;

/**
 * 
 * @author qinpeirong
 *
 */
public interface BuildingLimitModelMapper extends SqlMapper {
	public List<BuildingLimitModel> queryBuildingLimitModelList();
}
