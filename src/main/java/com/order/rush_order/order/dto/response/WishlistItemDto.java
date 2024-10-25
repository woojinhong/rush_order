package com.order.rush_order.order.dto.response;


import com.order.rush_order.product.dto.ProductSummaryResponseDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@Getter
@NoArgsConstructor
public class WishlistItemDto extends ProductSummaryResponseDto {
    private long wishlistId;

    @Builder
    public WishlistItemDto(
            Long wishlistId,
            long productId,
            String name,
            BigDecimal price
    ) {
        super(productId, name, price);
        this.wishlistId = wishlistId;
    }
}
