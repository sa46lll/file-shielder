package com.flow.sa46lll.fileshield.application.dto;

import com.flow.sa46lll.fileshield.BlockedExtension;

public class BlockedExtensionMapper {

        private BlockedExtensionMapper() {
        }

        public static BlockedExtension toDomainFromUpdateDto(final Long extensionId) {
            return new BlockedExtension(extensionId);
        }
}
