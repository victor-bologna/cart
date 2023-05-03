package com.api.cartmanagement.domain.service.impl;

import com.api.cartmanagement.domain.dto.SupermarketDto;
import com.api.cartmanagement.domain.entity.Cart;
import com.api.cartmanagement.domain.exception.CartNotFoundException;
import com.api.cartmanagement.domain.mapper.CartMapper;
import com.api.cartmanagement.domain.repository.CartRepository;
import com.api.cartmanagement.domain.service.CartService;
import com.api.cartmanagement.domain.service.SupermarketService;
import com.api.cartmanagement.domain.vo.CartRequestVo;
import com.api.cartmanagement.domain.vo.CartResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CartServiceImpl implements CartService {

    @Autowired
    CartRepository cartRepository;

    @Autowired
    private CartMapper cartMapper;

    @Autowired
    private SupermarketService supermarketService;

    /**
     * Create cart in cache and only sends when new supermarket is added.
     *
     * @param cartRequestVo Cart Data to be inserted.
     * @throws CartNotFoundException if cart does not exist in Supermarket manager database.
     */
    @Override
    public CartResponseVo createCart(CartRequestVo cartRequestVo) {
        String supermarketName = checkSupermarketExists(cartRequestVo);
        if (Objects.isNull(supermarketName)) {
            throwCartNotFoundException("Cart does not exist in Supermarket database.");
        }
        return persistDataInCartManager(cartRequestVo, supermarketName);
    }

    /**
     * Get Cart by ID from database.
     *
     * @param supermarketID to be retrieved.
     * @return Cart information data.
     * @throws CartNotFoundException if cart not found then return error message to user.
     */
    @Override
    public List<CartResponseVo> getCartBySupermarketID(Long supermarketID) {
        List<Cart> cartIDResult = cartRepository.findBySupermarketID(supermarketID);
        if (cartIDResult.size() == 0) {
            throwCartNotFoundException("Cart not found in the database.");
        }
        log.info("Cart retrieved successfully: {}", cartIDResult);
        return cartMapper.cartListToResponseListVo(cartIDResult);
    }

    /**
     * Get all carts from database.
     *
     * @return all carts information data.
     */
    @Override
    public List<CartResponseVo> getAllCarts() {
        return cartMapper.cartListToResponseListVo(new ArrayList<>(cartRepository.findAll()));
    }

    private String checkSupermarketExists(CartRequestVo cartRequestVo) {
        List<SupermarketDto> supermarketDtoList = supermarketService.getSupermarketDtoList()
                .stream()
                .filter(supermarketDto -> supermarketDto.getId().equals(cartRequestVo.getSupermarketID()))
                .collect(Collectors.toList());
        if (supermarketDtoList.size() > 0) {
            return supermarketDtoList.get(0).getName();
        }
        return null;
    }


    private CartResponseVo persistDataInCartManager(CartRequestVo cartRequestVo, String supermarketName) {
        Cart cart = cartMapper.requestVoToCart(cartRequestVo);
        cart.setId(UUID.randomUUID().toString());
        cart.setName(supermarketName);

        log.info("Saving cart: {}", cart);
        cartRepository.save(cart);
        log.info("Cart saved successfully.");
        return cartMapper.cartToResponseVo(cart);
    }

    private void throwCartNotFoundException(String message) {
        log.error(message);
        throw new CartNotFoundException(message);
    }
}
