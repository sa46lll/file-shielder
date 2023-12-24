package com.flow.sa46lll.fileshield.mapper;

import com.flow.sa46lll.fileshield.dto.BlockCustomExtensionResponse;

public class BlockCustomExtensionResponseMapper {

    private BlockCustomExtensionResponseMapper() {
    }

    public static BlockCustomExtensionResponse toResponse(final Long extensionId) {
        return new BlockCustomExtensionResponse(extensionId);
    }
}
