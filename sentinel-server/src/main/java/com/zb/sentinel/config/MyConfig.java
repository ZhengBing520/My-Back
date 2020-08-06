package com.zb.sentinel.config;

import com.alibaba.csp.sentinel.annotation.aspectj.SentinelResourceAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by bzheng on 2020/6/16.
 */
@Configuration
public class MyConfig {

    /**
     * 配置了nacos之后，sentinel控制台流控不起作用
     * @return
     */
    @Bean
    public SentinelResourceAspect sentinelResourceAspect() {
        return new SentinelResourceAspect();
    }
}
