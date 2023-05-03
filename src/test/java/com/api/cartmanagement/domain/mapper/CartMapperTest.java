package com.api.cartmanagement.domain.mapper;

import com.api.cartmanagement.domain.entity.Cart;
import com.api.cartmanagement.domain.request.CartRequest;
import com.api.cartmanagement.domain.response.CartResponse;
import com.api.cartmanagement.domain.vo.CartRequestVo;
import com.api.cartmanagement.domain.vo.CartResponseVo;
import com.api.cartmanagement.domain.mother.CartMother;
import org.assertj.core.api.BDDAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.Collections;
import java.util.List;

class CartMapperTest {

    private final CartMapper cartMapper = Mappers.getMapper(CartMapper.class);

    @DisplayName("Mapper Test CartRequest to CartRequestVo")
    @Test
    void requestToRequestVo() {
        CartRequest cartRequest = CartMother.getCartRequest();
        CartRequestVo cartRequestVo = CartMother.getCartRequestVo();

        CartRequestVo result = cartMapper.requestToVo(cartRequest);

        BDDAssertions.then(result).usingRecursiveComparison().isEqualTo(cartRequestVo);
    }

    @DisplayName("Mapper Test Cart to CartResponseVo")
    @Test
    void cartToCartResponseVo() {
        Cart cart = CartMother.getCart();
        CartResponseVo cartResponseVo = CartMother.getCartResponseVo();

        CartResponseVo result = cartMapper.cartToResponseVo(cart);

        BDDAssertions.then(result).usingRecursiveComparison().isEqualTo(cartResponseVo);
    }

    @DisplayName("Mapper Test CartResponseVo to CartResponse")
    @Test
    void responseVoToResponse() {
        CartResponseVo cartResponseVo = CartMother.getCartResponseVo();
        CartResponse cartResponse = CartMother.getCartResponse();

        CartResponse result = cartMapper.responseVoToResponse(cartResponseVo);

        BDDAssertions.then(result).usingRecursiveComparison().isEqualTo(cartResponse);
    }

    @DisplayName("Mapper Test List of CartResponseVos to List of CartResponses")
    @Test
    void responseListVoToResponseList() {
        List<CartResponseVo> cartResponseVoList = Collections.singletonList(CartMother.getCartResponseVo());
        List<CartResponse> cartResponseList = Collections.singletonList(CartMother.getCartResponse());

        List<CartResponse> result = cartMapper.responseListVoToResponseList(cartResponseVoList);

        BDDAssertions.then(result).usingRecursiveComparison().isEqualTo(cartResponseList);
    }

    @DisplayName("Mapper Test CartRequestVo to Cart")
    @Test
    void requestVoToCart() {
        CartRequestVo cartRequestVo = CartMother.getCartRequestVo();
        Cart cart = CartMother.getCart();

        Cart result = cartMapper.requestVoToCart(cartRequestVo);

        BDDAssertions.then(result).usingRecursiveComparison().ignoringActualNullFields().isEqualTo(cart);
    }

    @DisplayName("Mapper Test List of Carts to List of CartResponseVos")
    @Test
    void cartListToResponseListVo() {
        List<Cart> cartList = Collections.singletonList(CartMother.getCart());
        List<CartResponseVo> cartResponseVoList = Collections.singletonList(CartMother.getCartResponseVo());

        List<CartResponseVo> result = cartMapper.cartListToResponseListVo(cartList);

        BDDAssertions.then(result).usingRecursiveComparison().isEqualTo(cartResponseVoList);
    }
}