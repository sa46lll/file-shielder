package com.flow.sa46lll.fileshield.adapter;

import com.flow.sa46lll.fileshield.BlockedExtension;
import com.flow.sa46lll.fileshield.application.port.out.ExtensionPersistencePort;
import com.flow.sa46lll.fileshield.mapper.BlockedExtensionDomainMapper;
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
        return BlockedExtensionDomainMapper.toDomain(blockedExtensionRepository.findAll());
    }
}
