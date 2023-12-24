package com.flow.sa46lll.fileshield.application.port.out;

import com.flow.sa46lll.fileshield.BlockedExtension;
import java.util.List;

public interface ExtensionPersistencePort {

    List<BlockedExtension> findAll();

    Long blockCustomExtension(final BlockedExtension blockedExtension);

    void updateExtensionBlockStatus(final BlockedExtension blockedExtension);

    void deleteCustomExtensionById(final Long extensionId);
}
