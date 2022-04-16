package com.ethan.mall.oauth2.service.impl;

import com.ethan.mall.common.domain.AuthenticationUser;
import com.ethan.mall.oauth2.domain.AuthorizationUser;
import com.ethan.mall.oauth2.service.feign.AdminService;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

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
    @Resource
    private HttpServletRequest request;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AuthenticationUser authenticationUser = adminService.loadUserByUsername(username);
        String clientId = request.getParameter("client_id");
        if (authenticationUser == null) {
            throw new UsernameNotFoundException("用户名或密码错误!");
        }
        authenticationUser.setClientId(clientId);
        AuthorizationUser authorizationUser = new AuthorizationUser(authenticationUser);
        if (!authorizationUser.isEnabled()) {
            throw new DisabledException("该账户已被禁用，请联系管理员!");
        } else if (!authorizationUser.isAccountNonLocked()) {
            throw new LockedException("该账号已被锁定，请联系管理员!");
        } else if (!authorizationUser.isAccountNonExpired()) {
            throw new AccountExpiredException("该账号已过期，请联系管理员!");
        } else if (!authorizationUser.isCredentialsNonExpired()) {
            throw new CredentialsExpiredException("该账户的登录凭证已过期，请重新登录!");
        }
        return authorizationUser;
    }
}
