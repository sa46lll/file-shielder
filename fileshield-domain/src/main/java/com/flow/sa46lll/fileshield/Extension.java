package com.flow.sa46lll.fileshield;

public class Extension {

    String extension;

    public Extension(final String extension) {
        this.extension = extension;
    }

    public static Extension of(final String extension) {
        if (extension == null || extension.isBlank() || extension.length() > 20) {
            throw new IllegalArgumentException("확장자는 20자 이하로 입력해주세요.");
        }
        return new Extension(extension);
    }
}
