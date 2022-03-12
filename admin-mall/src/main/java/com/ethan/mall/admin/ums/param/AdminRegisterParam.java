package com.ethan.mall.admin.ums.param;

import lombok.Data;

/**
 * @author zhenghui
 * @name AdminRegisterParam
 * @Description 后台用户注册参数
 * @Date 2022/3/12
 */
@Data
public class AdminRegisterParam {
    private String username;
    private String password;
    private String email;
    private String nickName;
    private String note;
}
