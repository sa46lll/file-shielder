package com.flow.sa46lll.fileshield.application.dto;

import java.util.List;

public record GetExtensionsResponse(
        List<GetExtensionResponse> fixedExtensions,
        List<GetExtensionResponse> customExtensions
) {

}
