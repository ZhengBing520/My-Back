package com.zb.nacos.factory;

import com.zb.nacos.client.MyClient;
import com.zb.nacos.client.impl.MyClientImpl;
import feign.hystrix.FallbackFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by bzheng on 2020/6/15.
 */
@Component
public class FeignFactory implements FallbackFactory<MyClient> {

    @Autowired
    MyClientImpl myClient;

    @Override
    public MyClient create(Throwable cause) {
        // 打印错误日志
        cause.printStackTrace();
        return myClient;
    }


}
