package com.order.rush_order.order.service.wishlist;

import com.order.rush_order.member.entity.Users;
import com.order.rush_order.member.service.UserService;
import com.order.rush_order.order.dto.response.WishlistItemDto;
import com.order.rush_order.order.entity.Wishlist;
import com.order.rush_order.order.mapper.WishlistMapper;
import com.order.rush_order.order.repository.wishlist.WishlistRepository;
import com.order.rush_order.product.entity.Product;
import com.order.rush_order.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WishlistService {
    private final WishlistRepository wishlistRepository;

    private final UserService userService;
    private final ProductService productService;


    public void addProductToWishlist(Long productId, String email) {
        // 유저, 상품 반환
        Users user = userService.getUserByEmail(email);
        Product product = productService.getProductById(productId);

        // 검증
        // 중복 체크 (이미 존재할 경우 예외 발생)
        if (wishlistRepository.existsByUserAndProduct(user, product)) {
            throw new IllegalStateException("이미 위시리스트에 추가된 상품입니다.");
        }

        Wishlist wishlist = WishlistMapper.toEntity(user, product);

        wishlistRepository.save(wishlist);
    }


    // 특정 유저 아이디 wishlist 아이템's 반환
    public List<WishlistItemDto> getWishlists(String email) {
        Users user = userService.getUserByEmail(email);

        // 특정 User의 Wishlistitems 가져오기
        return wishlistRepository.findWishlistItemsByUser(user);

    }

    //
    public void removeProductFromWishlist(Long productId, String email) {
        Users user = userService.getUserByEmail(email);

        // 검증
        // 사용자의 Wishlist에서 특정 Product를 조회
        Wishlist wishlistProduct = wishlistRepository.findByUserIdAndProductId(user.getId(), productId)
                .orElseThrow(() -> new IllegalArgumentException("해당 상품이 위시리스트에 없습니다."));

        // 위시리스트에서 항목 삭제
        wishlistRepository.delete(wishlistProduct);
    }

    // 위시리스트 전체 삭제
    public void clearWishlist(String email) {
        Users user = userService.getUserByEmail(email);

        wishlistRepository.deleteAllByUser(user);
    }
}
