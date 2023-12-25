package com.flow.sa46lll.fileshield.dto;

import com.flow.sa46lll.fileshield.domain.ExtensionType;

public record SaveExtensionDto(
        String extension,
        ExtensionType extensionType,
        boolean isBlocked
) {

}
