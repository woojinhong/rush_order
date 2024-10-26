package com.order.rush_order.order.controller.wishlist;

import com.order.rush_order.common.security.UserDetailsImpl;
import com.order.rush_order.order.dto.response.WishlistItemDto;
import com.order.rush_order.order.service.wishlist.WishlistService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/wishlist")
@RequiredArgsConstructor
public class WishlistController {

    private final WishlistService wishlistService;

    // 위시리스트에 상품 추가
    @PostMapping("/add/{productId}")
    public ResponseEntity<?> addProductToWishList(@PathVariable Long productId,
                                         @AuthenticationPrincipal UserDetailsImpl userDetails){

        wishlistService.addProductToWishlist(productId, userDetails.getUsername());
        return ResponseEntity.ok("상품이 위시리스트에 추가되었습니다.");
    }

    @GetMapping
    public ResponseEntity<List<WishlistItemDto>> getWishlists(@AuthenticationPrincipal UserDetailsImpl userDetails){

        return ResponseEntity.ok(wishlistService.getWishlists(userDetails.getUsername()));
    }

    // 위시리스트에서 상품 삭제
    @DeleteMapping("/remove/{productId}")
    public ResponseEntity<?> removeProductFromWishlist(
            @PathVariable Long productId,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {

        wishlistService.removeProductFromWishlist(productId, userDetails.getUsername());
        return ResponseEntity.ok("상품이 위시리스트에서 삭제되었습니다.");
    }

    // 위시리스트 전체 삭제
    @DeleteMapping("/clear")
    public ResponseEntity<?> clearWishlist(
            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        wishlistService.clearWishlist(userDetails.getUsername());
        return ResponseEntity.ok("위시리스트가 초기화되었습니다.");
    }
}
