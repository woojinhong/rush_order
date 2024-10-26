package com.order.rush_order.order.controller.order;

import com.order.rush_order.common.security.UserDetailsImpl;
import com.order.rush_order.order.entity.Orders;
import com.order.rush_order.order.service.order.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    // 주문 생성 API
    @PostMapping("/create")
    public ResponseEntity<?> createOrder(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        orderService.createOrder(userDetails.getUsername());
        return ResponseEntity.ok("주문이 완료되었습니다.");
    }
}
