package com.order.rush_order.product.mapper;

import com.order.rush_order.product.dto.ProductDetailResponseDto;
import com.order.rush_order.product.dto.ProductRequestDto;
import com.order.rush_order.product.dto.ProductSummaryResponseDto;
import com.order.rush_order.product.entity.Product;


public class ProductMapper {

    public static Product toEntity(ProductRequestDto dto){
        return Product.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .price(dto.getPrice())
                .build();
    }

    public static ProductSummaryResponseDto toSummaryDto(Product product) {
        return new ProductSummaryResponseDto(
                product.getId(),
                product.getName(),
                product.getPrice()
        );
    }
    public static ProductDetailResponseDto toDetailDto(Product product) {
        return new ProductDetailResponseDto(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getDescription(),
                product.getStock()
        );
    }
}
