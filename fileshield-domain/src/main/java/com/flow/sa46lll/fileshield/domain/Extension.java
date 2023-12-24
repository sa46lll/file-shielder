package com.flow.sa46lll.fileshield.domain;

import com.flow.sa46lll.fileshield.exception.InvalidInputException;

public class Extension {

    private static final int MAX_EXTENSION_LENGTH = 20;
    private static final String PATTERN = "^[a-zA-Z0-9]*$";

    private String extension;

    public Extension(final String extension) {
        this.extension = extension;
    }

    public static Extension of(final String extension) {
        validate(extension);
        return new Extension(extension);
    }

    public String getExtension() {
        return extension;
    }

    private static void validate(String extension) {
        if (extension == null || extension.isBlank() || extension.length() > MAX_EXTENSION_LENGTH) {
            throw new InvalidInputException("확장자는 20자 이하로 입력해주세요.");
        }

        if (!extension.matches(PATTERN)) {
            throw new InvalidInputException("확장자는 영문, 숫자만 입력해주세요.");
        }
    }
}
