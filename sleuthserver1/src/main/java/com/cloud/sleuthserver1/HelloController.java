
package com.cloud.sleuthserver1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.logging.Logger;

@RestController
public class HelloController {
    private static final Logger LOG = Logger.getLogger(HelloController.class.getName());

    @Autowired
    RestTemplate restTemplate;

    @GetMapping(value = "/zipkin")
    public String hello() {
        LOG.info("reach server 1...");
        try {
            Thread.sleep(300);
        } catch (Exception ex) {
        }
        String response = (String) restTemplate.exchange("http://localhost:8082/zipkin",
                HttpMethod.GET, null, new ParameterizedTypeReference<String>() {}).getBody();
        LOG.info("bla bla return by server 1...");
        return "server 1 says bla bla <br />" + response;
    }
}
