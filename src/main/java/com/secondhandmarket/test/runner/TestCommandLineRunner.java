package com.secondhandmarket.test.runner;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TestCommandLineRunner implements CommandLineRunner {
    @Value("${bce.client.group}")
    private String bceGroup;
    @Override
    public void run(String... args) throws Exception {
        log.info("this is TestCommandLineRunner");
        log.info("==========get bce group name from value is :{}============",bceGroup);
    }
}
