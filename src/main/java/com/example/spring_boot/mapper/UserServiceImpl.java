package com.example.spring_boot.mapper;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.spring_boot.domain.UserDetailsImpl;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserDetailsImpl> implements UserService {

}

