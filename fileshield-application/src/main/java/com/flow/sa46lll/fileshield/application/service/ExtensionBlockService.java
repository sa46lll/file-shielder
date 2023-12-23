package com.flow.sa46lll.fileshield.application.service;

import com.flow.sa46lll.fileshield.BlockedExtension;
import com.flow.sa46lll.fileshield.application.dto.BlockCustomExtensionCommand;
import com.flow.sa46lll.fileshield.application.port.in.BlockCustomExtensionUseCase;
import com.flow.sa46lll.fileshield.application.port.in.BlockFixedExtensionUseCase;
import com.flow.sa46lll.fileshield.application.port.in.UnBlockCustomExtensionUseCase;
import com.flow.sa46lll.fileshield.application.port.out.ExtensionPersistencePort;
import org.springframework.stereotype.Service;

@Service
public class ExtensionBlockService implements BlockCustomExtensionUseCase,
        UnBlockCustomExtensionUseCase, BlockFixedExtensionUseCase {

    private final ExtensionPersistencePort extensionPersistencePort;

    public ExtensionBlockService(final ExtensionPersistencePort extensionPersistencePort) {
        this.extensionPersistencePort = extensionPersistencePort;
    }

    @Override
    public void blockCustomExtension(final BlockCustomExtensionCommand blockCustomExtensionCommand) {
        BlockedExtension blockedExtension = blockCustomExtensionCommand.toDomain();
        extensionPersistencePort.blockCustomExtension(blockedExtension);
    }

    @Override
    public void unblockCustomExtension(Long extensionId) {
        extensionPersistencePort.deleteCustomExtensionById(extensionId);
    }

    @Override
    public void blockFixedExtension(Long extensionId) {
        extensionPersistencePort.blockFixedExtension(extensionId);
    }
}
