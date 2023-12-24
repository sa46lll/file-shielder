package com.flow.sa46lll.fileshield.adapter;

import com.flow.sa46lll.fileshield.BlockedExtension;
import com.flow.sa46lll.fileshield.port.out.ExtensionPersistencePort;
import com.flow.sa46lll.fileshield.entity.BlockedExtensionEntity;
import com.flow.sa46lll.fileshield.entity.ExtensionTypeEntity;
import com.flow.sa46lll.fileshield.mapper.BlockedExtensionMapper;
import com.flow.sa46lll.fileshield.repository.BlockedExtensionRepository;
import java.util.List;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class ExtensionPersistenceAdapter implements ExtensionPersistencePort {

    private final BlockedExtensionRepository blockedExtensionRepository;

    public ExtensionPersistenceAdapter(final BlockedExtensionRepository blockedExtensionRepository) {
        this.blockedExtensionRepository = blockedExtensionRepository;
    }

    @Override
    public List<BlockedExtension> findAll() {
        return blockedExtensionRepository.findAll().stream()
                .map(BlockedExtensionMapper::toDomain)
                .toList();
    }

    @Override
    public Long blockCustomExtension(BlockedExtension blockedExtension) {
        return blockedExtensionRepository
                .save(BlockedExtensionMapper.toEntity(blockedExtension))
                .getId();
    }

    @Override
    public void updateExtensionBlockStatus(BlockedExtension blockedExtension) {
        blockedExtensionRepository.findByExtensionTypeAndId(ExtensionTypeEntity.FIXED, blockedExtension.getId())
                .ifPresent(persistedEntity -> {
                    BlockedExtensionEntity updated = BlockedExtensionMapper.toUpdateEntity(blockedExtension, persistedEntity);
                    blockedExtensionRepository.save(updated);
                });
    }

    @Override
    public void deleteCustomExtensionById(Long extensionId) {
        blockedExtensionRepository.deleteByExtensionTypeAndId(ExtensionTypeEntity.CUSTOM, extensionId);
    }
}
