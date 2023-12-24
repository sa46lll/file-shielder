package com.flow.sa46lll.fileshield.port.out;

import com.flow.sa46lll.fileshield.domain.BlockedExtension;
import java.util.List;

public interface ExtensionPersistencePort {

    List<BlockedExtension> findAll();

    Long blockCustomExtension(final BlockedExtension blockedExtension);

    void updateExtensionBlockStatus(final BlockedExtension blockedExtension);

    void deleteCustomExtensionById(final Long extensionId);

    boolean existsByExtension(String extension);
}
