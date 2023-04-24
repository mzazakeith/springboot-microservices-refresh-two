package com.keith.productservice.service.impl;

import com.keith.productservice.helper.ProductHelper;
import com.keith.productservice.model.Product;
import com.keith.productservice.model.dto.request.ProductListResponse;
import com.keith.productservice.model.dto.request.ProductRequest;
import com.keith.productservice.model.dto.request.ProductResponse;
import com.keith.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductHelper productHelper;

    @Override
    public ProductResponse createProduct(ProductRequest productRequest) {
        Product product = Product
                .builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .build();
        Product newProduct = productHelper.saveProduct(product);
        log.info("Product saved : {}", product);
        return ProductResponse.builder()
                .name(newProduct.getName())
                .description(newProduct.getDescription())
                .price(newProduct.getPrice())
                .createdAt(newProduct.getCreatedAt())
                .build();
    }
}
