package com.ethan.mall.admin.ums.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.crypto.digest.BCrypt;
import com.ethan.mall.admin.ums.dao.RoleDao;
import com.ethan.mall.admin.ums.param.AdminRegisterParam;
import com.ethan.mall.admin.ums.service.AdminService;
import com.ethan.mall.admin.ums.service.feign.AuthorizationService;
import com.ethan.mall.common.api.CommonData;
import com.ethan.mall.common.domain.AuthenticationUser;
import com.ethan.mall.common.domain.Oauth2Token;
import com.ethan.mall.common.exception.Asserts;
import com.ethan.mall.dao.UmsAdminMapper;
import com.ethan.mall.pojo.UmsAdmin;
import com.ethan.mall.pojo.UmsAdminExample;
import com.ethan.mall.pojo.UmsRole;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author zhenghui
 * @name AdminServiceImpl
 * @Description 后台用户管理业务类
 * @Date 2022/3/12
 */
@Service
public class AdminServiceImpl implements AdminService {
    @Resource
    private UmsAdminMapper adminMapper;
    @Resource
    private RoleDao roleDao;
    @Resource
    private AuthorizationService authorizationService;
    @Override
    public AuthenticationUser loadUserByUsername(String username) {
        // 1 校验
        // 2 执行逻辑
        // 2.1 获取后台用户
        UmsAdmin byUsername = getByUsername(username);
        if (byUsername == null) {
            return null;
        }
        // 2.2 根据后台用户id获取角色列表
        List<UmsRole> roleListByAdminId = roleDao.getRoleListByAdminId(byUsername.getId());
        List<String> roles = new ArrayList<>();
        roleListByAdminId.forEach(role -> roles.add(role.getName()));
        // 2.3 格式话数据
        AuthenticationUser authenticationUser = new AuthenticationUser();
        authenticationUser.setId(byUsername.getId());
        authenticationUser.setUsername(byUsername.getUsername());
        authenticationUser.setPassword(byUsername.getPassword());
        authenticationUser.setStatus(byUsername.getStatus());
        authenticationUser.setRoles(roles.size()==0? null:roles);
        // 3 返回结果
        return authenticationUser;
    }

    @Override
    public UmsAdmin getByUsername(String username) {
        // 1 校验
        // 2 执行逻辑
        UmsAdminExample adminExample = new UmsAdminExample();
        adminExample.createCriteria().andUsernameEqualTo(username);
        List<UmsAdmin> umsAdmins = adminMapper.selectByExample(adminExample);
        if (CollUtil.isNotEmpty(umsAdmins)) {
            return umsAdmins.get(0);
        }
        // 3 返回结果
        return null;
    }

    @Override
    public UmsAdmin register(AdminRegisterParam adminRegisterParam) {
        // 1 校验
        // 1.1 校验用户名是否存在
        UmsAdminExample adminExample = new UmsAdminExample();
        adminExample.createCriteria().andUsernameEqualTo(adminRegisterParam.getUsername());
        List<UmsAdmin> umsAdmins = adminMapper.selectByExample(adminExample);
        if (CollUtil.isNotEmpty(umsAdmins)) {
            Asserts.fail("用户名已存在");
        }
        // 2 执行逻辑
        // 2.1 初始化用户数据
        UmsAdmin admin = new UmsAdmin();
        BeanUtil.copyProperties(adminRegisterParam, admin);
        admin.setCreatedTime(new Date());
        // 2.2 插入用户数据
        int i = adminMapper.insertSelective(admin);
        // 3 返回结果
        if (i>0) {
            return admin;
        }
        return null;
    }

    @Override
    public CommonData<Oauth2Token> login(String username, String password) {
        // 1 校验
        // 2 执行逻辑
        // 2.1 初始化数据
        Map<String, String> params = new HashMap<>();
        params.put("client_id", "admin-password");
        params.put("client_secret", "123456");
        params.put("grant_type", "password");
        params.put("username", username);
        params.put("password", password);
        CommonData<Oauth2Token> accessToken = authorizationService.postAccessToken(params);
        if (accessToken.getCode().equals("200") && accessToken.getData()!=null) {
            // 更新登录时间
            // 插入日志
        }
        // 3 返回结果集
        return accessToken;
    }
}
