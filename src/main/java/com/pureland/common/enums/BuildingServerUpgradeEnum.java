package com.pureland.common.enums;

/**
 * Created by Administrator on 2015/2/2.
 */
public enum BuildingServerUpgradeEnum {
    NotCancel(1, "NotCancel"), Cancel(2, "Cancel");

    private int index;
    private String name;

    BuildingServerUpgradeEnum(int i, String normal) {
        this.index = i;
        this.name = normal;
    }

    public int getIndex() {
        return index;
    }

    public String getName() {
        return name;
    }

}
