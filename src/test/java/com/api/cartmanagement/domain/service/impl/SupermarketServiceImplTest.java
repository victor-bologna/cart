package com.api.cartmanagement.domain.service.impl;

import com.api.cartmanagement.client.SuperMarketClient;
import com.api.cartmanagement.domain.dto.SupermarketDto;
import com.api.cartmanagement.domain.mother.CartMother;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class SupermarketServiceImplTest {

    @InjectMocks
    private SupermarketServiceImpl supermarketService;

    @Mock
    private SuperMarketClient superMarketClient;

    @Mock
    private CacheManager cacheManager;

    @DisplayName("Retrieve supermarket Manager DTO list")
    @Test
    void givenNothingWhenGetSupermarketDtoListThenReturnSupermarketDtoList() {
        List<SupermarketDto> supermarketDtoList = Collections.singletonList(CartMother.getSupermarketDto());

        given(superMarketClient.getSupermarkets()).willReturn(supermarketDtoList);

        List<SupermarketDto> result = supermarketService.getSupermarketDtoList();

        then(result).usingRecursiveComparison().isEqualTo(supermarketDtoList);

        verify(superMarketClient).getSupermarkets();
    }

    @DisplayName("Clear Cache from supermarket DTO list")
    @Test
    void givenNothingWhenClearCacheThenReturnNothing() {
        Cache cache = mock(Cache.class);
        given(cacheManager.getCache("supermarket")).willReturn(cache);
        willDoNothing().given(cache).clear();

        supermarketService.clearCache();

        verify(cacheManager).getCache("supermarket");
        verify(cache).clear();
    }
}