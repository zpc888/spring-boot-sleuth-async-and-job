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

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@RestController
public class HelloController {
    private static final Logger LOG = Logger.getLogger(HelloController.class.getName());

    @GetMapping(value = "/zipkin")
    public String hello() {
        LOG.info("reach server 2...");
        try {
            Thread.sleep(1000);
        } catch (Exception ex) {
        }
        LOG.info("return by server 2...");
        return "server 2 says hello";
    }

    @GetMapping(value = "/zipkin2")
    public String hello2() {
        LOG.info("reach server 2 for zipkin2...");
        try {
            Thread.sleep(1000);
        } catch (Exception ex) {
        }
        LOG.info("return by server 2 for zipkin2...");
        return "server 2 says hello for zipkin2";
    }
}
