package com.example.spring_boot.security;

import com.example.spring_boot.mapper.UserMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.UserDetailsManager;

public class UserDetailsManagerImpl extends UserDetailsServiceImpl implements UserDetailsManager {
    public UserDetailsManagerImpl(UserMapper userMapper) {
        super(userMapper);
    }

    @Override
    public void createUser(UserDetails user) {

    }

    @Override
    public void updateUser(UserDetails user) {

    }

    @Override
    public void deleteUser(String username) {

    }

    @Override
    public void changePassword(String oldPassword, String newPassword) {

    }

    @Override
    public boolean userExists(String username) {
        return false;
    }
}
