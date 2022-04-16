package com.ethan.mall.oauth2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class, scanBasePackages = "com.ethan.mall")
@EnableFeignClients
@EnableDiscoveryClient
public class Oauth2MallApplication {

    public static void main(String[] args) {
        SpringApplication.run(Oauth2MallApplication.class, args);
    }
}
