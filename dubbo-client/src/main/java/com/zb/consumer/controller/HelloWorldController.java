package com.zb.consumer.controller;

import com.zb.api.TestService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by bzheng on 2020/6/16.
 */
@RestController
@RequestMapping("api/dubbo/")
public class HelloWorldController {

    // 默认就是随机
    @Reference(loadbalance = "random")
    TestService testService;

    @GetMapping("/hello")
    public String hello(@RequestParam("name") String name) {
        return testService.hello(name);
    }
}
