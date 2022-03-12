package com.ethan.mall.admin.ums.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.ethan.mall.admin.ums.dao.AdminRoleDao;
import com.ethan.mall.admin.ums.service.AdminService;
import com.ethan.mall.common.domain.AuthenticationUser;
import com.ethan.mall.dao.UmsAdminMapper;
import com.ethan.mall.pojo.UmsAdmin;
import com.ethan.mall.pojo.UmsAdminExample;
import com.ethan.mall.pojo.UmsRole;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

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
    private AdminRoleDao adminRoleDao;
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
        List<UmsRole> roleListByAdminId = adminRoleDao.getRoleListByAdminId(byUsername.getId());
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
}
