package com.order.rush_order.product.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductSummaryResponseDto {
    private Long id;
    private String name;
    private float price;
}
