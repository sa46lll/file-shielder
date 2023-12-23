package com.flow.sa46lll.fileshield;

public class BlockedExtension {

    private Long id;
    private Extension extension;
    private boolean isFixed;
    private boolean isBlocked;

    public BlockedExtension(final Long id,
                            final Extension extension,
                            final boolean isFixed,
                            final boolean isBlocked) {
        this.id = id;
        this.extension = extension;
        this.isFixed = isFixed;
        this.isBlocked = isBlocked;
    }

    public Long getId() {
        return id;
    }

    public Extension getExtension() {
        return extension;
    }

    public boolean isFixed() {
        return isFixed;
    }

    public boolean isBlocked() {
        return isBlocked;
    }
}
