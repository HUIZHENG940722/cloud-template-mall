package com.ethan.mall.admin.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhenghui
 * @name MybatisConfig
 * @Description Mybatis配置
 * @Date 2022/3/12
 */
@Configuration
@MapperScan(basePackages = {"com.ethan.mall.admin.*.dao", "com.ethan.mall.dao"})
public class MybatisConfig {
}
