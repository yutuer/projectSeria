package com.pureland.core.init;

import com.google.common.collect.Maps;
import com.pureland.common.db.dao.daoInterface.ExploreModelDAOInterface;
import com.pureland.common.db.error.DBException;
import com.pureland.common.db.statics.ExploreModel;
import com.pureland.common.util.SpringContextUtil;

import java.util.Map;

/**
 * Created by Administrator on 2015/3/9.
 */
public class ExploreModelHelper {
    private ExploreModelDAOInterface exploreModelDAO = (ExploreModelDAOInterface) SpringContextUtil.getBean("ExploreModelDao");
    public static final Map<Integer, ExploreModel> ENTITIES = Maps.newHashMap();

    public void init() {
        try {
            for (ExploreModel exploreModel : exploreModelDAO.queryExploreModelList()) {
                ENTITIES.put(exploreModel.getSection(), exploreModel);
            }
        } catch (DBException e) {
            e.printStackTrace();
        }
    }
}
