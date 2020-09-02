/*
 * Copyright © 2018 Capco. All rights reserved.
 * Capco BlockWorkx
 *
 * You may obtain a copy of the License at
 *
 * capco-blockworkx-license.txt file under the top-level jar file
 *
 */

package com.cloud.sleuthclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class SchedulingService {
    private static final Logger LOG = Logger.getLogger(SchedulingService.class.getName());

    @Autowired
    SleuthService sleuthService;

//    @Scheduled(fixedDelay = 20000)
    public void scheduledWork() {
        LOG.info("Start some work from the scheduled task");
        sleuthService.asyncMethod();
        LOG.info("End work from scheduled task");
    }

}
