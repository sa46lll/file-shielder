package com.flow.sa46lll.fileshield.adapter;

import com.flow.sa46lll.fileshield.BlockedExtension;
import com.flow.sa46lll.fileshield.application.port.out.ExtensionPersistencePort;
import com.flow.sa46lll.fileshield.entity.BlockedExtensionEntity;
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
        return BlockedExtensionMapper.toDomain(blockedExtensionRepository.findAll());
    }

    @Override
    public void blockCustomExtension(BlockedExtension blockedExtension) {
        blockedExtensionRepository.save(BlockedExtensionMapper.toEntity(blockedExtension));
    }

    @Override
    public void deleteCustomExtensionById(Long extensionId) {
        blockedExtensionRepository.deleteByIsFixedAndId(false, extensionId);
    }

    @Override
    public void blockFixedExtension(Long extensionId) {
        BlockedExtensionEntity blockedExtension = blockedExtensionRepository.findByIsFixedAndId(true, extensionId)
                .orElseThrow(() -> new IllegalArgumentException("확장자를 찾을 수 없습니다"));

        blockedExtension.block(); // 개선 필요
    }
}
