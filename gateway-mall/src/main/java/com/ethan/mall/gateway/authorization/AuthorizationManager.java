package com.ethan.mall.gateway.authorization;

import com.ethan.mall.gateway.config.IgnoreUrlsConfig;
import org.springframework.http.HttpMethod;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.ReactiveAuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.server.authorization.AuthorizationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.net.URI;
import java.util.List;

/**
 * @author zhenghui
 * @name AuthorizationManager
 * @Description 鉴权管理器
 * @Date 2022/4/25
 */
@Component
public class AuthorizationManager implements ReactiveAuthorizationManager<AuthorizationContext> {

    @Resource
    private IgnoreUrlsConfig ignoreUrlsConfig;

    @Override
    public Mono<AuthorizationDecision> check(Mono<Authentication> authentication, AuthorizationContext object) {
        ServerHttpRequest request = object.getExchange().getRequest();
        URI uri = request.getURI();
        AntPathMatcher pathMatcher = new AntPathMatcher();
        // 白名单路径直接放行
        List<String> urls = ignoreUrlsConfig.getUrls();
        for (String url : urls) {
            if (pathMatcher.match(url, uri.getPath())) {
                return Mono.just(new AuthorizationDecision(true));
            }
        }
        // 对应跨域的预检请求直接放行
        if (request.getMethod().equals(HttpMethod.OPTIONS)) {
            return Mono.just(new AuthorizationDecision(true));
        }
        return Mono.just(new AuthorizationDecision(false));
    }
}
