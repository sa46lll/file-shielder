package com.flow.sa46lll.fileshield.application.dto;

import com.flow.sa46lll.fileshield.BlockedExtension;

public record GetExtensionResponse(
        Long extensionId,
        String extension,
        boolean isBlocked
) {

    public static GetExtensionResponse of(final BlockedExtension extension) {
        return new GetExtensionResponse(
                extension.getId(),
                extension.getExtension().getExtension(),
                extension.isBlocked()
        );
    }
}
