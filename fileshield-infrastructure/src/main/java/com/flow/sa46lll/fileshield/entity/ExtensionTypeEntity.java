package com.flow.sa46lll.fileshield.entity;

public enum ExtensionTypeEntity {

    CUSTOM,
    FIXED;

    public static ExtensionTypeEntity from(String name) {
        return ExtensionTypeEntity.valueOf(name);
    }
}
