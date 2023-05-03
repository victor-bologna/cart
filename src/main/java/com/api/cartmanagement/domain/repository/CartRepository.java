package com.api.cartmanagement.domain.repository;

import com.api.cartmanagement.domain.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

    List<Cart> findBySupermarketID(Long supermarketID);
}
