package com.api.cartmanagement.domain.service.impl;

import com.api.cartmanagement.client.SuperMarketClient;
import com.api.cartmanagement.domain.dto.SupermarketDto;
import com.api.cartmanagement.domain.service.SupermarketService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class SupermarketServiceImpl implements SupermarketService {

    @Autowired
    private SuperMarketClient superMarketClient;

    @Autowired
    private CacheManager cacheManager;

    @Override
    @Cacheable("supermarket")
    public List<SupermarketDto> getSupermarketDtoList() {
        List<SupermarketDto> supermarketDtoList = superMarketClient.getSupermarkets();
        log.info("Supermarket list retrieved successfully: {}", supermarketDtoList);
        return supermarketDtoList;
    }

    @Override
    public void clearCache() {
        log.info("Clearing supermarket cache...");
        Objects.requireNonNull(cacheManager.getCache("supermarket")).clear();
        log.info("Cache cleared successfully!");
    }
}
