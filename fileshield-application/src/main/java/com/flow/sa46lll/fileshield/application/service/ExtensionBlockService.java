package com.flow.sa46lll.fileshield.application.service;

import com.flow.sa46lll.fileshield.BlockedExtension;
import com.flow.sa46lll.fileshield.application.dto.BlockCustomExtensionCommand;
import com.flow.sa46lll.fileshield.application.port.in.BlockCustomExtensionUseCase;
import com.flow.sa46lll.fileshield.application.port.in.BlockFixedExtensionUseCase;
import com.flow.sa46lll.fileshield.application.port.in.UnblockCustomExtensionUseCase;
import com.flow.sa46lll.fileshield.application.port.in.UnblockFixedExtensionUseCase;
import com.flow.sa46lll.fileshield.application.port.out.ExtensionPersistencePort;
import org.springframework.stereotype.Service;

@Service
public class ExtensionBlockService implements BlockCustomExtensionUseCase,
        UnblockCustomExtensionUseCase, BlockFixedExtensionUseCase, UnblockFixedExtensionUseCase {

    private final ExtensionPersistencePort extensionPersistencePort;

    public ExtensionBlockService(final ExtensionPersistencePort extensionPersistencePort) {
        this.extensionPersistencePort = extensionPersistencePort;
    }

    @Override
    public void blockCustom(final BlockCustomExtensionCommand blockCustomExtensionCommand) {
        BlockedExtension blockedExtension = blockCustomExtensionCommand.toDomain();
        extensionPersistencePort.blockCustomExtension(blockedExtension);
    }

    @Override
    public void unblockCustom(Long extensionId) {
        extensionPersistencePort.deleteCustomExtensionById(extensionId);
    }

    @Override
    public void blockFixed(Long extensionId) {
        extensionPersistencePort.blockFixedExtension(extensionId);
    }

    @Override
    public void unblockFixed(Long extensionId) {
        extensionPersistencePort.unblockFixedExtension(extensionId);
    }
}
