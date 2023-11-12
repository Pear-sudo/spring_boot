package com.example.spring_boot.controllers;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.spring_boot.domain.UserDetailsImpl;
import com.example.spring_boot.mapper.UserMapper;
import com.example.spring_boot.mapper.UserService;
import com.example.spring_boot.payload.JsonResponse;
import com.example.spring_boot.payload.LoginRequest;
import com.example.spring_boot.payload.SignupRequest;
import com.example.spring_boot.services.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    private final UserMapper userMapper;
    private final UserService userService;
    private final RedisService redisService;

    @Autowired
    public AuthController(UserMapper userMapper, UserService userService, RedisService redisService) {
        this.userMapper = userMapper;
        this.userService = userService;
        this.redisService = redisService;
    }

    @GetMapping("/username/{username}")
    public boolean testUsername(@PathVariable String username) {
        Object usernameExist = redisService.get(username);
        if (usernameExist == null) {
            QueryWrapper<UserDetailsImpl> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("username", username);
//            UserDetailsImpl user = userMapper.selectOne(queryWrapper);
            UserDetailsImpl user = userService.getOne(queryWrapper);
            redisService.save(username, user != null);
            return user != null;
        } else {
            System.out.println("Cache hit.");
            return (boolean) usernameExist;
        }
    }

    @PostMapping("/userlogin")
    public JsonResponse login(@RequestBody LoginRequest loginRequest) {
        UserDetailsImpl user = getUser(loginRequest.getUsername());
        if (user != null) {
            if (Objects.equals(user.getPassword(), loginRequest.getPassword())) {
                return new JsonResponse("success", "login succeed", "some data");
            } else {
                return new JsonResponse("error", "wrong password", "some data");
            }
        } else {
            return new JsonResponse("error", "user not found", "some data");
        }
    }

    @PostMapping("/newuser")
    public JsonResponse newUser(@RequestBody SignupRequest signupRequest) {
        UserDetailsImpl user = getUser(signupRequest.getUsername());
        if (user != null) {
            return new JsonResponse("error", "user exists", "some data");
        } else {
            UserDetailsImpl newUser = new UserDetailsImpl(signupRequest.getUsername(), signupRequest.getPassword());
            userMapper.insert(newUser);
            if (getUser(signupRequest.getUsername()) != null) {
                return new JsonResponse("success", "user created", "some data");
            } else {
                return new JsonResponse("error", "please try again, internal error", "some data");
            }
        }
    }

    private UserDetailsImpl getUser(String username) {
        QueryWrapper<UserDetailsImpl> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        return userMapper.selectOne(queryWrapper);
    }
}
