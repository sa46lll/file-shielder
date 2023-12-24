package com.flow.sa46lll.fileshield.mapper;

import com.flow.sa46lll.fileshield.BlockedExtension;

public class BlockedExtensionDtoMapper {

        private BlockedExtensionDtoMapper() {
        }

        public static BlockedExtension toDomainFromUpdateDto(final Long extensionId) {
            return new BlockedExtension(extensionId);
        }
}
