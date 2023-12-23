package com.flow.sa46lll.fileshield.mapper;

import com.flow.sa46lll.fileshield.BlockedExtension;
import com.flow.sa46lll.fileshield.Extension;
import com.flow.sa46lll.fileshield.entity.BlockedExtensionEntity;
import java.util.List;

public class BlockedExtensionDomainMapper {

    private BlockedExtensionDomainMapper() {
    }

    public static List<BlockedExtension> toDomain(List<BlockedExtensionEntity> entities) {
        return entities.stream()
                .map(BlockedExtensionDomainMapper::toDomain)
                .toList();
    }

    public static BlockedExtension toDomain(BlockedExtensionEntity entity) {
        return new BlockedExtension(
                entity.getId(),
                Extension.of(entity.getExtension()),
                entity.isFixed(),
                entity.isBlocked());
    }
}
