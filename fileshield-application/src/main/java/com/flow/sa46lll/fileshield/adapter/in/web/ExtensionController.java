package com.flow.sa46lll.fileshield.adapter.in.web;

import com.flow.sa46lll.fileshield.application.dto.ApiResponse;
import com.flow.sa46lll.fileshield.application.dto.BlockCustomExtensionCommand;
import com.flow.sa46lll.fileshield.application.dto.GetExtensionsResponse;
import com.flow.sa46lll.fileshield.application.port.in.BlockExtensionUseCase;
import com.flow.sa46lll.fileshield.application.port.in.GetExtensionQuery;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/file-extensions")
public class ExtensionController {

    private final BlockExtensionUseCase blockExtensionUseCase;
    private final GetExtensionQuery getExtensionQuery;

    public ExtensionController(final GetExtensionQuery getExtensionQuery,
                               final BlockExtensionUseCase blockExtensionUseCase) {
        this.blockExtensionUseCase = blockExtensionUseCase;
        this.getExtensionQuery = getExtensionQuery;
    }

    /**
     * 커스텀 확장자 차단
     *
     * @param blockCustomExtensionCommand 차단할 커스텀 확장자
     */
    @PostMapping("/custom-block")
    public ApiResponse<Void> block(@RequestBody final BlockCustomExtensionCommand blockCustomExtensionCommand) {
        blockExtensionUseCase.block(blockCustomExtensionCommand);
        return ApiResponse.ok();
    }

    /**
     * 확장자 목록 조회
     *
     * @return 확장자 목록
     */
    @GetMapping
    public ApiResponse<GetExtensionsResponse> findAll() {
        return ApiResponse.ok(getExtensionQuery.findAll());
    }


}
