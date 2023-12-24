package com.flow.sa46lll.fileshield.adapter.in.web;

import com.flow.sa46lll.fileshield.application.dto.ApiResponse;
import com.flow.sa46lll.fileshield.application.dto.BlockCustomExtensionCommand;
import com.flow.sa46lll.fileshield.application.dto.BlockCustomExtensionResponse;
import com.flow.sa46lll.fileshield.application.dto.GetExtensionsResponse;
import com.flow.sa46lll.fileshield.application.port.in.BlockCustomExtensionUseCase;
import com.flow.sa46lll.fileshield.application.port.in.BlockFixedExtensionUseCase;
import com.flow.sa46lll.fileshield.application.port.in.GetExtensionQuery;
import com.flow.sa46lll.fileshield.application.port.in.UnblockCustomExtensionUseCase;
import com.flow.sa46lll.fileshield.application.port.in.UnblockFixedExtensionUseCase;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/file-extensions")
public class ExtensionRestController {

    private final BlockCustomExtensionUseCase blockCustomExtensionUseCase;
    private final UnblockCustomExtensionUseCase unblockCustomExtensionUseCase;
    private final BlockFixedExtensionUseCase blockFixedExtensionUseCase;
    private final UnblockFixedExtensionUseCase unblockFixedExtensionUseCase;
    private final GetExtensionQuery getExtensionQuery;

    public ExtensionRestController(final BlockCustomExtensionUseCase blockCustomExtensionUseCase,
                                   final UnblockCustomExtensionUseCase unblockCustomExtensionUseCase,
                                   final BlockFixedExtensionUseCase blockFixedExtensionUseCase,
                                   final UnblockFixedExtensionUseCase unblockFixedExtensionUseCase,
                                   final GetExtensionQuery getExtensionQuery) {
        this.blockCustomExtensionUseCase = blockCustomExtensionUseCase;
        this.unblockCustomExtensionUseCase = unblockCustomExtensionUseCase;
        this.blockFixedExtensionUseCase = blockFixedExtensionUseCase;
        this.unblockFixedExtensionUseCase = unblockFixedExtensionUseCase;
        this.getExtensionQuery = getExtensionQuery;
    }

    /**
     * 고정 확장자 차단
     *
     * @param extensionId 차단할 고정 확장자 식별자
     */
    @PatchMapping("/{extensionId}/fixed-block")
    public ApiResponse<Void> blockFixed(@PathVariable("extensionId") final Long extensionId) {
        blockFixedExtensionUseCase.blockFixed(extensionId);
        return ApiResponse.ok();
    }

    /**
     * 고정 확장자 차단 해제
     *
     * @param extensionId 차단 해제할 확장자 식별자
     */
    @PatchMapping("/{extensionId}/fixed-unblock")
    public ApiResponse<Void> unblockFixed(@PathVariable("extensionId") final Long extensionId) {
        unblockFixedExtensionUseCase.unblockFixed(extensionId);
        return ApiResponse.ok();
    }

    /**
     * 커스텀 확장자 차단
     *
     * @param blockCustomExtensionCommand 차단할 커스텀 확장자
     */
    @PostMapping("/custom-block")
    public ApiResponse<BlockCustomExtensionResponse> blockCustom(@RequestBody final BlockCustomExtensionCommand blockCustomExtensionCommand) {
        return ApiResponse.ok(blockCustomExtensionUseCase.blockCustom(blockCustomExtensionCommand));
    }

    /**
     * 커스텀 확장자 차단 해제
     *
     * @param extensionId 차단 해제할 확장자 식별자
     */
    @DeleteMapping("/{extensionId}/custom-unblock")
    public ApiResponse<Void> unblockCustom(@PathVariable("extensionId") final Long extensionId) {
        unblockCustomExtensionUseCase.unblockCustom(extensionId);
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
