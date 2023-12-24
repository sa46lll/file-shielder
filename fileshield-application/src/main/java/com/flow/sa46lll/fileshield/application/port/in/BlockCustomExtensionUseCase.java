package com.flow.sa46lll.fileshield.application.port.in;

import com.flow.sa46lll.fileshield.application.dto.BlockCustomExtensionCommand;
import com.flow.sa46lll.fileshield.application.dto.BlockCustomExtensionResponse;

public interface BlockCustomExtensionUseCase {

    BlockCustomExtensionResponse blockCustom(final BlockCustomExtensionCommand blockCustomExtensionCommand);
}
