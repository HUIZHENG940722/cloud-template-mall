package com.ethan.mall.oauth2.component;

import com.ethan.mall.oauth2.domain.AuthorizationUser;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhenghui
 * @name JwtTokenEnhancer
 * @Description JWT内容增强器
 * @Date 2022/4/16
 */
@Component
public class JwtTokenEnhancer implements TokenEnhancer {
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        AuthorizationUser authorizationUser = (AuthorizationUser) authentication.getPrincipal();
        Map<String, Object> info = new HashMap<>();
        info.put("id", authorizationUser.getAuthenticationUser().getId());
        info.put("client_id", authorizationUser.getAuthenticationUser().getClientId());
        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(info);
        return accessToken;
    }
}
