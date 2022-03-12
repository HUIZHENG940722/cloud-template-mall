package com.ethan.mall.admin.ums.controller;

import com.ethan.mall.admin.ums.service.AdminService;
import com.ethan.mall.common.domain.AuthenticationUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author zhenghui
 * @name AdminController
 * @Description 后台用户管理控制器
 * @Date 2022/3/12
 */
@RestController
@RequestMapping(value = "/admin")
public class AdminController {
    @Resource
    private AdminService adminService;

    /**
     * 根据用户名获取认证用户信息
     * @param username
     * @return
     */
    @GetMapping(value = "/loadByUsername")
    public AuthenticationUser loadUserByUsername(String username) {
        AuthenticationUser authenticationUser = adminService.loadUserByUsername(username);
        return authenticationUser;
    }
}
