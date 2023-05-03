package com.api.cartmanagement.domain.mapper;

import com.api.cartmanagement.domain.entity.Cart;
import com.api.cartmanagement.domain.request.CartRequest;
import com.api.cartmanagement.domain.response.CartResponse;
import com.api.cartmanagement.domain.vo.CartRequestVo;
import com.api.cartmanagement.domain.vo.CartResponseVo;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public interface CartMapper {

    CartRequestVo requestToVo(CartRequest cartRequest);

    CartResponseVo cartToResponseVo(Cart cart);

    CartResponse responseVoToResponse(CartResponseVo cartResponseVo);

    List<CartResponse> responseListVoToResponseList(List<CartResponseVo> cartResponseVoList);

    Cart requestVoToCart(CartRequestVo cartRequestVo);

    List<CartResponseVo> cartListToResponseListVo(List<Cart> cartList);
}
