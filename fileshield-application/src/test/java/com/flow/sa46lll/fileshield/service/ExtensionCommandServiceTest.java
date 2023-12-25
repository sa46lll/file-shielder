package com.flow.sa46lll.fileshield.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.flow.sa46lll.fileshield.dto.BlockCustomExtensionCommand;
import com.flow.sa46lll.fileshield.exception.ExtensionDuplicationException;
import com.flow.sa46lll.fileshield.port.out.ReadExtensionPersistencePort;
import com.flow.sa46lll.fileshield.port.out.WriteExtensionPersistencePort;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ExtensionCommandServiceTest {

    @InjectMocks
    private ExtensionCommandService sut;

    @Mock
    private WriteExtensionPersistencePort writeExtensionPersistencePort;

    @Mock
    private ReadExtensionPersistencePort readExtensionPersistencePort;

    @Test
    void 고정_확장자를_차단한다() {
        sut.blockFixed(1L);

        verify(writeExtensionPersistencePort).updateExtensionBlockStatus(any());
    }

    @Test
    void 고정_확장자의_차단을_해제한다() {
        sut.unblockFixed(1L);

        verify(writeExtensionPersistencePort).updateExtensionBlockStatus(any());
    }

    @Nested
    class 커스텀_확장자를_차단할_때 {

        @Test
        void 확장자가_중복되면_예외가_발생한다() {
            BlockCustomExtensionCommand command = new BlockCustomExtensionCommand("bat");

            when(writeExtensionPersistencePort.existsByExtension(any())).thenReturn(true);

            assertThrows(ExtensionDuplicationException.class, () -> sut.blockCustom(command));
        }

        @Test
        void 확장자가_중복되지_않으면_차단한다() {
            BlockCustomExtensionCommand command = new BlockCustomExtensionCommand("bat");

            when(writeExtensionPersistencePort.existsByExtension(any())).thenReturn(false);
            when(readExtensionPersistencePort.countByExtensionType(any())).thenReturn(0);
            sut.blockCustom(command);

            verify(writeExtensionPersistencePort).blockCustomExtension(any());
        }
    }

    @Test
    void 커스텀_확장자의_차단을_해제한다() {
        sut.unblockCustom(1L);

        verify(writeExtensionPersistencePort).deleteCustomExtensionById(any());
    }
}
