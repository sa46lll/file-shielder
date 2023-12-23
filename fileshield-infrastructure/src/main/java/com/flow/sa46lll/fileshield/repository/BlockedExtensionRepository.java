package com.flow.sa46lll.fileshield.repository;

import com.flow.sa46lll.fileshield.entity.BlockedExtensionEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlockedExtensionRepository extends JpaRepository<BlockedExtensionEntity, Long> {

    Optional<BlockedExtensionEntity> findByIsFixedAndId(boolean isFixed, Long id);
    void deleteByIsFixedAndId(boolean isFixed, Long id);
}
