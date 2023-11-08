package com.example.spring_boot.domain;

import com.baomidou.mybatisplus.annotation.TableName;

@TableName("`user`")
public class User {
    private Long id;
    private String username;
}
