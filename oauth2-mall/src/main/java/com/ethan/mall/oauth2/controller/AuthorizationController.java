package com.ethan.mall.oauth2.controller;

import com.ethan.mall.common.api.CommonData;
import com.ethan.mall.common.domain.Oauth2Token;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import java.security.Principal;
import java.util.Map;

/**
 * @author zhenghui
 * @name AuthorizationController
 * @Description 授权服务控制器
 * @Date 2022/3/12
 */
@RestController
@RequestMapping(value = "/oauth")
public class AuthorizationController {
    @Resource
    private TokenEndpoint tokenEndpoint;

    @GetMapping(value = "/token")
    public CommonData<Oauth2Token> getAccessToken(@ApiIgnore Principal principal, @RequestParam(value = "params") Map<String, String> params)
            throws HttpRequestMethodNotSupportedException {
        OAuth2AccessToken body = tokenEndpoint.getAccessToken(principal, params).getBody();
        Oauth2Token oauth2Token = new Oauth2Token();
        oauth2Token.setTokenHead("Bearer ");
        oauth2Token.setToken(body.getValue());
        oauth2Token.setRefreshToken(body.getRefreshToken().getValue());
        oauth2Token.setExpiresIn(body.getExpiresIn());
        return CommonData.success(oauth2Token);
    }
}
