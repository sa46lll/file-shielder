package com.flow.sa46lll.fileshield.mapper;

import com.flow.sa46lll.fileshield.domain.BlockedExtension;
import com.flow.sa46lll.fileshield.domain.Extension;
import com.flow.sa46lll.fileshield.dto.SaveExtensionDto;

public class BlockedExtensionDtoMapper {

    private BlockedExtensionDtoMapper() {
    }

    public static BlockedExtension toDomainFromUpdateDto(final Long extensionId) {
        return new BlockedExtension(extensionId);
    }

    public static BlockedExtension toDomainFromSaveDto(final SaveExtensionDto saveExtensionDto) {
        return new BlockedExtension(
                null,
                Extension.of(saveExtensionDto.extension()),
                saveExtensionDto.extensionType(),
                saveExtensionDto.isBlocked()
        );
    }
}
