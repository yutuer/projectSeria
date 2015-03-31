package com.pureland.core.init;

import com.google.common.collect.*;
import com.pureland.common.db.dao.daoInterface.RankModelDaoInterface;
import com.pureland.common.db.dao.mysql.RankModelDao;
import com.pureland.common.db.dao.mysql.ShopModelDao;
import com.pureland.common.db.error.DBException;
import com.pureland.common.db.statics.RankModel;
import com.pureland.common.db.statics.ShopModel;
import com.pureland.common.enums.RankModelType;
import com.pureland.common.util.SpringContextUtil;
import org.apache.commons.collections.comparators.ComparableComparator;

import java.util.*;

/**
 * Created by Administrator on 2015/1/27.
 */
public class RankModelHelper {
    private RankModelDaoInterface rankModelDao = (RankModelDaoInterface) SpringContextUtil.getBean(RankModelDao.class.getSimpleName());

    public static final Multimap<RankModelType, RankModel> ENTITIES = ArrayListMultimap.create();

    private static final Map<RankModelType, List<RankModel>> SortedRankModelList = Maps.newHashMap();

    public void init() {
        try {
            List<RankModel> queryRankModelList = rankModelDao.queryRankModelList();
            for (RankModel rankModel : queryRankModelList) {
                ENTITIES.put(getRankModelTypeByName(rankModel.getType()), rankModel);
            }

            for (RankModelType rmt : RankModelType.values()) {
                Collection<RankModel> c = ENTITIES.get(rmt);
                List<RankModel> list = Lists.newArrayList();
                list.addAll(c);
                Collections.sort(list, new Comparator<RankModel>() {
                    @Override
                    public int compare(RankModel o1, RankModel o2) {
                        return o2.getExp() - o1.getExp();
                    }
                });
                SortedRankModelList.put(rmt, list);
            }
        } catch (DBException e) {
            e.printStackTrace();
        }
    }


    private static RankModelType getRankModelTypeByName(String type) {
        for (RankModelType rmt : RankModelType.values()) {
            if (rmt.name().equals(type)) {
                return rmt;
            }
        }
        return null;
    }

    public static int getLevelByExp(int exp) {
        List<RankModel> list = SortedRankModelList.get(RankModelType.Level);
        for (RankModel rmm : list) {
            if (exp >= rmm.getExp()) {
                return Integer.parseInt(rmm.getName());
            }
        }
        return 0;
    }

}
