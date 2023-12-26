package com.flow.sa46lll.fileshield.adapter;

import com.flow.sa46lll.fileshield.domain.BlockedExtension;
import com.flow.sa46lll.fileshield.entity.NotFoundException;
import com.flow.sa46lll.fileshield.exception.InvalidInputException;
import com.flow.sa46lll.fileshield.mapper.BlockedExtensionMapper;
import com.flow.sa46lll.fileshield.port.out.WriteExtensionPersistencePort;
import com.flow.sa46lll.fileshield.entity.BlockedExtensionEntity;
import com.flow.sa46lll.fileshield.entity.ExtensionTypeEntity;
import com.flow.sa46lll.fileshield.repository.BlockedExtensionRepository;
import org.springframework.stereotype.Component;

@Component
public class WriteExtensionPersistenceAdapter implements WriteExtensionPersistencePort {

    private final BlockedExtensionRepository blockedExtensionRepository;

    public WriteExtensionPersistenceAdapter(final BlockedExtensionRepository blockedExtensionRepository) {
        this.blockedExtensionRepository = blockedExtensionRepository;
    }

    @Override
    public Long blockCustomExtension(BlockedExtension blockedExtension) {
        return blockedExtensionRepository
                .save(BlockedExtensionMapper.toEntity(blockedExtension))
                .getId();
    }

    @Override
    public void updateExtensionBlockStatus(BlockedExtension blockedExtension) {
        BlockedExtensionEntity persistedEntity =
                blockedExtensionRepository.findByExtensionTypeAndId(ExtensionTypeEntity.FIXED, blockedExtension.getId())
                        .orElseThrow(() -> new NotFoundException("확장자를 찾을 수 없습니다."));

        blockedExtensionRepository.save(BlockedExtensionMapper.toUpdateEntity(blockedExtension, persistedEntity));
    }

    @Override
    public void deleteCustomExtensionById(Long extensionId) {
        blockedExtensionRepository.deleteByExtensionTypeAndId(ExtensionTypeEntity.CUSTOM, extensionId);
    }

    @Override
    public boolean existsByExtension(String extension) {
        return blockedExtensionRepository.existsByExtension(extension);
    }

    @Override
    public void save(BlockedExtension blockedExtension) {
        blockedExtensionRepository.save(BlockedExtensionMapper.toEntity(blockedExtension));
    }
}
