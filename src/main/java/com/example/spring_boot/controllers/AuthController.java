package com.example.spring_boot.controllers;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.spring_boot.domain.User;
import com.example.spring_boot.mapper.UserMapper;
import com.example.spring_boot.services.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserMapper userMapper;
    private final RedisService redisService;

    @Autowired
    public AuthController(UserMapper userMapper, RedisService redisService) {
        this.userMapper = userMapper;
        this.redisService = redisService;
    }

    @GetMapping("/username/{username}")
    public boolean testUsername(@PathVariable String username) {
        Object usernameExist = redisService.get(username);
        if (usernameExist == null) {
            QueryWrapper<User> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("username", username);
            User user = userMapper.selectOne(queryWrapper);
            redisService.save(username, user != null);
            return user != null;
        } else {
            System.out.println("Cache hit.");
            return (boolean) usernameExist;
        }
    }
}
