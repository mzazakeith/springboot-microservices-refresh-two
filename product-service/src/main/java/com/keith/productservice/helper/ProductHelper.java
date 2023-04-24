package com.keith.productservice.helper;

import com.keith.productservice.model.Product;
import com.keith.productservice.model.dto.request.ProductRequest;
import com.keith.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
@RequiredArgsConstructor
public class ProductHelper {

    private final ProductRepository productRepository;

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Throwable.class)
    public Product saveProduct(Product newProduct){
        return productRepository.saveAndFlush(newProduct);
    }
}
