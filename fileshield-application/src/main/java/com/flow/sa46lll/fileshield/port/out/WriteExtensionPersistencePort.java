package com.flow.sa46lll.fileshield.port.out;

import com.flow.sa46lll.fileshield.domain.BlockedExtension;

public interface WriteExtensionPersistencePort {

    Long blockCustomExtension(final BlockedExtension blockedExtension);

    void updateExtensionBlockStatus(final BlockedExtension blockedExtension);

    void deleteCustomExtensionById(final Long extensionId);

    boolean existsByExtension(final String extension);
}
