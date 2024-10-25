package com.order.rush_order.order.controller.cart;

import com.order.rush_order.common.security.UserDetailsImpl;
import com.order.rush_order.order.dto.request.CartQuantityDto;
import com.order.rush_order.order.dto.response.CartItemDto;
import com.order.rush_order.order.service.cart.CartService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    // 특정 유저 장바구니 상품 조회
    @GetMapping()
    public List<CartItemDto> getAllCartItems(@AuthenticationPrincipal UserDetailsImpl userDetails){
        return cartService.getCartItems(userDetails.getUsername());
    }

    @PostMapping("/add/{productId}")
    public ResponseEntity<?> addProductToCart(
            @PathVariable Long productId,
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @RequestBody CartQuantityDto dto
    )
    {
        cartService.addProductToCart(productId, userDetails.getUsername(), dto);
        return ResponseEntity.ok("상품이 장바구니에 추가 되었습니다.");
    }

    // 수량 업데이트 (증가/감소)
    @PatchMapping("/update/{productId}")
    public ResponseEntity<CartItemDto> updateProductQuantity(
            @PathVariable Long productId,
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @RequestBody CartQuantityDto dto)
    {
        CartItemDto updatedCartItem = cartService.updateProductQuantity(
                productId, userDetails.getUsername(), dto.getQuantity());

        return ResponseEntity.ok(updatedCartItem);
    }

    // 추후에 softdelete 방식으로 변경
    @DeleteMapping("/remove/{productId}")
    public ResponseEntity<?> removeProductFromCart(
            @PathVariable Long productId,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {

        // 장바구니에서 항목 제거
        cartService.removeProductFromCart(productId, userDetails.getUsername());

        // 성공 메시지 반환
        return ResponseEntity.ok("장바구니에서 상품이 삭제되었습니다.");
    }
}
