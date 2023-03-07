package com.secondhandmarket.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class BaiduAIRestTemplate {

    @Bean
    RestTemplate getBaiduClient(){
       return new RestTemplateBuilder()
                .build();
    }
}
