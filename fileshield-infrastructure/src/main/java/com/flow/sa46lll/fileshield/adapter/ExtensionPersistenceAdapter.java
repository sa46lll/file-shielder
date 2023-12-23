package com.flow.sa46lll.fileshield.adapter;

import com.flow.sa46lll.fileshield.BlockedExtension;
import com.flow.sa46lll.fileshield.application.port.out.ExtensionPersistencePort;
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
    public void blockCustomExtension(BlockedExtension blockedExtension) {
        blockedExtensionRepository.save(BlockedExtensionMapper.toEntity(blockedExtension));
    }

    @Override
    public void deleteCustomExtensionById(Long extensionId) {
        blockedExtensionRepository.deleteByExtensionTypeAndId(ExtensionTypeEntity.CUSTOM, extensionId);
    }

    @Override
    public void blockFixedExtension(Long extensionId) {
        BlockedExtensionEntity blockedExtension =
                blockedExtensionRepository.findByExtensionTypeAndId(ExtensionTypeEntity.FIXED, extensionId)
                        .orElseThrow(() -> new IllegalArgumentException("확장자를 찾을 수 없습니다"));

        blockedExtension.block(); // 개선 필요
    }

    @Override
    public void unblockFixedExtension(Long extensionId) {
        BlockedExtensionEntity blockedExtension =
                blockedExtensionRepository.findByExtensionTypeAndId(ExtensionTypeEntity.FIXED, extensionId)
                        .orElseThrow(() -> new IllegalArgumentException("확장자를 찾을 수 없습니다"));

        blockedExtension.unblock();
    }
}
