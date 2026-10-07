package ru.netology.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.netology.service.DevProfile;
import ru.netology.service.ProductionProfile;
import ru.netology.service.SystemProfile;

@Configuration
public class JavaConfig {

    @Bean
    @ConditionalOnProperty("netology.profile.dev")
    public SystemProfile devProfile() {
        return new DevProfile();
    }

    @Bean
    @ConditionalOnProperty("netology.profile.prod")
    public SystemProfile prodProfile() {
        return new ProductionProfile();
    }
}
