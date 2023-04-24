package com.keith.productservice.service.impl;

import com.keith.productservice.helper.ProductHelper;
import com.keith.productservice.model.Product;
import com.keith.productservice.model.dto.request.ProductListResponse;
import com.keith.productservice.model.dto.request.ProductRequest;
import com.keith.productservice.model.dto.request.ProductResponse;
import com.keith.productservice.repository.ProductRepository;
import com.keith.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductHelper productHelper;
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

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

    @Override
    public ProductListResponse getAllProducts(Pageable pageable) {
        log.info("In Products service - Attempting to retrieve all products");
        Page<Product> products = productRepository.findAll(pageable);
        List<ProductResponse> customerResponses = products
                .getContent()
                .stream()
                .map(customer -> modelMapper.map(customer, ProductResponse.class))
                .toList();

        ProductListResponse productsFound =  ProductListResponse.builder()
                .data(customerResponses
                        .stream()
                        .map(customerResponse -> modelMapper.map(customerResponse, ProductResponse.class))
                        .collect(Collectors.toList()))
                .meta(ProductListResponse.MetaData.builder()
                        .recordCount(products.getTotalElements())
                        .currentPage(products.getNumber())
                        .numberOfPages(products.getTotalPages())
                        .limit(products.getSize())
                        .build())
                .build();

        log.info("Products Meta for this request {}", productsFound.getMeta());
        return productsFound;
    }
}
