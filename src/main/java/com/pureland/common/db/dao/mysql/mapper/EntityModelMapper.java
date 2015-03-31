package com.pureland.common.db.dao.mysql.mapper;

import java.util.List;

import com.pureland.common.db.statics.EntityModel;

public interface EntityModelMapper extends SqlMapper {
	public List<EntityModel> queryEntityModelList();
}
