package com.pureland.core.service.search;

import java.util.Map;

/**
 * Created by qinpeirong on 15-1-16.
 */
public class FightSearchMap {
    private Map<String, FightSearchService> fightSearchMap;

    public Map<String, FightSearchService> getFightSearchMap() {
        return fightSearchMap;
    }

    public void setFightSearchMap(Map<String, FightSearchService> fightSearchMap) {
        this.fightSearchMap = fightSearchMap;
    }
}
