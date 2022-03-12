package com.ethan.mall.admin.ums.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.ethan.mall.admin.ums.service.RoleService;
import com.ethan.mall.common.exception.Asserts;
import com.ethan.mall.dao.UmsRoleMapper;
import com.ethan.mall.pojo.UmsAdminExample;
import com.ethan.mall.pojo.UmsRole;
import com.ethan.mall.pojo.UmsRoleExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author zhenghui
 * @name RoleServiceImpl
 * @Description 后台用户角色管理业务类
 * @Date 2022/3/12
 */
@Service
public class RoleServiceImpl implements RoleService {
    @Resource
    private UmsRoleMapper roleMapper;
    @Override
    public UmsRole create(UmsRole role) {
        // 1 校验
        // 1.1 校验角色名称是否存在
        UmsRoleExample roleExample = new UmsRoleExample();
        roleExample.createCriteria().andNameEqualTo(role.getName());
        List<UmsRole> umsRoles = roleMapper.selectByExample(roleExample);
        if (CollUtil.isNotEmpty(umsRoles)) {
            Asserts.fail("角色名称已存在");
        }
        // 2 执行逻辑
        // 2.1 初始化数据
        role.setCreatedTime(new Date());
        // 2.2 插入角色数据
        int i = roleMapper.insertSelective(role);
        // 3 返回结果集
        if (i > 0) {
            return role;
        }
        return null;
    }
}
