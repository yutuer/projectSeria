package com.pureland.common.enums;

/**
 * Created by Administrator on 2015/1/22.
 */
public enum ArmoryBuildTypeEnum implements BaseEnum{
    ARMYTYPE(new Long(1),"ARMYTYPE"),
    SKILLTYPE(new Long(2), "SKILLTYPE");

    private Long id;
    private String code;
    private String name;
    private String label;

    private ArmoryBuildTypeEnum(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getLabel() {
        return label;
    }
}
