package com.flow.sa46lll.fileshield.mapper;

import com.flow.sa46lll.fileshield.BlockedExtension;
import com.flow.sa46lll.fileshield.Extension;
import com.flow.sa46lll.fileshield.ExtensionType;
import com.flow.sa46lll.fileshield.entity.BlockedExtensionEntity;
import com.flow.sa46lll.fileshield.entity.ExtensionTypeEntity;
import java.util.List;

public class BlockedExtensionMapper {

    private BlockedExtensionMapper() {
    }

    public static List<BlockedExtension> toDomain(List<BlockedExtensionEntity> entities) {
        return entities.stream()
                .map(BlockedExtensionMapper::toDomain)
                .toList();
    }

    public static BlockedExtension toDomain(BlockedExtensionEntity entity) {
        return new BlockedExtension(
                entity.getId(),
                Extension.of(entity.getExtension()),
                ExtensionType.from(entity.getExtensionType().name()),
                entity.isBlocked());
    }

    public static BlockedExtensionEntity toEntity(BlockedExtension blockedExtension) {
        return new BlockedExtensionEntity(
                blockedExtension.getExtension().getExtension(),
                ExtensionTypeEntity.from(blockedExtension.getExtensionType().name()),
                blockedExtension.isBlocked());
    }
}
