package com.secondhandmarket.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * MyBatisPlus分页插件配置--配置类
 */
@Configuration
public class MyBatisPlusConfig {

    @Bean
    public PaginationInterceptor createPaginationInterceptor(){
        return new PaginationInterceptor();
    }
}
