package com.api.cartmanagement.domain.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@Schema(description = "Cart Response")
public class CartResponse {

    @JsonProperty("id")
    @Schema(description = "ID", example = "1d236439-e3c0-405f-befb-8d085a95474a")
    private String id;

    @JsonProperty("supermarketID")
    @Schema(description = "Supermarket ID", example = "1")
    private Long supermarketID;

    @JsonProperty("name")
    @Schema(description = "Supermarket name", example = "Carrefour")
    private String name;

    @JsonProperty("items")
    @Schema(description = "Items with values", example = "{\"Tomato\":10.10,\"Cake\":20.50}")
    private Map<String, Double> items;
}
