package com.api.cartmanagement.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;

@Configuration
public class OpenApiConfig {

    @Value("${server.url}")
    private String host;

    @Value("${server.port}")
    private int port;

    @Bean
    public OpenAPI api() {
        return new OpenAPI()
                .components(new Components())
                .info(new Info().title("Cart Management API")
                        .description("RESTful API documentation for adding and reading cart items from MySQL database while" +
                                " validating carts from another api called Supermarket Management (Mockoon).")
                        .contact(new Contact().email("victorbologna@hotmail.com"))
                        .version("1.0.0"))
                .servers(Collections.singletonList(new Server().url("http://" + host + ":" + port)
                        .description("Supermarket Management API")));
    }
}
