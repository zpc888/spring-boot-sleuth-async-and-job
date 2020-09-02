
package com.cloud.sleuthserver1;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class Server1Config {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
