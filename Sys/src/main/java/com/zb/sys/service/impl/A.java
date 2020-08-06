package com.zb.sys.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by bzheng on 2020/6/12.
 */
@Component
public class A {

    @Autowired
    B b;

    public A() {
        System.out.println("初始化A 。。");
    }

    public B getB() {
        return b;
    }

    public void setB(B b) {
        this.b = b;
    }
}
