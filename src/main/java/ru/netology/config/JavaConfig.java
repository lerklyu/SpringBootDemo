package ru.netology.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import ru.netology.service.DevProfile;
import ru.netology.service.ProductionProfile;
import ru.netology.service.SystemProfile;

@Configuration
public class JavaConfig {

    @Bean
    @ConditionalOnProperty(
            name = "netology.profile.dev",
            havingValue = "true")
    public SystemProfile devProfile() {
        return new DevProfile();
    }

    @Bean
    @ConditionalOnProperty(
            name = "netology.profile.dev",
            havingValue = "false",
            matchIfMissing = true)
    public SystemProfile prodProfile() {
        return new ProductionProfile();
    }

}
