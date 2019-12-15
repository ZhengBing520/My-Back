package com.zb.sys.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zb.App;
import com.zb.sys.entity.User;
import com.zb.sys.service.impl.UserServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Created by bzheng on 2019/12/9.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
public class UserServiceImplTest {

    @Autowired
    UserServiceImpl userService;

    @Test
    public void query() {
        QueryWrapper<User> queryWrapper = new QueryWrapper();
        queryWrapper.like("username", "aa");
        System.out.println(queryWrapper.getSqlSelect());
        System.out.println(queryWrapper.getSqlComment());
        System.out.println(queryWrapper.getSqlSegment());
        System.out.println(queryWrapper.getSqlSet());
        System.out.println(queryWrapper.getCustomSqlSegment());

        Page page = new Page(1,10);
        IPage page1 = userService.page(page, queryWrapper);
        List<User> records = page1.getRecords();
        records.forEach(user -> System.out.println(user.getUsername()));
    }
}
