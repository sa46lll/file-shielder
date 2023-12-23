package com.flow.sa46lll.fileshield.application.dto;

import com.flow.sa46lll.fileshield.BlockedExtension;
import com.flow.sa46lll.fileshield.Extension;
import com.flow.sa46lll.fileshield.ExtensionType;

public record BlockCustomExtensionCommand(
        String extension
) {

    public BlockedExtension toDomain() {
        return new BlockedExtension(
                null,
                Extension.of(extension),
                ExtensionType.CUSTOM,
                true
        );
    }
}
