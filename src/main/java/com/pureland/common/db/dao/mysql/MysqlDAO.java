package com.pureland.common.db.dao.mysql;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.pureland.common.db.dao.BaseDAO;
import com.pureland.common.db.error.DBException;
import com.pureland.common.util.DataObject;
import com.pureland.common.util.SpringContextUtil;

public abstract class MysqlDAO implements BaseDAO {

	protected SqlSession getSession() {
		SqlSessionFactory sqlSessionFactory = (SqlSessionFactory) SpringContextUtil.getBean("sqlSessionFactory");
		return sqlSessionFactory.openSession();
	}

	@Override
	public Long add(DataObject dObj) throws DBException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long update(DataObject dObj) throws DBException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DataObject findOne(DataObject dObj) throws DBException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DataObject> query(DataObject query) throws DBException {
		// TODO Auto-generated method stub
		return null;
	}

}
