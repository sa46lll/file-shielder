package com.flow.sa46lll.fileshield.application.port.out;

import com.flow.sa46lll.fileshield.BlockedExtension;
import java.util.List;

public interface ExtensionPersistencePort {

    List<BlockedExtension> findAll();

    Long blockCustomExtension(BlockedExtension blockedExtension);

    void deleteCustomExtensionById(Long extensionId);

    void blockFixedExtension(Long extensionId);

    void unblockFixedExtension(Long extensionId);
}
