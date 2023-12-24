package com.flow.sa46lll.fileshield.port.in;

public interface UpdateFixedExtensionUsecase {

    void blockFixed(final Long extensionId);

    void unblockFixed(final Long extensionId);
}
