package com.api.cartmanagement.domain.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@Schema(description = "Cart Request")
public class CartRequest {

    @JsonProperty("supermarketID")
    @NotNull(message = "Supermarket ID must not be empty.")
    @Schema(description = "Supermarket ID", example = "1")
    private Long supermarketID;

    @JsonProperty("items")
    @Schema(description = "Items with values", example = "{\"Tomato\":10.10,\"Cake\":20.50}")
    private Map<String, Double> items;
}
