package com.api.cartmanagement.domain.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Map;

@Data
@Entity
@Table(name = "cart")
public class Cart {

    @Id
    private String id;

    @Column(name = "supermarket_id")
    private Long supermarketID;

    private String name;

    @ElementCollection
    private Map<String, Double> items;
}
