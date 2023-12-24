package com.flow.sa46lll.fileshield.application.port.in;

public interface UpdateFixedExtensionUsecase {

    void blockFixed(final Long extensionId);

    void unblockFixed(final Long extensionId);
}
