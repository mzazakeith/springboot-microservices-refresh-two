package com.keith.productservice.controller;

import com.keith.productservice.model.dto.request.ProductListResponse;
import com.keith.productservice.model.dto.request.ProductRequest;
import com.keith.productservice.model.dto.request.ProductResponse;
import com.keith.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/product")
public class ProductController {

    private final ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponse createProduct(@RequestBody ProductRequest productRequest){
        return productService.createProduct(productRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ProductListResponse getAllProducts(){
        return productService.getAllProducts();
    }
}
