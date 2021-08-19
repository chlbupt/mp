package com.atguigu.mybatisplus.mapper;

import com.atguigu.mybatisplus.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;

//@Repository
public interface UserMapper extends BaseMapper<User> {
    List<User> selectAllByNameUsers(String name);

    // 查询：根据年龄查询用户列表，分页显示
    IPage<User> selectPageByAge(Page<User> page, Integer age);
}
