package com.api.cartmanagement.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.BDDAssertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.text.SimpleDateFormat;

@ExtendWith(MockitoExtension.class)
class ObjectMapperConfigTest {

    @InjectMocks
    private ObjectMapperConfig objectMapperConfig;

    @Test
    void testObjectMapperConfig() {
        ObjectMapper objectMapper = objectMapperConfig.getObjectMapper();

        BDDAssertions.then(objectMapper.getDateFormat().getNumberFormat()).isEqualTo(
                new SimpleDateFormat("yyyy-MM-dd").getNumberFormat());
    }
}