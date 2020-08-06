package com.zb.nacos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Created by bzheng on 2020/6/15.
 */
@RestController
@RequestMapping("api/nacos/")
public class HelloWordController {

    @Autowired
    LoadBalancerClient loadBalancerClient;

    @GetMapping("/hello")
    public String hello(@RequestParam("name") String name) {
        ServiceInstance choose = loadBalancerClient.choose("alibaba-nacos-discovery-server");
        String s = choose.getUri() + "/api/nacos/hello?name={1}";
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(s, String.class, name);
        return result;
    }


}
