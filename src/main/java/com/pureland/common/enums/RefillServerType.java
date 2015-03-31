package com.pureland.common.enums;

/**
 * Created by Administrator on 2015/2/11.
 */
public enum RefillServerType {
    Single(1, "Single"), All(2, "All");

    private int id;
    private String name;

    private RefillServerType(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static RefillServerType getRefillServerTypeById(int id) {
        for (RefillServerType refillServerType : RefillServerType.values()) {
            if (refillServerType.getId() == id) {
                return refillServerType;
            }
        }
        throw new RuntimeException(String.format("error no find %:d\n", id));
    }

}
