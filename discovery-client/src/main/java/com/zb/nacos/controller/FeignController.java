package com.zb.nacos.controller;

import com.zb.nacos.client.MyClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by bzheng on 2020/6/15.
 */
@RestController
@RequestMapping("api/feign/")
public class FeignController {

    @Autowired
    MyClient myClient;

    @GetMapping("/hello")
    public String hello(@RequestParam("name") String name) {
        return myClient.hello(name);
    }
}
