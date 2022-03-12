package com.ethan.mall.admin.ums.dao;

import com.ethan.mall.pojo.UmsRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @name UmsAdminRoleDao
 * @Description 后台用户数据访问扩展
 * @author zhenghui
 * @Date 2022/3/12
 */
public interface RoleDao {
    List<UmsRole> getRoleListByAdminId(@Param(value = "adminId") Long adminId);
}
