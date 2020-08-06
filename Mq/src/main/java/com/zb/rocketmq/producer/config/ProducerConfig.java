package com.zb.rocketmq.producer.config;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Created by bzheng on 2020/1/17.
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "rocketmq.producer")
@ToString
public class ProducerConfig {
    private String namesrvAddr;

    private String groupName;
}
