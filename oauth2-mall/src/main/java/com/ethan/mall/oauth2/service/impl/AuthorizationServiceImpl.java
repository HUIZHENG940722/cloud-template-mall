package com.ethan.mall.oauth2.service.impl;

import com.ethan.mall.common.domain.AuthenticationUser;
import com.ethan.mall.oauth2.domain.AuthorizationUser;
import com.ethan.mall.oauth2.service.feign.AdminService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author zhenghui
 * @name UserDetailsServiceImpl
 * @Description 授权业务类
 * @Date 2022/3/12
 */
@Service
public class AuthorizationServiceImpl implements UserDetailsService {
    @Resource
    private AdminService adminService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AuthenticationUser authenticationUser = adminService.loadUserByUsername(username);
        return (AuthorizationUser) authenticationUser;
    }
}
