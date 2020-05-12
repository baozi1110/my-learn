package com.example.mymybatis.controller;


import com.example.mymybatis.model.User;
import com.example.mymybatis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Baozi
 */
@RestController
@RequestMapping("/testBoot")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("getUser/{id}")
    public String getUser(@PathVariable int id) {
        return userService.sel(id).toString();
    }

    @GetMapping("findOne/{id}")
    public String findOne(@PathVariable int id) {
        return userService.finOne(id).toString();
    }

    @GetMapping("getAll")
    public List<User> getAll() {
        return userService.selectAll();
    }
}
