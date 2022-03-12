package com.ethan.mall.admin.ums.service;

import com.ethan.mall.admin.ums.param.AdminRegisterParam;
import com.ethan.mall.common.api.CommonData;
import com.ethan.mall.common.domain.AuthenticationUser;
import com.ethan.mall.common.domain.Oauth2Token;
import com.ethan.mall.pojo.UmsAdmin;

/**
 * @author zhenghui
 * @name AdminService
 * @Description 后台用户管理业务接口
 * @Date 2022/3/12
 */
public interface AdminService {
    /**
     * 根据用户名获取认证用户
     * @param username
     * @return
     */
    AuthenticationUser loadUserByUsername(String username);

    /**
     * 根据用户名获取后台用户
     * @param username
     * @return
     */
    UmsAdmin getByUsername(String username);

    /**
     * 注册后台用户
     * @param adminRegisterParam
     * @return
     */
    UmsAdmin register(AdminRegisterParam adminRegisterParam);

    /**
     * 登录获取token
     * @param username
     * @param password
     * @return
     */
    CommonData<Oauth2Token> login(String username, String password);
}
