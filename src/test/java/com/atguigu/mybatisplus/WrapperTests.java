package com.atguigu.mybatisplus;

import com.atguigu.mybatisplus.entity.User;
import com.atguigu.mybatisplus.mapper.UserMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class WrapperTests {
    @Resource
    private UserMapper userMapper;

    /**
     * 查询名字中包含n，年龄大于等于10且小于等于20，email不为空的用户
     */
    @Test
    public void test1() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .like("username", "花")
                .between("age", 23, 60)
                .isNotNull("email");

        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }

    /**
     * 按年龄降序查询用户，如果年龄相同则按id升序排列
     */
    @Test
    public void test2() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("age")
                .orderByAsc("id");

        List<User> users = userMapper.selectList(wrapper);
        users.forEach(System.out::println);
    }

    /**
     * 删除email is null的数据
     */
    @Test
    public void test3() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.isNull("email");

        int num = userMapper.delete(wrapper);
        System.out.println("删除的总数为：" + num);
    }

    /**
     * 查询名字中包含n，且（年龄小于18或email为空的用户），并将这些用户的年龄设置为18，邮箱设置为 user@atguigu.com
     */
    @Test
    public void test4() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.like("username", "n")
                .and(i -> i.lt("age", 18).or().isNull("email"));

        // 组装数据
        User user = new User();
        user.setAge(18);
        user.setEmail("user@atguigu.com");

        int num = userMapper.update(user, wrapper);
        System.out.println("影响行：" + num);
    }

    /**
     * 查询用户名和年龄
     */
    @Test
    public void test5() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.select("username", "age")
                .eq("email", "user@atguigu.com");

//        List<User> users = userMapper.selectList(wrapper);
        List<Map<String, Object>> maps = userMapper.selectMaps(wrapper);
        maps.forEach(System.out::println);
    }

    /**
     * 查询id小于3的用户
     */
    @Test
    public void test6() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.inSql("uid", "select uid from t_user where uid <= 3");

        List<Object> objs = userMapper.selectObjs(wrapper);
        objs.forEach(System.out::println);
    }

    /**
     * 使用updateWrapper
     * 查询名字中包含n，且（年龄小于18或email为空的用户），并将这些用户的年龄设置为18，邮箱设置为 user@atguigu.com
     */
    @Test
    public void test7() {
        UpdateWrapper<User> wrapper = new UpdateWrapper<>();
        wrapper.like("username", "n")
                .set("age", 19)
                .set("email", "chl@bupt.cn")
                .and(q -> q.lt("age", 18).or().isNull("email"));

        int update = userMapper.update(new User(), wrapper);
        System.out.println("影响结果：" + update);
    }

    /**
     * 查询名字中包含n，年龄大于10且小于20的用户，查询条件来源于用户输入，是可选的
     */
    @Test
    public void test8() {
        String username = null;
        Integer ageBegin = null;
        Integer ageEnd = 20;

        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.like(StringUtils.isNotBlank(username), "username", username)
                .gt(ageBegin != null, "age", ageBegin)
                .lt(ageEnd != null, "age", ageEnd);

        List<User> users = userMapper.selectList(wrapper);
        users.forEach(System.out::println);
    }

    /**
     * 使用lambdaQueryWrapper
     * 查询名字中包含n，年龄大于10且小于20的用户，查询条件来源于用户输入，是可选的
     */
    @Test
    public void test9() {
        String username = null;
        Integer ageBegin = null;
        Integer ageEnd = 20;

        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(StringUtils.isNotBlank(username), User::getName, username)
                .gt(ageBegin != null, User::getAge, ageBegin)
                .lt(ageEnd != null, User::getAge, ageEnd);

        List<User> users = userMapper.selectList(wrapper);
        users.forEach(System.out::println);
    }


    /**
     * 使用LambdaUpdateWrapper
     * 查询名字中包含n，且（年龄小于18或email为空的用户），并将这些用户的年龄设置为18，邮箱设置为 user@atguigu.com
     */
    @Test
    public void test10() {
        LambdaUpdateWrapper<User> wrapper = new LambdaUpdateWrapper<>();
        wrapper.like(User::getName, "n")
                .set(User::getAge, 19)
                .set(User::getEmail, "chl@bupt.cn")
                .and(q -> q.lt(User::getAge, 18).or().isNull(User::getAge));

        int update = userMapper.update(new User(), wrapper);
        System.out.println("影响结果：" + update);
    }
}
