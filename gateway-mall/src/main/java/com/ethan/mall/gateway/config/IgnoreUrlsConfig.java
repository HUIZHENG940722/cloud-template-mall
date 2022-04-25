package com.ethan.mall.gateway.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * @author zhenghui
 * @name IgnoreUrlsConfig
 * @Description 网关白名单配置
 * @Date 2022/4/25
 */
@ConfigurationProperties(prefix = "secure.ignore")
@Data
public class IgnoreUrlsConfig {
    private List<String> urls;
}
