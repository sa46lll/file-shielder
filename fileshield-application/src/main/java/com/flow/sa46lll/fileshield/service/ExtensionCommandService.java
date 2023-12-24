package com.flow.sa46lll.fileshield.service;

import com.flow.sa46lll.fileshield.domain.BlockedExtension;
import com.flow.sa46lll.fileshield.dto.BlockCustomExtensionCommand;
import com.flow.sa46lll.fileshield.dto.BlockCustomExtensionResponse;
import com.flow.sa46lll.fileshield.exception.ExtensionDuplicationException;
import com.flow.sa46lll.fileshield.mapper.BlockedExtensionDtoMapper;
import com.flow.sa46lll.fileshield.mapper.BlockCustomExtensionResponseMapper;
import com.flow.sa46lll.fileshield.port.in.BlockCustomExtensionUseCase;
import com.flow.sa46lll.fileshield.port.in.UpdateFixedExtensionUsecase;
import com.flow.sa46lll.fileshield.port.in.UnblockCustomExtensionUseCase;
import com.flow.sa46lll.fileshield.port.out.WriteExtensionPersistencePort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ExtensionCommandService implements BlockCustomExtensionUseCase,
        UnblockCustomExtensionUseCase, UpdateFixedExtensionUsecase {

    private final WriteExtensionPersistencePort writeExtensionPersistencePort;

    public ExtensionCommandService(final WriteExtensionPersistencePort writeExtensionPersistencePort) {
        this.writeExtensionPersistencePort = writeExtensionPersistencePort;
    }

    @Override
    public BlockCustomExtensionResponse blockCustom(final BlockCustomExtensionCommand blockCustomExtensionCommand) {
        validateExtensionDuplication(blockCustomExtensionCommand.extension());

        BlockedExtension blockedExtension = blockCustomExtensionCommand.toDomain();
        Long savedId = writeExtensionPersistencePort.blockCustomExtension(blockedExtension);
        return BlockCustomExtensionResponseMapper.toResponse(savedId);
    }

    private void validateExtensionDuplication(String extension) {
        if (writeExtensionPersistencePort.existsByExtension(extension)) {
            throw new ExtensionDuplicationException("이미 존재하는 확장자입니다.");
        }
    }

    @Override
    public void unblockCustom(final Long extensionId) {
        writeExtensionPersistencePort.deleteCustomExtensionById(extensionId);
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
        writeExtensionPersistencePort.updateExtensionBlockStatus(blockedExtension);
    }
}
