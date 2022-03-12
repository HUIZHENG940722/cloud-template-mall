package com.ethan.mall.common.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author zhenghui
 * @name authenticationUser
 * @Description 认证用户实体
 * @Date 2022/3/12
 */
@Data
@NoArgsConstructor
public class AuthenticationUser {
    private Long id;
    private String username;
    private String password;
    private List<String> roles;
    private int status;
}
