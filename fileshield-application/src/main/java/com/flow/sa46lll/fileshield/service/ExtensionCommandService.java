package com.flow.sa46lll.fileshield.service;

import com.flow.sa46lll.fileshield.BlockedExtension;
import com.flow.sa46lll.fileshield.dto.BlockCustomExtensionCommand;
import com.flow.sa46lll.fileshield.dto.BlockCustomExtensionResponse;
import com.flow.sa46lll.fileshield.exception.ExtensionDuplicationException;
import com.flow.sa46lll.fileshield.mapper.BlockedExtensionDtoMapper;
import com.flow.sa46lll.fileshield.mapper.BlockCustomExtensionResponseMapper;
import com.flow.sa46lll.fileshield.port.in.BlockCustomExtensionUseCase;
import com.flow.sa46lll.fileshield.port.in.UpdateFixedExtensionUsecase;
import com.flow.sa46lll.fileshield.port.in.UnblockCustomExtensionUseCase;
import com.flow.sa46lll.fileshield.port.out.ExtensionPersistencePort;
import org.springframework.stereotype.Service;

@Service
public class ExtensionCommandService implements BlockCustomExtensionUseCase,
        UnblockCustomExtensionUseCase, UpdateFixedExtensionUsecase {

    private final ExtensionPersistencePort extensionPersistencePort;

    public ExtensionCommandService(final ExtensionPersistencePort extensionPersistencePort) {
        this.extensionPersistencePort = extensionPersistencePort;
    }

    @Override
    public BlockCustomExtensionResponse blockCustom(final BlockCustomExtensionCommand blockCustomExtensionCommand) {
        validateExtensionDuplication(blockCustomExtensionCommand.extension());

        BlockedExtension blockedExtension = blockCustomExtensionCommand.toDomain();
        Long savedId = extensionPersistencePort.blockCustomExtension(blockedExtension);
        return BlockCustomExtensionResponseMapper.toResponse(savedId);
    }

    private void validateExtensionDuplication(String extension) {
        if (extensionPersistencePort.existsByExtension(extension)) {
            throw new ExtensionDuplicationException("이미 존재하는 확장자입니다.");
        }
    }

    @Override
    public void unblockCustom(final Long extensionId) {
        extensionPersistencePort.deleteCustomExtensionById(extensionId);
    }

    @Override
    public void blockFixed(final Long extensionId) {
        BlockedExtension blockedExtension = BlockedExtensionDtoMapper.toDomainFromUpdateDto(extensionId);
        blockedExtension.block();
        updateExtensionBlockStatus(blockedExtension);
    }

    @Override
    public void unblockFixed(final Long extensionId) {
        BlockedExtension blockedExtension = BlockedExtensionDtoMapper.toDomainFromUpdateDto(extensionId);
        blockedExtension.unblock();
        updateExtensionBlockStatus(blockedExtension);
    }

    private void updateExtensionBlockStatus(final BlockedExtension blockedExtension) {
        extensionPersistencePort.updateExtensionBlockStatus(blockedExtension);
    }
}
