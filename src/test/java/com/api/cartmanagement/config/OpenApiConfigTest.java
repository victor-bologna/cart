package com.api.cartmanagement.config;

import io.swagger.v3.oas.models.OpenAPI;
import org.assertj.core.api.BDDAssertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.BDDAssertions.then;
import static org.springframework.test.util.ReflectionTestUtils.getField;
import static org.springframework.test.util.ReflectionTestUtils.setField;

@ExtendWith(MockitoExtension.class)
class OpenApiConfigTest {

    @InjectMocks
    private OpenApiConfig openApiConfig;

    @Test
    public void testOpenAPI() {
        String localhost = "localhost";
        int port = 8081;
        setField(openApiConfig, "host", localhost);
        setField(openApiConfig, "port", port);

        OpenAPI result = openApiConfig.api();

        BDDAssertions.then(result).isNotNull();

        then(getField(openApiConfig, "host")).isEqualTo(localhost);
        then(getField(openApiConfig, "port")).isEqualTo(port);
    }
}