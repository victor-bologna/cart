package com.api.cartmanagement.domain.service.impl;

import com.api.cartmanagement.domain.dto.SupermarketDto;
import com.api.cartmanagement.domain.entity.Cart;
import com.api.cartmanagement.domain.exception.CartNotFoundException;
import com.api.cartmanagement.domain.mapper.CartMapper;
import com.api.cartmanagement.domain.vo.CartRequestVo;
import com.api.cartmanagement.domain.vo.CartResponseVo;
import com.api.cartmanagement.domain.mother.CartMother;
import com.api.cartmanagement.domain.repository.CartRepository;
import com.api.cartmanagement.domain.service.SupermarketService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CartServiceImplTest {

    @InjectMocks
    private CartServiceImpl cartService;

    @Mock
    private SupermarketService supermarketService;

    @Mock
    private CartRepository cartRepository;

    @Mock
    private CartMapper cartMapper;

    @DisplayName("Service Test to create cart")
    @Test
    void givenCartRequestVoWhenCreateCartThenReturnCartResponseVo() {
        CartRequestVo cartRequestVo = CartMother.getCartRequestVo();
        List<SupermarketDto> supermarketDtoList = Collections.singletonList(CartMother.getSupermarketDto());
        Cart cart = CartMother.getCart();
        CartResponseVo cartResponseVo = CartMother.getCartResponseVo();

        given(supermarketService.getSupermarketDtoList()).willReturn(supermarketDtoList);
        given(cartMapper.requestVoToCart(cartRequestVo)).willReturn(cart);
        given(cartRepository.save(cart)).willReturn(cart);
        given(cartMapper.cartToResponseVo(cart)).willReturn(cartResponseVo);

        CartResponseVo result = cartService.createCart(cartRequestVo);

        then(result).usingRecursiveComparison().isEqualTo(cartResponseVo);

        verify(supermarketService).getSupermarketDtoList();
        verify(cartMapper).requestVoToCart(cartRequestVo);
        verify(cartRepository).save(cart);
        verify(cartMapper).cartToResponseVo(cart);
    }

    @DisplayName("Service Test to with null cart ID in create cart")
    @Test
    void givenNullCartIDWhenCreateCartThenThrowCartNotFoundException() {
        CartRequestVo cartRequestVo = CartMother.getCartRequestVo();
        cartRequestVo.setSupermarketID(null);
        List<SupermarketDto> supermarketDtoList = Collections.singletonList(CartMother.getSupermarketDto());

        given(supermarketService.getSupermarketDtoList()).willReturn(supermarketDtoList);

        CartNotFoundException cartNotFoundException =
                Assertions.assertThrows(CartNotFoundException.class, () -> cartService.createCart(cartRequestVo));

        then(cartNotFoundException.getMessage()).isEqualTo("Cart does not exist in Supermarket database.");

        verify(supermarketService).getSupermarketDtoList();
    }

    @DisplayName("Service Test to get cart by supermarket ID")
    @Test
    void givenIdWhenCartBySupermarketIDThenReturnCartResponseVo() {
        Long cartID = 1L;
        List<Cart> cartList = Collections.singletonList(CartMother.getCart());
        List<CartResponseVo> cartResponseVoList = Collections.singletonList(CartMother.getCartResponseVo());

        given(cartRepository.findBySupermarketID(cartID)).willReturn(cartList);
        given(cartMapper.cartListToResponseListVo(cartList)).willReturn(cartResponseVoList);

        List<CartResponseVo> result = cartService.getCartBySupermarketID(cartID);

        then(result).usingRecursiveComparison().isEqualTo(cartResponseVoList);

        verify(cartRepository).findBySupermarketID(cartID);
        verify(cartMapper).cartListToResponseListVo(cartList);
    }

    @DisplayName("Service Test to get cart by supermarket ID not finding in database")
    @Test
    void givenWrongIdWhenCartBySupermarketIDThenThrowCartNotFoundException() {
        Long cartID = 1L;

        given(cartRepository.findBySupermarketID(cartID)).willReturn(Collections.emptyList());

        CartNotFoundException cartNotFoundException =
                Assertions.assertThrows(CartNotFoundException.class, () -> cartService.getCartBySupermarketID(cartID));

        then(cartNotFoundException.getMessage()).isEqualTo("Cart not found in the database.");

        verify(cartRepository).findBySupermarketID(cartID);
    }

    @DisplayName("Service Test to get all Carts")
    @Test
    void givenNothingWhenGetAllCartThenReturnAllCarts() {
        List<Cart> carts = Collections.singletonList(CartMother.getCart());
        List<CartResponseVo> cartResponseVo = Collections.singletonList(CartMother.getCartResponseVo());

        given(cartRepository.findAll()).willReturn(carts);
        given(cartMapper.cartListToResponseListVo(carts)).willReturn(cartResponseVo);

        List<CartResponseVo> result = cartService.getAllCarts();

        then(result).usingRecursiveComparison().isEqualTo(cartResponseVo);

        verify(cartRepository).findAll();
        verify(cartMapper).cartListToResponseListVo(carts);
    }
}