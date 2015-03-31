package com.pureland.common.db.dao.mysql;

import com.google.common.collect.Lists;
import com.pureland.common.db.dao.daoInterface.ShopModelDaoInterface;
import com.pureland.common.db.error.DBException;
import com.pureland.common.db.statics.ShopModel;
import com.pureland.common.util.parseExcel.ReadExcelData;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

/**
 * Created by Administrator on 2015/1/27.
 */
public class ShopModelDao extends MysqlDAO implements ShopModelDaoInterface {

    @Override
    public List<ShopModel> queryShopModelList() throws DBException {
        List<ShopModel> shopModels = Lists.newArrayList();
        SqlSession session = null;
        try {
            session = getSession();
            shopModels = session.selectList("queryShopModelList");
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return shopModels;
    }

}
