package com.keith.productservice.service;

import com.keith.productservice.model.dto.request.ProductListResponse;
import com.keith.productservice.model.dto.request.ProductRequest;
import com.keith.productservice.model.dto.request.ProductResponse;

public interface ProductService {

    ProductResponse createProduct(ProductRequest productRequest);
}
