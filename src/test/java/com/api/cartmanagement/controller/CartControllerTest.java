package com.api.cartmanagement.controller;

import com.api.cartmanagement.domain.mapper.CartMapper;
import com.api.cartmanagement.domain.request.CartRequest;
import com.api.cartmanagement.domain.response.CartResponse;
import com.api.cartmanagement.domain.response.ErrorResponse;
import com.api.cartmanagement.domain.vo.CartRequestVo;
import com.api.cartmanagement.domain.vo.CartResponseVo;
import com.api.cartmanagement.domain.mother.ErrorResponseMother;
import com.api.cartmanagement.domain.mother.CartMother;
import com.api.cartmanagement.domain.service.CartService;
import com.api.cartmanagement.domain.service.SupermarketService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;

import static com.api.cartmanagement.domain.mother.CartMother.getClearCacheResponse;
import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CartController.class)
public class CartControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private SupermarketService supermarketService;

    @MockBean
    private CartService cartService;

    @MockBean
    private CartMapper cartMapper;

    @DisplayName("Controller Test to create Cart")
    @Test
    public void givenCartRequestWhenCreateCartThenReturn201Created() throws Exception {
        CartRequest cartRequest = CartMother.getCartRequest();
        CartRequestVo cartRequestVo = CartMother.getCartRequestVo();
        CartResponseVo cartResponseVo = CartMother.getCartResponseVo();
        CartResponse cartResponse = CartMother.getCartResponse();

        given(cartMapper.requestToVo(cartRequest)).willReturn(cartRequestVo);
        given(cartService.createCart(cartRequestVo)).willReturn(cartResponseVo);
        given(cartMapper.responseVoToResponse(cartResponseVo)).willReturn(cartResponse);

        MockHttpServletResponse response = mockMvc.perform(post("/cart")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(cartRequest)))
                .andExpect(status().isCreated())
                .andReturn().getResponse();

        CartResponse result = objectMapper.readValue(response.getContentAsString(), CartResponse.class);

        verify(cartMapper).requestToVo(cartRequest);
        verify(cartService).createCart(cartRequestVo);
        verify(cartMapper).responseVoToResponse(cartResponseVo);

        then(result).usingRecursiveComparison().isEqualTo(cartResponse);
    }

    @DisplayName("Controller Test to create cart with missing params")
    @Test
    public void givenMissingCartIdWhenCreateCartThenReturn400BadRequest() throws Exception {
        CartRequest cartRequest = CartMother.getCartRequest();
        cartRequest.setSupermarketID(null);

        MockHttpServletResponse response = mockMvc.perform(post("/cart")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(cartRequest)))
                .andExpect(status().isBadRequest())
                .andReturn().getResponse();

        ErrorResponse result = objectMapper.readValue(response.getContentAsString(), ErrorResponse.class);

        then(result.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.getReasonPhrase());
    }

    @DisplayName("Controller Test to get cart by ID")
    @Test
    public void givenIdWhenGetCartByIdThenReturn200Ok() throws Exception {
        Long cartID = 1L;
        List<CartResponseVo> cartResponseVo = Collections.singletonList(CartMother.getCartResponseVo());
        List<CartResponse> cartResponse = Collections.singletonList(CartMother.getCartResponse());

        when(cartService.getCartBySupermarketID(cartID)).thenReturn(cartResponseVo);
        when(cartMapper.responseListVoToResponseList(cartResponseVo)).thenReturn(cartResponse);

        MockHttpServletResponse response = mockMvc.perform(get("/cart/{cartID}", cartID))
                .andExpect(status().isOk())
                .andReturn().getResponse();

        List<CartResponse>  result = objectMapper.readValue(response.getContentAsByteArray(), new TypeReference<>() {
        });

        verify(cartService).getCartBySupermarketID(cartID);
        verify(cartMapper).responseListVoToResponseList(cartResponseVo);

        then(result).usingRecursiveComparison().isEqualTo(cartResponse);
    }

    @DisplayName("Controller Test to get cart ID with no param and return internal server error")
    @Test
    public void givenNoParamWhenGetCartByIdThenReturn500InternalServerError() throws Exception {
        ErrorResponse errorResponse = ErrorResponseMother.getRequestMethodNotSupportedResponse();

        MockHttpServletResponse response = mockMvc.perform(get("/cart/"))
                .andExpect(status().isInternalServerError())
                .andReturn().getResponse();

        ErrorResponse result = objectMapper.readValue(response.getContentAsString(), ErrorResponse.class);

        then(result.getStatus()).isEqualTo(errorResponse.getStatus());
        then(result.getMessage()).isEqualTo(errorResponse.getMessage());
    }

    @DisplayName("Controller Test to get all carts")
    @Test
    public void givenNothingWhenGetAllCartsThenReturn200Ok() throws Exception {
        List<CartResponseVo> cartResponseVos = Collections.singletonList(CartMother.getCartResponseVo());
        List<CartResponse> cartRespons = Collections.singletonList(CartMother.getCartResponse());

        given(cartService.getAllCarts()).willReturn(cartResponseVos);
        given(cartMapper.responseListVoToResponseList(cartResponseVos)).willReturn(cartRespons);

        MockHttpServletResponse response = mockMvc.perform(get("/allCarts"))
                .andExpect(status().isOk())
                .andReturn().getResponse();

        List<CartResponse> result = objectMapper.readValue(response.getContentAsByteArray(), new TypeReference<>() {
        });

        verify(cartService).getAllCarts();
        verify(cartMapper).responseListVoToResponseList(cartResponseVos);

        then(result).usingRecursiveComparison().isEqualTo(cartRespons);
    }

    @DisplayName("Controller Test to clear cart cache")
    @Test
    public void givenNothingWhenClearCacheThenReturn200Ok() throws Exception {
        willDoNothing().given(supermarketService).clearCache();

        MockHttpServletResponse response = mockMvc.perform(delete("/clearCache"))
                .andExpect(status().isOk())
                .andReturn().getResponse();

        verify(supermarketService).clearCache();

        then(response.getContentAsString()).isEqualTo(objectMapper.writeValueAsString(getClearCacheResponse()));
    }
}