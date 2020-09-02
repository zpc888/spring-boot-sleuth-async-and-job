/*
 * Copyright © 2018 Capco. All rights reserved.
 * Capco BlockWorkx
 *
 * You may obtain a copy of the License at
 *
 * capco-blockworkx-license.txt file under the top-level jar file
 *
 */

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
