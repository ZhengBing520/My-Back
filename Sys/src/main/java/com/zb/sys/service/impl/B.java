package com.zb.sys.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by bzheng on 2020/6/12.
 */
@Component
public class B {

    @Autowired
    A a;

    public B() {
        System.out.println("初始化 。。");
    }

    public A getA() {
        return a;
    }

    public void setA(A a) {
        this.a = a;
    }
}
