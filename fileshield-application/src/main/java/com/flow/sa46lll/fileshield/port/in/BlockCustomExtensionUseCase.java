package com.flow.sa46lll.fileshield.port.in;

import com.flow.sa46lll.fileshield.dto.BlockCustomExtensionCommand;
import com.flow.sa46lll.fileshield.dto.BlockCustomExtensionResponse;

public interface BlockCustomExtensionUseCase {

    BlockCustomExtensionResponse blockCustom(final BlockCustomExtensionCommand blockCustomExtensionCommand);
}
