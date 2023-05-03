package com.api.cartmanagement.controller;

import com.api.cartmanagement.domain.mapper.CartMapper;
import com.api.cartmanagement.domain.request.CartRequest;
import com.api.cartmanagement.domain.response.CartResponse;
import com.api.cartmanagement.domain.response.ClearCacheResponse;
import com.api.cartmanagement.domain.response.ErrorResponse;
import com.api.cartmanagement.domain.service.CartService;
import com.api.cartmanagement.domain.service.SupermarketService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping
@Tag(name = "Cart", description = "APIs for creating and reading carts with dates and their values.")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private SupermarketService supermarketService;

    @Autowired
    private CartMapper cartMapper;

    @PostMapping(path = "/cart",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Create Cart into the database, if there's no Cart ID in Supermarket Manager API, " +
            "then a CartNotFoundException is thrown.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Cart created successfully.", content = {
                    @Content(schema = @Schema(implementation = CartResponse.class))
            }),
            @ApiResponse(responseCode = "400", description = "Cart data missing.", content = {
                    @Content(schema = @Schema(implementation = ErrorResponse.class))
            }),
            @ApiResponse(responseCode = "404", description = "Cart ID not found.", content = {
                    @Content(schema = @Schema(implementation = ErrorResponse.class))
            }),
            @ApiResponse(responseCode = "500", description = "Internal Server Error.", content = {
                    @Content(schema = @Schema(implementation = ErrorResponse.class))
            })
    })
    public ResponseEntity<CartResponse> createCart(@RequestBody @Valid CartRequest cartRequest) {
        return new ResponseEntity(cartMapper.responseVoToResponse(cartService.createCart(
                cartMapper.requestToVo(cartRequest))), HttpStatus.CREATED);
    }

    @Operation(summary = "Retrieve a specific Cart from the database, if there's no Cart ID in the database, " +
            "then a CartNotFoundException is thrown.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Cart created successfully.", content = {
                    @Content(array = @ArraySchema(schema = @Schema(implementation = CartResponse.class)))
            }),
            @ApiResponse(responseCode = "404", description = "Cart ID not found.", content = {
                    @Content(schema = @Schema(implementation = ErrorResponse.class))
            }),
            @ApiResponse(responseCode = "500", description = "Internal Server Error.", content = {
                    @Content(schema = @Schema(implementation = ErrorResponse.class))
            })
    })
    @GetMapping(value = "/cart/{supermarketID}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CartResponse>> getByCartID(@Parameter(description = "Cart ID name", example = "1")
                                                          @PathVariable Long supermarketID) {
        List<CartResponse> cartResponse = cartMapper.responseListVoToResponseList(cartService.getCartBySupermarketID(supermarketID));
        return ResponseEntity.ok(cartResponse);
    }

    @Operation(summary = "Read all Carts from the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All Carts retrieved successfully.", content = {
                    @Content(array = @ArraySchema(schema = @Schema(implementation = CartResponse.class)))
            }),
            @ApiResponse(responseCode = "500", description = "Internal Server Error.", content = {
                    @Content(schema = @Schema(implementation = ErrorResponse.class))
            })
    })
    @GetMapping(value = "/allCarts",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CartResponse>> getAllCarts() {
        return ResponseEntity.ok(cartMapper.responseListVoToResponseList(cartService.getAllCarts()));
    }

    @Operation(summary = "Clear cache from the Supermarket database.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Cache cleared successfully.", content = {
                    @Content(schema = @Schema(implementation = ClearCacheResponse.class))
            }),
            @ApiResponse(responseCode = "500", description = "Internal Server Error.", content = {
                    @Content(schema = @Schema(implementation = ErrorResponse.class))
            })
    })
    @DeleteMapping(value = "/clearCache")
    public ResponseEntity<ClearCacheResponse> clearSupermarketCache() {
        supermarketService.clearCache();
        return ResponseEntity.ok(new ClearCacheResponse("Cache cleared successfully."));
    }
}
