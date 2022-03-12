package com.ethan.mall.admin.ums.service;

import com.ethan.mall.pojo.UmsRole;

/**
 * @author zhenghui
 * @name RoleService
 * @Description 后台用户角色管理业务接口
 * @Date 2022/3/12
 */
public interface RoleService {
    /**
     * 创建后台用户角色
     * @param role
     * @return
     */
    UmsRole create(UmsRole role);
}
