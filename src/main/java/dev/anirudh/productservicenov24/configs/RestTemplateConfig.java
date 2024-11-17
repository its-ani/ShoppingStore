package dev.anirudh.productservicenov24.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
// @Configuration tells the spring to create the object of this class. So, that classes do not create dependencies themselves. we want spring to create dependency on its own.
public class RestTemplateConfig {
    @Bean
    //spring bean
    //Rest template is used to call external APIs
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}