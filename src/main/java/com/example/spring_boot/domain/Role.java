package com.example.spring_boot.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

@Data
@TableName("`role`")
public class Role implements GrantedAuthority {
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;
    private String authority;
    public Role(String authority) {
        this.authority = authority;
    }
}
