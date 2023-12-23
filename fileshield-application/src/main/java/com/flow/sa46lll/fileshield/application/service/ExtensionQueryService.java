package com.flow.sa46lll.fileshield.application.service;

import com.flow.sa46lll.fileshield.BlockedExtension;
import com.flow.sa46lll.fileshield.application.dto.GetExtensionResponse;
import com.flow.sa46lll.fileshield.application.dto.GetExtensionsResponse;
import com.flow.sa46lll.fileshield.application.port.in.GetExtensionQuery;
import com.flow.sa46lll.fileshield.application.port.out.ExtensionPersistencePort;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ExtensionQueryService implements GetExtensionQuery {

    private final ExtensionPersistencePort extensionPersistencePort;

    public ExtensionQueryService(final ExtensionPersistencePort extensionPersistencePort) {
        this.extensionPersistencePort = extensionPersistencePort;
    }

    @Override
    public GetExtensionsResponse findAll() {
        List<BlockedExtension> extensions = extensionPersistencePort.findAll();
        List<GetExtensionResponse> fixedExtensions = extensions.stream()
                .filter(BlockedExtension::isFixed)
                .map(GetExtensionResponse::of)
                .toList();
        List<GetExtensionResponse> customExtensions = extensions.stream()
                .filter(extension -> !extension.isFixed())
                .map(GetExtensionResponse::of)
                .toList();
        return new GetExtensionsResponse(fixedExtensions, customExtensions);
    }
}
