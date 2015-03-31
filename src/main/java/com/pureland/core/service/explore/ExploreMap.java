package com.pureland.core.service.explore;

import java.util.Map;

/**
 * Created by Administrator on 2015/3/6.
 */
public class ExploreMap {
    private Map<String, ExploreLogicService> exploreMap;

    public Map<String, ExploreLogicService> getExploreMaps() {
        return exploreMap;
    }

    public void setExploreMaps(Map<String, ExploreLogicService> exploreMap) {
        this.exploreMap = exploreMap;
    }
}
