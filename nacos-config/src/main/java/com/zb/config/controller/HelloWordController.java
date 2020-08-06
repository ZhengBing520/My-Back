package com.zb.config.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by bzheng on 2020/6/15.
 */
@RestController
@RequestMapping("api/config/")
@RefreshScope
public class HelloWordController {

    @Value("${didispace.title}")
    String title;

    @Value("${didispace.log}")
    String logTitle;

    @Value("${didispace.actuatror}")
    String actuatror;

    @GetMapping("/hello")
    public String hello(@RequestParam("name") String name) {
        return title + logTitle + actuatror;
    }
}
