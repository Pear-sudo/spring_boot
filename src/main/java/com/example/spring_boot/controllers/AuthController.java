package com.example.spring_boot.controllers;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.spring_boot.domain.User;
import com.example.spring_boot.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserMapper userMapper;

    @Autowired
    public AuthController(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @GetMapping("/username/{username}")
    public boolean testUsername(@PathVariable String username) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        User user = userMapper.selectOne(queryWrapper);
        return user != null;
    }
}
