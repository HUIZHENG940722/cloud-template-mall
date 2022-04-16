package com.ethan.mall.admin.ums.service.feign;

import com.ethan.mall.common.api.CommonData;
import com.ethan.mall.common.domain.Oauth2Token;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * @author zhenghui
 * @name AuthorizationService
 * @Description 授权管理服务客户端
 * @Date 2022/3/12
 */
@FeignClient("oauth2-mall")
public interface AuthorizationService {
    @PostMapping(value = "/oauth/token")
    CommonData<Oauth2Token> postAccessToken(@RequestParam Map<String, String> params);
}
