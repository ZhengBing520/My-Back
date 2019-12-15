package com.zb.sys.service;

import com.zb.App;
import com.zb.sys.entity.UserRole;
import com.zb.sys.service.impl.UserRoleServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

/**
 * Created by bzheng on 2019/11/28.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
public class UserRoleServiceImplTest {

    @Autowired
    UserRoleServiceImpl userRoleService;

    @Test
    public void test1() {
        UserRole user = new UserRole();
        user.setUserId(1L);
        user.setRoleCode("hello");
        user.setCreateTime(LocalDateTime.now());
        userRoleService.save(user);
        System.out.println(user.getId());
    }
}
