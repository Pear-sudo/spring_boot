package com.example.spring_boot.domain;

import com.example.spring_boot.mapper.UserMapper;
import com.example.spring_boot.mapper.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DatabaseInitializer implements CommandLineRunner {

    private final UserMapper userMapper;
    private final UserService userService;

    public DatabaseInitializer(UserMapper userMapper, UserService userService) {
        this.userMapper = userMapper;
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {
//        userService.saveBatch(List.of(
//                new UserDetailsImpl("adam", "123456")
//        ));
        userMapper.insert(new UserDetailsImpl("adam", "123456"));
    }
}

