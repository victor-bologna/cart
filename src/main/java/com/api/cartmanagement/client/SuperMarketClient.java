package com.api.cartmanagement.client;

import com.api.cartmanagement.domain.dto.SupermarketDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name = "${supermarket.name}", url = "${supermarket.url}")
public interface SuperMarketClient {

    @RequestMapping(method = RequestMethod.GET, value = "/supermarkets")
    List<SupermarketDto> getSupermarkets();
}
