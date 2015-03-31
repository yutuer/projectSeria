package com.pureland.core.service.clan;

import java.util.Map;

/**
 * Created by Administrator on 2015/3/12.
 */
public class ClanMap {
    private Map<String, ClanLogicService> clanMap;

    public Map<String, ClanLogicService> getClanMap() {
        return clanMap;
    }

    public void setClanMap(Map<String, ClanLogicService> clanMap) {
        this.clanMap = clanMap;
    }
}
