package com.order.rush_order.order.service.cart;

import com.order.rush_order.member.entity.Users;
import com.order.rush_order.member.service.UserService;
import com.order.rush_order.order.dto.request.CartQuantityDto;
import com.order.rush_order.order.dto.response.CartItemDto;
import com.order.rush_order.order.entity.Cart;
import com.order.rush_order.order.mapper.CartMapper;
import com.order.rush_order.order.repository.cart.CartRepository;
import com.order.rush_order.product.entity.Product;
import com.order.rush_order.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    private final UserService userService;
    private final ProductService productService;


    // 장바구니 조회
    public List<CartItemDto> getCartItems(String email) {
        Users user = userService.getUserByEmail(email);
        return cartRepository.findAllByUser(user);
    }

    public void addProductToCart(Long productId, String email, CartQuantityDto dto) {
        Users existUser = userService.getUserByEmail(email);
        Product existProduct = productService.getProductById(productId);

        // 검증
        if (cartRepository.existsByUserAndProduct(existUser, existProduct)) {
            throw new IllegalStateException("이미 장바구니에 추가된 상품입니다.");
        }

        // 장바구니 아이템 추가/ 수량, 제품 비용 계산
        Cart cartItem = Cart.toEntity(existUser, existProduct, dto.getQuantity());
        cartRepository.save(cartItem);
    }


    // 수량 변경 메서드 (증가/감소)
    @Transactional
    public CartItemDto updateProductQuantity(Long productId, String email, long quantityChange) {
        // 해당 상품 검증
        Cart cart = findCartItem(productId, email);

        // 수량 업데이트: 양수면 증가, 음수면 감소
        if (quantityChange > 0) {
            cart.addQuantity(quantityChange);
        } else if (quantityChange < 0) {
            cart.subtractQuantity(Math.abs(quantityChange));
        }

        // 수량이 0이면 삭제
        if (cart.getQuantity() == 0) {
            cartRepository.delete(cart);
            return null;  // 0이 된 경우 null 반환
        } else {
            cartRepository.save(cart);
            return CartMapper.toItemDto(cart);  // 변경된 데이터 반환
        }
    }

    // 장바구니 항목 삭제
    @Transactional
    public void removeProductFromCart(Long productId, String email) {
        Cart cart = findCartItem(productId, email);
        cartRepository.delete(cart);
    }

    // 사용자와 상품으로 장바구니 항목 조회
    private Cart findCartItem(Long productId, String email) {
        Users user = userService.getUserByEmail(email);
        Product product = productService.getProductById(productId);

        return cartRepository.findByUserAndProduct(user, product)
                .orElseThrow(() -> new IllegalArgumentException("장바구니에 해당 상품이 없습니다."));
    }



}
