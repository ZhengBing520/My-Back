package com.zb.server.service;

import com.zb.api.TestService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Value;

/**
 * Created by bzheng on 2020/6/16.
 */
@Service
public class TestServiceImpl implements TestService {

    @Value("${server.port}")
    Integer port;

    @Override
    public String hello(String name) {
        return port + " dubbo " + name;
    }
}
