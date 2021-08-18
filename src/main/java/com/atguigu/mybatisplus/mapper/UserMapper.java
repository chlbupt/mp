package com.atguigu.mybatisplus.mapper;

import com.atguigu.mybatisplus.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

//@Repository
public interface UserMapper extends BaseMapper<User> {
    List<User> selectAllByNameUsers(String name);
}
