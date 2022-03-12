package com.ethan.mall.oauth2.service.feign;

import com.ethan.mall.common.domain.AuthenticationUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author zhenghui
 * @name AdminService
 * @Description 后台用户服务器客户端业务
 * @Date 2022/3/12
 */
@FeignClient(value = "admin-mall")
public interface AdminService {
    @GetMapping(value = "/admin/loadUserByUsername")
    AuthenticationUser loadUserByUsername(String username);
}
