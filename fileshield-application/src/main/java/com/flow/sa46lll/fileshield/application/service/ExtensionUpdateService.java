package com.flow.sa46lll.fileshield.application.service;

import com.flow.sa46lll.fileshield.BlockedExtension;
import com.flow.sa46lll.fileshield.application.dto.BlockCustomExtensionCommand;
import com.flow.sa46lll.fileshield.application.dto.BlockCustomExtensionResponse;
import com.flow.sa46lll.fileshield.application.dto.BlockedExtensionMapper;
import com.flow.sa46lll.fileshield.application.mapper.BlockCustomExtensionResponseMapper;
import com.flow.sa46lll.fileshield.application.port.in.BlockCustomExtensionUseCase;
import com.flow.sa46lll.fileshield.application.port.in.UpdateFixedExtensionUsecase;
import com.flow.sa46lll.fileshield.application.port.in.UnblockCustomExtensionUseCase;
import com.flow.sa46lll.fileshield.application.port.out.ExtensionPersistencePort;
import org.springframework.stereotype.Service;

@Service
public class ExtensionUpdateService implements BlockCustomExtensionUseCase,
        UnblockCustomExtensionUseCase, UpdateFixedExtensionUsecase {

    private final ExtensionPersistencePort extensionPersistencePort;

    public ExtensionUpdateService(final ExtensionPersistencePort extensionPersistencePort) {
        this.extensionPersistencePort = extensionPersistencePort;
    }

    @Override
    public BlockCustomExtensionResponse blockCustom(final BlockCustomExtensionCommand blockCustomExtensionCommand) {
        BlockedExtension blockedExtension = blockCustomExtensionCommand.toDomain();
        Long savedId = extensionPersistencePort.blockCustomExtension(blockedExtension);
        return BlockCustomExtensionResponseMapper.toResponse(savedId);
    }

    @Override
    public void unblockCustom(final Long extensionId) {
        extensionPersistencePort.deleteCustomExtensionById(extensionId);
    }

    @Override
    public void blockFixed(final Long extensionId) {
        BlockedExtension blockedExtension = BlockedExtensionMapper.toDomainFromUpdateDto(extensionId);
        blockedExtension.block();
        updateExtensionBlockStatus(blockedExtension);
    }

    @Override
    public void unblockFixed(final Long extensionId) {
        BlockedExtension blockedExtension = BlockedExtensionMapper.toDomainFromUpdateDto(extensionId);
        blockedExtension.unblock();
        updateExtensionBlockStatus(blockedExtension);
    }

    private void updateExtensionBlockStatus(final BlockedExtension blockedExtension) {
        extensionPersistencePort.updateExtensionBlockStatus(blockedExtension);
    }
}
