package com.pureland.common.enums;

public enum ResourceServerTypeEnum {
    None, Oil, Number, NewOil, RMB, Gold, Diamond, GoldOrOil;

    public static ResourceServerTypeEnum getResourceServerTypeEnumById(int index) {
        return ResourceServerTypeEnum.values()[index];
    }

    public static ResourceServerTypeEnum getResourceServerTypeEnumByName(String name) {
        return ResourceServerTypeEnum.valueOf(name);
    }

}
