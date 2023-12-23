package com.flow.sa46lll.fileshield;

public class BlockedExtension {

    private Long id;
    private Extension extension;
    private ExtensionType extensionType;
    private boolean isBlocked;

    public BlockedExtension(final Long id,
                            final Extension extension,
                            final ExtensionType extensionType,
                            final boolean isBlocked) {
        this.id = id;
        this.extension = extension;
        this.extensionType = extensionType;
        this.isBlocked = isBlocked;
    }

    public Long getId() {
        return id;
    }

    public Extension getExtension() {
        return extension;
    }

    public ExtensionType getExtensionType() {
        return extensionType;
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    public boolean isFixed() {
        return extensionType == ExtensionType.FIXED;
    }

    public boolean isCustom() {
        return extensionType == ExtensionType.CUSTOM;
    }
}
