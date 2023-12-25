package com.flow.sa46lll.fileshield.mapper;

import com.flow.sa46lll.fileshield.domain.ExtensionType;
import com.flow.sa46lll.fileshield.entity.ExtensionTypeEntity;

public class ExtensionTypeMapper {

    private ExtensionTypeMapper() {
    }

    public static ExtensionTypeEntity toEntity(final ExtensionType extensionType) {
        return ExtensionTypeEntity.valueOf(extensionType.name());
    }
}
