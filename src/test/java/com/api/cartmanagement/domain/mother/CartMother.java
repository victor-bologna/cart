package com.api.cartmanagement.domain.mother;

import com.api.cartmanagement.domain.dto.SupermarketDto;
import com.api.cartmanagement.domain.entity.Cart;
import com.api.cartmanagement.domain.request.CartRequest;
import com.api.cartmanagement.domain.response.CartResponse;
import com.api.cartmanagement.domain.response.ClearCacheResponse;
import com.api.cartmanagement.domain.vo.CartRequestVo;
import com.api.cartmanagement.domain.vo.CartResponseVo;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CartMother {

    public static final String ID = "b6d62f6e-9931-4a03-92af-eeca7eb72ce0";
    public static final Long SUPERMARKET_ID = 1L;
    private static final String NAME = "Carrefour";

    public static Map<String, Double> getItems() {
        Map<String, Double> items = new HashMap<>();
        items.put("Tomato", 1.50);
        return items;
    }

    public static CartRequest getCartRequest() {
        CartRequest cartRequest = new CartRequest();
        cartRequest.setSupermarketID(SUPERMARKET_ID);
        cartRequest.setItems(getItems());
        return cartRequest;
    }

    public static CartRequestVo getCartRequestVo() {
        CartRequestVo cartRequestVo = new CartRequestVo();
        cartRequestVo.setSupermarketID(SUPERMARKET_ID);
        cartRequestVo.setItems(getItems());
        return cartRequestVo;
    }

    public static CartResponseVo getCartResponseVo() {
        CartResponseVo cartResponseVo = new CartResponseVo();
        cartResponseVo.setSupermarketID(SUPERMARKET_ID);
        cartResponseVo.setId(ID);
        cartResponseVo.setName(NAME);
        cartResponseVo.setItems(getItems());
        return cartResponseVo;
    }

    public static CartResponse getCartResponse() {
        CartResponse cartResponse = new CartResponse();
        cartResponse.setSupermarketID(SUPERMARKET_ID);
        cartResponse.setId(ID);
        cartResponse.setName(NAME);
        cartResponse.setItems(getItems());
        return cartResponse;
    }

    public static Cart getCart() {
        Cart cart = new Cart();
        cart.setId(ID);
        cart.setSupermarketID(SUPERMARKET_ID);
        cart.setName(NAME);
        cart.setItems(getItems());
        return cart;
    }

    public static SupermarketDto getSupermarketDto() {
        SupermarketDto supermarketDto = new SupermarketDto();
        supermarketDto.setId(SUPERMARKET_ID);
        supermarketDto.setName(NAME);
        return supermarketDto;
    }

    public static ClearCacheResponse getClearCacheResponse() {
        return new ClearCacheResponse("Cache cleared successfully.");
    }
}
