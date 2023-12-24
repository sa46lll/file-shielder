package com.flow.sa46lll.fileshield.service;

import com.flow.sa46lll.fileshield.domain.BlockedExtension;
import com.flow.sa46lll.fileshield.dto.GetExtensionResponse;
import com.flow.sa46lll.fileshield.dto.GetExtensionsResponse;
import com.flow.sa46lll.fileshield.port.in.GetExtensionQuery;
import com.flow.sa46lll.fileshield.port.out.ReadExtensionPersistencePort;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class ExtensionQueryService implements GetExtensionQuery {

    private final ReadExtensionPersistencePort readExtensionPersistencePort;

    public ExtensionQueryService(final ReadExtensionPersistencePort readExtensionPersistencePort) {
        this.readExtensionPersistencePort = readExtensionPersistencePort;
    }

    @Override
    public GetExtensionsResponse findAll() {
        List<BlockedExtension> extensions = readExtensionPersistencePort.findAll();
        List<GetExtensionResponse> fixedExtensions = extensions.stream()
                .filter(BlockedExtension::isFixed)
                .map(GetExtensionResponse::of)
                .toList();
        List<GetExtensionResponse> customExtensions = extensions.stream()
                .filter(BlockedExtension::isCustom)
                .map(GetExtensionResponse::of)
                .toList();
        return new GetExtensionsResponse(fixedExtensions, customExtensions);
    }
}
