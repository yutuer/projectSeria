package com.pureland.core.init;

import com.google.common.collect.Maps;
import com.pureland.common.db.dao.daoInterface.ShopModelDaoInterface;
import com.pureland.common.db.dao.mysql.ShopModelDao;
import com.pureland.common.db.error.DBException;
import com.pureland.common.db.statics.ShopModel;
import com.pureland.common.util.SpringContextUtil;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015/1/27.
 */
public class ShopModelHelper {
    private ShopModelDaoInterface shopModelDao = (ShopModelDaoInterface) SpringContextUtil.getBean(ShopModelDao.class.getSimpleName());

    public static final Map<Integer, ShopModel> ENTITIES = Maps.newHashMap();

    public void init() {
        try {
            List<ShopModel> queryShopModelList = shopModelDao.queryShopModelList();
            for (ShopModel shopModel : queryShopModelList) {
                ENTITIES.put(shopModel.getBaseId(), shopModel);
            }
        } catch (DBException e) {
            e.printStackTrace();
        }

    }


}
