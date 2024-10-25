package com.order.rush_order.product.service;

import com.order.rush_order.product.dto.ProductDetailResponseDto;
import com.order.rush_order.product.dto.ProductRequestDto;
import com.order.rush_order.product.dto.ProductSummaryResponseDto;
import com.order.rush_order.product.entity.Product;
import com.order.rush_order.product.mapper.ProductMapper;
import com.order.rush_order.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    // 상품 생성
    public void createOrUpdateProduct(ProductRequestDto requestDto) {
        String name = requestDto.getName();
        long stockQuantity = requestDto.getStock();

        // 비관적 락을 이용해 상품 조회
        Product product = productRepository.findByName(name)
                .orElseGet(() -> ProductMapper.toEntity(requestDto));

        // 재고 업데이트 또는 새 상품의 초기 재고 설정
        product.increaseStock(stockQuantity);

        // 상품 저장 (신규 생성 또는 업데이트)
        productRepository.save(product);
    }

    // 상품 목록 조회
    public List<ProductSummaryResponseDto> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(ProductMapper::toSummaryDto)
                .collect(Collectors.toList());
    }

    // 상품 상세 조회
    public ProductDetailResponseDto getProduct(Long productId) {
        Product product = getProductById(productId);
        return ProductMapper.toDetailDto(product);
    }


    public Product getProductById(Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("상품을 찾을 수 없습니다."));
    }


}
