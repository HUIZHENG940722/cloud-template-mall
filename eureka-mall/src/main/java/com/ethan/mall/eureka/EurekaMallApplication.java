package com.ethan.mall.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekaMallApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaMallApplication.class, args);
    }

}
