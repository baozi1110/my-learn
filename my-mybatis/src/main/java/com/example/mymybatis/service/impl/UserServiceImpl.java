package com.example.mymybatis.service.impl;

import com.example.mymybatis.mapper.UserMapper;
import com.example.mymybatis.model.User;
import com.example.mymybatis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Baozi
 */

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public User sel(int id){
        return userMapper.sel(id);
    }

    @Override
    public User finOne(int id) {
        return userMapper.findOne(id);
    }

    @Override
    public List<User> selectAll() {
        return userMapper.selectAll();
    }
}

