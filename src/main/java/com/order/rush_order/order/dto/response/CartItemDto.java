package com.order.rush_order.order.dto.response;

import com.order.rush_order.product.dto.ProductSummaryResponseDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor
public class CartItemDto extends ProductSummaryResponseDto{
    private long cartId;
    private long quantity;

    private BigDecimal cartPrice;

    @Builder
    public CartItemDto(
            Long cartId,
            long productId,
            String name,
            BigDecimal price,
            long quantity,
            BigDecimal cartPrice
    ) {
        super(productId, name, price);
        this.cartId = cartId;
        this.quantity = quantity;
        this.cartPrice = cartPrice;
    }
}
