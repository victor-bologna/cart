package com.api.cartmanagement.domain.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
public class CartRequestVo {

    private Long supermarketID;
    private Map<String, Double> items;
}
