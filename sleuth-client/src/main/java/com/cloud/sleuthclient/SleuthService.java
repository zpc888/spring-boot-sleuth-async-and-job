
package com.cloud.sleuthclient;

import brave.Span;
import brave.Tracer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class SleuthService {
    private static final Logger LOG = Logger.getLogger(SleuthService.class.getName());

    @Autowired
    private Tracer tracer;

    public void doSomeWorkSameSpan() {
        try {
            Thread.sleep(500L);
        } catch (Exception ex) {
        }
        LOG.info("Doing some work");
    }

    public void doSomeWorkNewSpan() {
        LOG.info("I'm from the original span");
        Span newSpan = tracer.nextSpan().name("newSpan").start();
        try (Tracer.SpanInScope ws = tracer.withSpanInScope(newSpan.start())) {
            LOG.info("I'm in the new span doing some cool work that needs its own span");
            LOG.info("new-span ready to call same-span method");
            doSomeWorkSameSpan();
            LOG.info("new span ready to finish");
        } finally {
            newSpan.finish();
        }
        LOG.info("I'm back to the original span");
    }

    @Async
    public void asyncMethod() {
        LOG.info("Start Async Method");
        try {
            Thread.sleep(800L);
        } catch (Exception ex) {
        }
        LOG.info("End Async Method");
    }
}
