package com.order.rush_order.order.service.order;


import com.order.rush_order.member.entity.Users;
import com.order.rush_order.member.service.UserService;
import com.order.rush_order.order.entity.Cart;
import com.order.rush_order.order.entity.OrderItem;
import com.order.rush_order.order.entity.OrderStatus;
import com.order.rush_order.order.entity.Orders;
import com.order.rush_order.order.repository.cart.CartRepository;
import com.order.rush_order.order.repository.order.OrderRepository;
import com.order.rush_order.product.entity.Product;
import com.order.rush_order.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final CartRepository cartRepository;
    private final OrderRepository orderRepository;
    private final UserService userService;

    @Transactional
    public Orders createOrder(String email) {
        Users user = userService.getUserByEmail(email);

        // 사용자의 장바구니 항목 가져오기
        List<Cart> cartItems = cartRepository.findByUser(user);

        if (cartItems.isEmpty()) {
            throw new IllegalStateException("장바구니가 비어 있습니다.");
        }

        // Order 생성
        Orders order = Orders.toEntity(user);


        // OrderItems 생성 및 주문에 추가
        BigDecimal totalPrice = BigDecimal.ZERO;
        for (Cart cart : cartItems) {

            Product orderedProduct = cart.getProduct();
            BigDecimal productCartPrice = cart.getCartPrice();
            long productQuantity = cart.getQuantity();

            // 주문 상품 생성 및 저장
            OrderItem.toEntity(order, orderedProduct, productQuantity , productCartPrice);

            // 재고 감소 처리
            orderedProduct.decreaseStock(productQuantity);


            // 총 누적 가격
            totalPrice = totalPrice.add(productCartPrice);
        }

        // 총 가격 설정 및 저장
        order.setTotalPrice(totalPrice);
        orderRepository.save(order);

        // 장바구니 비우기
        cartRepository.deleteAll(cartItems);

        return order;
    }
}
