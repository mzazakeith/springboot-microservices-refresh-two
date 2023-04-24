package com.keith.productservice.controller;

import com.keith.productservice.model.dto.ProductListResponse;
import com.keith.productservice.model.dto.ProductRequest;
import com.keith.productservice.model.dto.ProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/product")
public class ProductController {

    private final ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createProduct(@RequestBody ProductRequest productRequest){
        productService.createProduct(productRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ProductListResponse getAllProducts(){
        return productService.getAllProducts();
    }
}
