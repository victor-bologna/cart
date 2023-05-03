package com.api.cartmanagement.domain.repository;

import com.api.cartmanagement.domain.entity.Cart;
import com.api.cartmanagement.domain.mother.CartMother;
import org.assertj.core.api.BDDAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CartRepositoryTest {

    @Autowired
    private CartRepository cartRepository;

    @DisplayName("Repository Test to get cart from H2 in memory database")
    @Test
    public void givenCartsWhenLoadThenGetCart() {
        Long cartID = 1L;

        List<Cart> result = cartRepository.findBySupermarketID(1L);

        BDDAssertions.then(result).isNotNull();
    }

    @DisplayName("Repository Test to save cart to H2 in memory database")
    @Test
    public void givenCartsWhenSaveThenGetCart() {
        Cart cart = CartMother.getCart();

        Cart save = cartRepository.save(cart);

        BDDAssertions.then(save).usingRecursiveComparison().isEqualTo(cart);
    }

    @DisplayName("Repository Test to get All carts")
    @Test
    public void givenNothingWhenGetAllCartsThenGetListOfCarts() {
        List<Cart> all = cartRepository.findAll();

        BDDAssertions.then(all).isNotEmpty();
    }
}