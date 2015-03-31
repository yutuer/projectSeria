package com.pureland.common.enums;


/**
 * Created by Administrator on 2015/2/3.
 */
public enum ResearchRequestServerType {
    Research(1, "Research"), Complete(2, "Complete"), CompleteImmediately(3, "CompleteImmediately");

    private int index;
    private String enumName;

    ResearchRequestServerType(int index, String enumName) {
        this.enumName = enumName;
        this.index = index;
    }

    public String getEnumName() {
        return enumName;
    }

    public int getIndex() {
        return index;
    }

    public static ResearchRequestServerType getResearchRequestServerTypeByNumber(int index) {
        for (ResearchRequestServerType e : ResearchRequestServerType.values()) {
            if (e.index == index) {
                return e;
            }
        }
        return null;
    }
}
