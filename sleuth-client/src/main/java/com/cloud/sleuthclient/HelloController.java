
package com.cloud.sleuthclient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.client.RestTemplateCustomizer;
import org.springframework.cloud.sleuth.instrument.web.client.TraceWebClientAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Set;
import java.util.concurrent.Executor;

@RestController
public class HelloController {
    private static final Logger LOG = LoggerFactory.getLogger(HelloController.class);

    @Autowired
    @Qualifier("restTemplate1")
    RestTemplate restTemplate1;
    @Autowired
    @Qualifier("restTemplate2")
    RestTemplate restTemplate2;
    @Autowired
    SleuthService sleuthService;
//    @Autowired(required = false)
//    Set<RestTemplateCustomizer> customizers;
    @Autowired(required = false)
    RestTemplateBuilder restTemplateBuilder;

    @GetMapping(value="/zipkin")
    public String zipkinService1()
    {
        LOG.info("Inside zipkinService 1..");
//        RestTemplate restTemplate = new RestTemplate();
//        if (!CollectionUtils.isEmpty(this.customizers)) {
//            for (RestTemplateCustomizer customizer : this.customizers) {
//                customizer.customize(restTemplate);
//            }
//        }

        RestTemplate restTemplate = new RestTemplate();
        if (restTemplateBuilder != null) {
            restTemplateBuilder.configure(restTemplate);
        }
        String response  = (String) restTemplate.exchange("http://localhost:8081/zipkin",
                HttpMethod.GET, null, new ParameterizedTypeReference<String>() {}).getBody();
        String response2 = (String) restTemplate1.exchange("http://localhost:8082/zipkin2",
                HttpMethod.GET, null, new ParameterizedTypeReference<String>() {}).getBody();
        sleuthService.doSomeWorkSameSpan();
        return "Hi there, " + response;
    }

    @GetMapping("/new-span")
    public String helloNewSpan() {
        LOG.info("New Span");
        sleuthService.doSomeWorkNewSpan();
        return "success";
    }

    @Qualifier("syncExecutor")
    @Autowired
    Executor executor;

    @GetMapping("/new-thread")
    public String helloNewThread() {
        LOG.info("New Thread");
        Runnable runnable = () -> {
          try {
              Thread.sleep(600L);
          } catch (InterruptedException ex) {
          }
          LOG.info("I'm inside the new thread - with a new span");
        };
        executor.execute(runnable);
        LOG.info("Done. Ready to exit original trace");
        return "success";
    }

    @GetMapping("/async")
    public String helloAsync() {
        LOG.info("Before Async Method Call");
        sleuthService.asyncMethod();
        LOG.info("After Async Method Call");
        return "success";
    }
}
