package com.ethan.mall.oauth2.service.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author zhenghui
 * @name UserDetailsServiceImpl
 * @Description 授权业务类
 * @Date 2022/3/12
 */
@Service
public class AuthorizationServiceImpl implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}
