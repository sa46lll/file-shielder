package com.flow.sa46lll.fileshield.dto;

import com.flow.sa46lll.fileshield.domain.BlockedExtension;
import com.flow.sa46lll.fileshield.domain.Extension;
import com.flow.sa46lll.fileshield.domain.ExtensionType;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record BlockCustomExtensionCommand(
        @NotBlank(message = "확장자는 필수입니다.")
        @Length(max = 20, message = "확장자는 20자 이하로 입력해주세요.")
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
