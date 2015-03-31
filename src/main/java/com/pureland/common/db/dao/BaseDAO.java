package com.pureland.common.db.dao;

import java.util.List;

import com.pureland.common.db.error.DBException;
import com.pureland.common.util.DataObject;

public interface BaseDAO {

	public Long add(DataObject dObj) throws DBException;

	public Long update(DataObject dObj) throws DBException;

	public DataObject findOne(DataObject dObj) throws DBException;

	public List<DataObject> query(DataObject query) throws DBException;
}
