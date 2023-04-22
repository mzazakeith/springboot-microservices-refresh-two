package com.keith.customerservice.controller;

import com.keith.customerservice.model.dto.CustomerRequest;
import com.keith.customerservice.model.dto.CustomerResponse;
import com.keith.customerservice.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/customer")
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<CustomerResponse> registerCustomer(@Valid @RequestBody CustomerRequest customerRequest){
        log.info("In register customer method payload:{}",customerRequest);
        return new ResponseEntity<CustomerResponse>(customerService.registerCustomer(customerRequest),HttpStatus.CREATED);
    }
}
