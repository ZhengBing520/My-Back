package com.zb.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Created by bzheng on 2020/6/16.
 */
@SpringBootApplication
@EnableDiscoveryClient
public class DoubleClientApp {

    public static void main(String[] args) {
        SpringApplication.run(DoubleClientApp.class, args);
    }
}
