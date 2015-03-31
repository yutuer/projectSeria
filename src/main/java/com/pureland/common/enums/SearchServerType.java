package com.pureland.common.enums;

import com.pureland.common.protocal.FightSearchReqProtocal.FightSearchReq.SearchType;

/**
 * Created by qinpeirong on 15-1-16.
 */
public enum SearchServerType implements BaseEnum {
    PVE(new Long(1), "PVE"),
    PVP_NORMAL(new Long(2), "PVP_NORMAL"),
    PVP_HIGH(new Long(3), "PVP_HIGH");

    private Long id;
    private String code;
    private String name;
    private String label;

    private SearchServerType(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public Long getId() {
        return this.id;
    }

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getLabel() {
        return this.label;
    }

    public static SearchServerType getSearchServerType(SearchType searchType) {
        for (SearchServerType searchServerType : SearchServerType.values()) {
            if (searchServerType.getName().equals(searchType.toString())) {
                return searchServerType;
            }
        }
        return null;
    }
}
