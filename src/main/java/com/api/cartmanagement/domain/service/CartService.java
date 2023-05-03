package com.api.cartmanagement.domain.service;

import com.api.cartmanagement.domain.vo.CartRequestVo;
import com.api.cartmanagement.domain.vo.CartResponseVo;

import java.util.List;

public interface CartService {

    CartResponseVo createCart(CartRequestVo cartRequestVo);

    List<CartResponseVo> getCartBySupermarketID(Long supermarketID);

    List<CartResponseVo> getAllCarts();
}
