package com.zb.nacos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Created by bzheng on 2020/6/12.
 */
@EnableDiscoveryClient // 开启Spring Cloud的服务注册与发现
@SpringBootApplication
public class DiscoveryServerApp {

    public static void main(String[] args) {
        SpringApplication.run(DiscoveryServerApp.class, args);
    }
}
