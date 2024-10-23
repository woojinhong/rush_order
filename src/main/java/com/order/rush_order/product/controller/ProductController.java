package com.order.rush_order.product.controller;

import com.order.rush_order.product.dto.ProductDetailResponseDto;
import com.order.rush_order.product.dto.ProductRequestDto;
import com.order.rush_order.product.dto.ProductSummaryResponseDto;
import com.order.rush_order.product.entity.Product;
import com.order.rush_order.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    //상품 생성 또는 업데이트 (관리자용)
    @PostMapping
    public ResponseEntity<?> createOrUpdateProduct(@RequestBody ProductRequestDto requestDto) {
        productService.createOrUpdateProduct(requestDto);
        return ResponseEntity.ok("상품 업데이트 완료");
    }

    // 전체 상품 목록 조회
    @GetMapping
    public ResponseEntity<List<ProductSummaryResponseDto>> getAllProducts() {
        List<ProductSummaryResponseDto> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    // 특정 상품의 상세 정보 조회
    @GetMapping("/{id}")
    public ResponseEntity<ProductDetailResponseDto> getProductById(@PathVariable Long id) {
        ProductDetailResponseDto productDetail = productService.getProductById(id);
        return ResponseEntity.ok(productDetail);
    }
}