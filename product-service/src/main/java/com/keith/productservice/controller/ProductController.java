package com.keith.productservice.controller;

import com.keith.productservice.model.dto.request.ProductListResponse;
import com.keith.productservice.model.dto.request.ProductRequest;
import com.keith.productservice.model.dto.request.ProductResponse;
import com.keith.productservice.service.ProductService;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    @GetMapping("list")
    @ResponseStatus(HttpStatus.OK)
    public ProductListResponse getAllProducts(
            @RequestParam(value = "page_number", required = false, defaultValue = "0") @Min(0) int page,
            @RequestParam(value = "page_size", required = false, defaultValue = "20") @Min(1) int limit,
            @RequestParam(value = "sort_by", required = false, defaultValue = "createdAt") String sortBy,
            @RequestParam(value = "sort_type", required = false, defaultValue = "desc") String sortType
    ){
        Sort.Direction direction = Sort.Direction.fromString(sortType);
        Sort.Order order = Sort.Order.by(sortBy).with(direction);
        Pageable pageable = PageRequest.of(page, limit, Sort.by(order));
        return productService.getAllProducts(pageable);
    }
}
