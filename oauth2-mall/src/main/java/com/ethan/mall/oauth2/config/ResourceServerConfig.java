package com.ethan.mall.oauth2.config;

import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

/**
 * @author zhenghui
 * @name ResourceServerConfig
 * @Description 资源服务器配置
 * @Date 2022/3/12
 */
/*@Configuration
@EnableResourceServer
public class ResourceServerConfig implements ResourceServerConfigurer {
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {

    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .requestMatchers(EndpointRequest.toAnyEndpoint()).permitAll()
                .anyRequest().authenticated();
    }
}*/
