package com.pureland.common.db.dao.mysql;

import java.util.List;

import com.google.common.collect.Lists;
import com.pureland.common.db.dao.daoInterface.EntityModelDAOInterface;
import com.pureland.common.util.parseExcel.ReadExcelData;

import com.pureland.common.db.error.DBException;
import com.pureland.common.db.statics.EntityModel;
import org.apache.ibatis.session.SqlSession;

/**
 * @author qinpeirong
 */
public class EntityModelDAO extends MysqlDAO implements EntityModelDAOInterface {
    @Override
    public List<EntityModel> queryEntityModelList() throws DBException {
        List<EntityModel> entities = Lists.newArrayList();
        SqlSession session = null;
        try {
            session = getSession();
            entities = session.selectList("queryEntityModelList");
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return entities;
    }

}
