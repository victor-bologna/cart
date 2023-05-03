package com.api.cartmanagement.domain.service;

import com.api.cartmanagement.domain.dto.SupermarketDto;

import java.util.List;

public interface SupermarketService {

    List<SupermarketDto> getSupermarketDtoList();

    void clearCache();
}
