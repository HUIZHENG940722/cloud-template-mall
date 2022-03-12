package com.ethan.mall.admin.ums.controller;

import com.ethan.mall.admin.ums.service.RoleService;
import com.ethan.mall.common.api.CommonData;
import com.ethan.mall.pojo.UmsRole;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author zhenghui
 * @name RoleController
 * @Description 后台用户角色管理控制器
 * @Date 2022/3/12
 */
@RestController
@RequestMapping(value = "/role")
public class RoleController {
    @Resource
    private RoleService roleService;

    /**
     * 添加后台用户角色
     * @param role
     * @return
     */
    @PostMapping(value = "/create")
    public CommonData<UmsRole> create(@RequestBody UmsRole role) {
        UmsRole createdRole = roleService.create(role);
        if (createdRole == null) {
            return CommonData.failed();
        }
        return CommonData.success(createdRole);
    }
}
