package com.api.cartmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "com.api.cartmanagement.client")
@EnableCaching
public class CartManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(CartManagementApplication.class, args);
    }

}
