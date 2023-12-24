package com.flow.sa46lll.fileshield.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import com.flow.sa46lll.fileshield.domain.BlockedExtension;
import com.flow.sa46lll.fileshield.domain.Extension;
import com.flow.sa46lll.fileshield.domain.ExtensionType;
import com.flow.sa46lll.fileshield.port.out.ReadExtensionPersistencePort;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ExtensionQueryServiceTest {

    @InjectMocks
    private ExtensionQueryService sut;

    @Mock
    private ReadExtensionPersistencePort readExtensionPersistencePort;

    @Test
    void 확장자_목록을_조회한다() {
        List<BlockedExtension> blockedExtensions = List.of(
                new BlockedExtension(1L, Extension.of("bat"), ExtensionType.FIXED, true));

        when(readExtensionPersistencePort.findAll()).thenReturn(blockedExtensions);

        assertThat(sut.findAll()).isNotNull();
    }
}
