package com.zb.nacos.client;

import com.zb.nacos.factory.FeignFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.FeignClientsConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by bzheng on 2020/6/15.
 */
@FeignClient(value = "alibaba-nacos-discovery-server",
//        fallback = MyClientImpl.class,
        decode404 = true,
        configuration = FeignClientsConfiguration.class,
        fallbackFactory = FeignFactory.class
)
public interface MyClient {

    @GetMapping("/api/nacos/hello")
    String hello(@RequestParam(name = "name") String name);

}
