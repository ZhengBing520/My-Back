package com.zb.nacos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/**
 * Created by bzheng on 2020/6/15.
 */
@RestController
@RequestMapping("/api/webClient/")
public class WebClientController {

    @Autowired
    WebClient.Builder webClientBuilder;

    @GetMapping("/hello")
    public Mono<String> hello(@RequestParam("name") String name) {
        String name2 = Thread.currentThread().getName();
        System.out.println(name2);
        Mono<String> name1 = webClientBuilder.build().get().uri("http://alibaba-nacos-discovery-server/api/nacos/hello?name={1}", name).retrieve().bodyToMono(String.class);
        return name1;
    }

}
