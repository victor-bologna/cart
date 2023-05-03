package com.api.cartmanagement.domain.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Schema(description = "Clear Cache Response")
public class ClearCacheResponse {

    @JsonProperty("message")
    @Schema(description = "Message", example = "Cache cleared successfully.")
    private String message;
}
