package com.ethan.mall.gateway.filter;

import com.ethan.mall.gateway.config.IgnoreUrlsConfig;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.net.URI;
import java.util.List;

/**
 * @author zhenghui
 * @name IgnoreUrlsRemoveJwtFilter
 * @Description 白名单路径访问移除JWT请求头过滤器
 * @Date 2022/4/25
 */
@Component
public class IgnoreUrlsRemoveJwtFilter implements WebFilter {

    @Resource
    private IgnoreUrlsConfig ignoreUrlsConfig;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        URI uri = request.getURI();
        AntPathMatcher antPathMatcher = new AntPathMatcher();
        // 白名单路径移除JWT请求头
        List<String> ignoreUrls = ignoreUrlsConfig.getUrls();
        for (String ignoreUrl : ignoreUrls) {
            if (antPathMatcher.match(ignoreUrl, uri.getPath())) {
                exchange.getRequest().mutate().header("Authorization", "")
                        .build();
                exchange = exchange.mutate().request(request).build();
                return chain.filter(exchange);
            }
        }
        return chain.filter(exchange);
    }
}
