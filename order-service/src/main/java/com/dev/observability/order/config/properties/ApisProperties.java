package com.dev.observability.order.config.properties;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties("apis")
public class ApisProperties {

    private ApiConfig notificator;
    private ApiConfig payment;
    private ApiConfig picking;

    @Data
    @NoArgsConstructor
    public static class ApiConfig {
        private String url;
    }

}
