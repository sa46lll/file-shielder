package com.flow.sa46lll.fileshield.application.dto;

import com.flow.sa46lll.fileshield.BlockedExtension;
import com.flow.sa46lll.fileshield.Extension;

public record BlockCustomExtensionCommand(
        String extension
) {

    public BlockedExtension toDomain() {
        return new BlockedExtension(
                null,
                Extension.of(extension),
                false,
                true
        );
    }
}
