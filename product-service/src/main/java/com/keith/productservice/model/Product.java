package com.keith.productservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "t_product")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Product {
    @Id
    @JsonProperty("id")
    @GeneratedValue(generator = "UUID")
    @Column(updatable = false, nullable = false)
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @Column(nullable = false)
    @JsonProperty("product_name")
    private String name;

    @Column(nullable = false)
    @JsonProperty("product_description")
    private String description;

    @Column(nullable = false)
    @JsonProperty("product_price")
    private BigDecimal price;
}
