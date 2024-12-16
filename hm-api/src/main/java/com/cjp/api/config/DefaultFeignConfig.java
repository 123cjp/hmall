package com.cjp.api.config;

import org.springframework.context.annotation.Bean;

import feign.Logger;
/**
 * @author 陈建平
 */
public class DefaultFeignConfig {
    @Bean
    public Logger.Level feignLoggerLevel(){
        return Logger.Level.FULL;
    }
}
