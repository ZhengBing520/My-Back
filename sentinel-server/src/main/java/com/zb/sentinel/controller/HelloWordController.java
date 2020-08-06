package com.zb.sentinel.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by bzheng on 2020/6/15.
 */
@RestController
@RequestMapping("api/sentinel/")
public class HelloWordController {

    @GetMapping("hello")
    public String hello(@RequestParam(required = false) String name) {
        String hello = "hello  nacos  " + name;
        return hello;
    }
}
