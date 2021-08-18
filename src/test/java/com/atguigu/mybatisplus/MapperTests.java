package com.atguigu.mybatisplus;

import com.atguigu.mybatisplus.entity.User;
import com.atguigu.mybatisplus.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class MapperTests {

    @Resource
    private UserMapper userMapper;

    @Test
    public void testInsert() {
        User user = new User();
//        user.setAge(74);
        user.setEmail("jianguo@qq.com");
        user.setName("慧慧7");
//        user.setCreatedAt(LocalDateTime.now());
//        user.setUpdatedAt(LocalDateTime.now());
        final int result = userMapper.insert(user);
        System.out.println("结果：" + result);

    }

    @Test
    public void testSelect() {
        // selectById
        User user = userMapper.selectById(1);
        System.out.println(user);

        // selectBatchIds
        List<User> users = userMapper.selectBatchIds(Arrays.asList(1, 2, 3));
        users.forEach(System.out::println);

        // selectByMap
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("username", "建国");
        map.put("age", 74);
        List<User> users1 = userMapper.selectByMap(map);
        users1.forEach(System.out::println);

    }

    @Test
    public void testUpdate() {
        User user = new User();
        user.setId(1427637477516017678l);
        user.setAge(28);

        final int res = userMapper.updateById(user);
        System.out.println("结果：" + res);
    }

    @Test
    public void testDelete() {
        final int i = userMapper.deleteById(3);
        System.out.println("结果：" + i);
    }

    @Test
    public void testSelectAllByName(){
        List<User> users = userMapper.selectAllByNameUsers("Jack");
        users.forEach(System.out::println);
    }
}
