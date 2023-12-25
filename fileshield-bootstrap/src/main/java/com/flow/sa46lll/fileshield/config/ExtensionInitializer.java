package com.flow.sa46lll.fileshield.config;

import com.flow.sa46lll.fileshield.domain.ExtensionType;
import com.flow.sa46lll.fileshield.dto.SaveExtensionDto;
import com.flow.sa46lll.fileshield.port.in.SaveExtensionUseCase;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * 애플리케이션이 구동될 때, 초기 데이터를 생성하는 클래스입니다.
 */
@Component
public class ExtensionInitializer implements ApplicationRunner {

    private final SaveExtensionUseCase saveExtensionUsecase;

    public ExtensionInitializer(final SaveExtensionUseCase saveExtensionUsecase) {
        this.saveExtensionUsecase = saveExtensionUsecase;
    }

    @Override
    public void run(ApplicationArguments args) {
        SaveExtensionDto fixed = new SaveExtensionDto("exe", ExtensionType.FIXED, true);
        SaveExtensionDto fixed2 = new SaveExtensionDto("bat", ExtensionType.FIXED, false);
        SaveExtensionDto custom = new SaveExtensionDto("cmd", ExtensionType.CUSTOM, true);

        saveExtensionUsecase.save(fixed);
        saveExtensionUsecase.save(fixed2);
        saveExtensionUsecase.save(custom);
    }
}
