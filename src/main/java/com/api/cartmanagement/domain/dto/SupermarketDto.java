package com.api.cartmanagement.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class SupermarketDto {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("name")
    private String name;
}
