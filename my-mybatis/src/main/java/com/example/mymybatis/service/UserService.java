package com.example.mymybatis.service;

import com.example.mymybatis.model.User;

import java.util.List;

/**
 * @author Baozi
 */
public interface UserService {

    User sel(int id);

    User finOne(int id);

    List<User> selectAll();
}
