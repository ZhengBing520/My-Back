package com.zb.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Created by bzheng on 2020/6/16.
 */
@SpringBootApplication
@EnableDiscoveryClient
public class DubboServerApp {

    public static void main(String[] args) {
        SpringApplication.run(DubboServerApp.class, args);
    }
}
