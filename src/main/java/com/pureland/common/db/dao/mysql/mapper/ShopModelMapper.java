package com.pureland.common.db.dao.mysql.mapper;

import com.pureland.common.db.statics.ShopModel;

import java.util.List;

/**
 * Created by Administrator on 2015/1/27.
 */
public interface ShopModelMapper extends SqlMapper{
    public List<ShopModel> queryShopModelList();

}
