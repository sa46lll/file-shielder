package com.flow.sa46lll.fileshield.port.out;

import com.flow.sa46lll.fileshield.domain.BlockedExtension;
import java.util.List;

public interface ReadExtensionPersistencePort {

    List<BlockedExtension> findAll();
}
