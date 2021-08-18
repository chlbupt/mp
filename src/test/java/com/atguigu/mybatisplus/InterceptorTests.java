package com.atguigu.mybatisplus;

import com.atguigu.mybatisplus.entity.User;
import com.atguigu.mybatisplus.mapper.UserMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
public class InterceptorTests {
    @Resource
    private UserMapper userMapper;

    @Test
    public void testSelectPage(){
        Page<User> userPage = new Page<>(2, 5);
        userMapper.selectPage(userPage, null);
        List<User> users = userPage.getRecords();
        users.forEach(System.out::println);

        long total = userPage.getTotal();
        System.out.println("总共" + total + "页");

        boolean bn = userPage.hasNext();
        System.out.println("下一页?" + bn);

        boolean bp = userPage.hasPrevious();
        System.out.println("上一页?" + bp);
    }
}
