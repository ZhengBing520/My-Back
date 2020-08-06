package com.zb.nacos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Created by bzheng on 2020/6/12.
 */
@EnableDiscoveryClient // 开启Spring Cloud的服务注册与发现
@SpringBootApplication
@EnableFeignClients
public class DiscoveryClientApp {

    public static void main(String[] args) {
        SpringApplication.run(DiscoveryClientApp.class, args);
    }
}
