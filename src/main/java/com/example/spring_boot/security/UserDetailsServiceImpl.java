package com.example.spring_boot.security;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.spring_boot.domain.UserDetailsImpl;
import com.example.spring_boot.mapper.UserMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserMapper userMapper;

    public UserDetailsServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetailsImpl user = userMapper.selectOne(new QueryWrapper<UserDetailsImpl>().eq("username", username));
        if (user == null) {
            throw new UsernameNotFoundException("User Not Found with username: " + username);
        }
        return user;
    }
}
