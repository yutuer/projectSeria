package com.pureland.common.enums;

public enum RaceServerTypeEnum {
    None, Human, Predator, Zerg, Titan;

    public static RaceServerTypeEnum getRaceServerTypeEnumById(int index) {
        return RaceServerTypeEnum.values()[index];
    }

    public static RaceServerTypeEnum getRaceServerTypeEnumByName(String name) {
        return RaceServerTypeEnum.valueOf(name);
    }

}
