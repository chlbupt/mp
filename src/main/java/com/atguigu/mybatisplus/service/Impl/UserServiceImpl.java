package com.atguigu.mybatisplus.service.Impl;

import com.atguigu.mybatisplus.entity.User;
import com.atguigu.mybatisplus.mapper.UserMapper;
import com.atguigu.mybatisplus.service.UserService;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
//    @Resource
//    private UserMapper userMapper;

    @Override
    public List<User> listAllByName(String name) {
        return baseMapper.selectAllByNameUsers(name);
    }
}
