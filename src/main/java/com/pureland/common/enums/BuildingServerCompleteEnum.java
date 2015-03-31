package com.pureland.common.enums;

/**
 * Created by Administrator on 2015/2/2.
 */
public enum BuildingServerCompleteEnum {
    Normal(1, "Normal"), CompleteImmediately(2, "CompleteImmediately") ;

    private int index;
    private String name;

    BuildingServerCompleteEnum(int i, String normal) {
        this.index = i;
        this.name = normal;
    }

    public int getIndex() {
        return index;
    }

    public String getName() {
        return name;
    }

    public static BuildingServerCompleteEnum getBuildingServerCompleteEnumByIndex(int i) {
        for (BuildingServerCompleteEnum e : BuildingServerCompleteEnum.values()) {
            if (e.getIndex() == i) {
                return e;
            }
        }
        return null;
    }
}
