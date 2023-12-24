package com.flow.sa46lll.fileshield.adapter;

import com.flow.sa46lll.fileshield.domain.BlockedExtension;
import com.flow.sa46lll.fileshield.mapper.BlockedExtensionMapper;
import com.flow.sa46lll.fileshield.port.out.ReadExtensionPersistencePort;
import com.flow.sa46lll.fileshield.repository.BlockedExtensionRepository;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class ReadExtensionPersistenceAdapter implements ReadExtensionPersistencePort {

    private final BlockedExtensionRepository blockedExtensionRepository;

    public ReadExtensionPersistenceAdapter(BlockedExtensionRepository blockedExtensionRepository) {
        this.blockedExtensionRepository = blockedExtensionRepository;
    }

    @Override
    public List<BlockedExtension> findAll() {
        return blockedExtensionRepository.findAll().stream()
                .map(BlockedExtensionMapper::toDomain)
                .toList());
    }
}
