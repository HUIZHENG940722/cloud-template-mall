package com.ethan.mall.oauth2.config;

import com.ethan.mall.oauth2.component.JwtTokenEnhancer;
import com.ethan.mall.oauth2.service.impl.AuthorizationServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhenghui
 * @name AuthorizationServerConfig
 * @Description 授权服务器配置
 * @Date 2022/3/9
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig implements AuthorizationServerConfigurer {
    @Resource
    private AuthorizationServiceImpl authorizationService;
    @Resource
    private AuthenticationManager authenticationManager;
    @Resource
    private PasswordEncoder passwordEncoder;
    @Resource
    private JwtTokenEnhancer jwtTokenEnhancer;
    /**
     * 配置授权服务器的安全性
     * @param security
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.allowFormAuthenticationForClients();
    }

    /**
     * 配置客户端详细信息
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient("admin-password")
                .secret(passwordEncoder.encode("123456"))
                .scopes("all")
                .authorizedGrantTypes("password", "refresh_token")
                .accessTokenValiditySeconds(3600)
                .refreshTokenValiditySeconds(86400);
    }

    /**
     * 配置授权服务器端点的非安全功能
     * @param endpoints
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        List<TokenEnhancer> delegates = new ArrayList<>();
        delegates.add(jwtTokenEnhancer);
        delegates.add(jwtAccessTokenConverter());
        tokenEnhancerChain.setTokenEnhancers(delegates);
        endpoints.authenticationManager(authenticationManager)
                .userDetailsService(authorizationService)
                .tokenEnhancer(tokenEnhancerChain);
    }

    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
        jwtAccessTokenConverter.setSigningKey("123456");
        return jwtAccessTokenConverter;
    }
}
