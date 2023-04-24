package com.keith.productservice.config;


import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class ProductConfig {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
