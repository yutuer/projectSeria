package com.pureland.core.service.explore;

import com.pureland.common.db.data.Ship;
import com.pureland.common.error.CoreException;
import com.pureland.common.service.bean.ExploreBean;

import java.util.List;

/**
 * Created by Administrator on 2015/3/6.
 */
public interface ExploreLogicService {

    List<Ship> exploreLogic(ExploreBean exploreBean) throws CoreException;
}
