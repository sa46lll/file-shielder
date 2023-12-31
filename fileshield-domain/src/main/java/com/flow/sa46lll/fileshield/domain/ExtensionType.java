package com.flow.sa46lll.fileshield.domain;

public enum ExtensionType {
    CUSTOM,
    FIXED;

    public static ExtensionType from(String name) {
        return ExtensionType.valueOf(name);
    }
}
