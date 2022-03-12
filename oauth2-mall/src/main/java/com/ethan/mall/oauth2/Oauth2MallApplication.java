package com.ethan.mall.oauth2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class Oauth2MallApplication {

    public static void main(String[] args) {
        SpringApplication.run(Oauth2MallApplication.class, args);
    }
}
