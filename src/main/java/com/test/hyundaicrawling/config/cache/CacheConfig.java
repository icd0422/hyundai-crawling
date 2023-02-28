package com.test.hyundaicrawling.config.cache;

import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableCaching
@Configuration
public class CacheConfig extends CachingConfigurerSupport {

    @Bean("customKeyGenerator")
    @Override
    public KeyGenerator keyGenerator() {
        return new CustomKeyGenerator();
    }
}
