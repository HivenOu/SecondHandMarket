package com.secondhandmarket.test.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

@Slf4j
public class TestSpringApplicationRunListener implements SpringApplicationRunListener {

    public TestSpringApplicationRunListener(SpringApplication application, String[] args) {
    }
    @Override
    public void starting() {
        log.info("TestSpringApplicationRunListener starting");
    }

    @Override
    public void environmentPrepared(ConfigurableEnvironment environment) {
        log.info("TestSpringApplicationRunListener environmentPrepared");
    }

    @Override
    public void contextPrepared(ConfigurableApplicationContext context) {
        log.info("TestSpringApplicationRunListener contextPrepared");
    }

    @Override
    public void contextLoaded(ConfigurableApplicationContext context) {
        log.info("TestSpringApplicationRunListener contextLoaded");
    }

    @Override
    public void started(ConfigurableApplicationContext context) {
        log.info("TestSpringApplicationRunListener started");
    }

    @Override
    public void running(ConfigurableApplicationContext context) {
        log.info("TestSpringApplicationRunListener running");
    }

    @Override
    public void failed(ConfigurableApplicationContext context, Throwable exception) {
        log.info("TestSpringApplicationRunListener failed");
    }
}
