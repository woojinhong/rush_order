package com.order.rush_order.product.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductSummaryResponseDto {
    private Long id;
    private String name;
    private BigDecimal price;
}
