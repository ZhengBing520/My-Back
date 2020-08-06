package com.zb.nacos.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by bzheng on 2020/6/12.
 */
@RestController
@RequestMapping("api/nacos/")
public class HelloWordController {

    @Value("${server.port}")
    String port;

    @GetMapping("hello")
    public String hello(@RequestParam(required = false) String name) {
        String hello = "hello  nacos  " +port + " : " + name;
        return hello;
    }
}
