package com.flow.sa46lll.fileshield.adapter.in.web;

import com.flow.sa46lll.fileshield.application.dto.ApiResponse;
import com.flow.sa46lll.fileshield.application.dto.GetExtensionsResponse;
import com.flow.sa46lll.fileshield.application.port.in.GetExtensionQuery;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class ExtensionController {

    private final GetExtensionQuery getExtensionQuery;

    public ExtensionController(final GetExtensionQuery getExtensionQuery) {
        this.getExtensionQuery = getExtensionQuery;
    }

    @GetMapping("/file-extensions")
    public ApiResponse<GetExtensionsResponse> findAll() {
        return ApiResponse.ok(getExtensionQuery.findAll());
    }
}
