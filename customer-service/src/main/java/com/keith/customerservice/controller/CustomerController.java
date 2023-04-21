package com.keith.customerservice.controller;

import com.keith.customerservice.model.dto.CustomerRequest;
import com.keith.customerservice.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/customer")
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void registerCustomer(@Valid @RequestBody CustomerRequest customerRequest){
        log.info("In register customer method payload:{}",customerRequest);
        customerService.registerCustomer(customerRequest);
    }
}
