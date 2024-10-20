package com.order.rush_order.order.entity;

public enum OrderStatus {

    ORDERED,           // 주문 완료
    PAYMENT_PENDING,   // 결제 대기
    PAID,              // 결제 완료
    PROCESSING,        // 처리 중
    PACKAGED,          // 포장 완료
    SHIPPED,           // 배송 중
    IN_TRANSIT,        // 이동 중
    OUT_FOR_DELIVERY,  // 배달 중
    DELIVERED,         // 배송 완료
    CANCELED,          // 주문 취소
    REFUND_REQUESTED,  // 환불 요청
    REFUNDED,          // 환불 완료
    FAILED             // 실패
}
