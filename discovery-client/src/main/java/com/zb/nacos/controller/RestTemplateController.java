package com.zb.nacos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Created by bzheng on 2020/6/15.
 */
@RestController
@RequestMapping("/api/rest/")
public class RestTemplateController {

    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/hello")
    public String hello(@RequestParam("name") String name) {
        String result = restTemplate.getForObject("http://alibaba-nacos-discovery-server/api/nacos/hello?name={1}", String.class, name);
        return result;
    }

}
