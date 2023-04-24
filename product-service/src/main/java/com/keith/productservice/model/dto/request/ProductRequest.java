package com.keith.productservice.model.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {

    @JsonProperty("product_name")
    private String name;
    @JsonProperty("product_description")
    private String description;
    @JsonProperty("product_price")
    private BigDecimal price;
}
