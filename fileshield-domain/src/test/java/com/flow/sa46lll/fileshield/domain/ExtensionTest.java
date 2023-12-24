package com.flow.sa46lll.fileshield.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

import com.flow.sa46lll.fileshield.exception.InvalidInputException;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class ExtensionTest {

    @ParameterizedTest
    @ValueSource(strings = {" ", "  "})
    void 확장자가_비어있으면_예외가_발생한다(String extension) {
        assertThatThrownBy(() -> Extension.of(extension))
                .isInstanceOf(InvalidInputException.class)
                .hasMessage("확장자는 20자 이하로 입력해주세요.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"abcdefghijklmnopqrstuvwxyz", "123456789012345678901"})
    void 확장자가_20자_초과면_예외가_발생한다(String extension) {
        assertThatThrownBy(() -> Extension.of(extension))
                .isInstanceOf(InvalidInputException.class)
                .hasMessage("확장자는 20자 이하로 입력해주세요.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"가나다라", "ㄱㄴㄷㄹ", "!@#$%^&*()"})
    void 확장자가_영문이나_숫자가_아니면_예외가_발생한다(String extension) {
        assertThatThrownBy(() -> Extension.of(extension))
                .isInstanceOf(InvalidInputException.class)
                .hasMessage("확장자는 영문, 숫자만 입력해주세요.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"jpg", "png", "gif"})
    void 확장자가_영문과_숫자로_이루어져_있으면_정상적으로_생성된다(String extension) {
        Extension actual = Extension.of(extension);
        assertEquals(extension, actual.getExtension());
    }
}
