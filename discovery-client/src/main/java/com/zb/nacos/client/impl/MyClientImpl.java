package com.zb.nacos.client.impl;

import com.zb.nacos.client.MyClient;
import org.springframework.stereotype.Component;

/**
 * Created by bzheng on 2020/6/15.
 */
@Component
public class MyClientImpl implements MyClient {
    @Override
    public String hello(String name) {
        return "hello";
    }
}
