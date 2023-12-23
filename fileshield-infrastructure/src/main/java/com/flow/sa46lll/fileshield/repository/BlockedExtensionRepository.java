package com.flow.sa46lll.fileshield.repository;

import com.flow.sa46lll.fileshield.entity.BlockedExtensionEntity;
import com.flow.sa46lll.fileshield.entity.ExtensionTypeEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlockedExtensionRepository extends JpaRepository<BlockedExtensionEntity, Long> {

    Optional<BlockedExtensionEntity> findByExtensionTypeAndId(ExtensionTypeEntity extensionType, Long id);
    void deleteByExtensionTypeAndId(ExtensionTypeEntity extensionType, Long id);
}
