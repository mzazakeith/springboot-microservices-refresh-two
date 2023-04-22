package com.keith.customerservice.controller;

import com.keith.customerservice.model.dto.request.CustomerRequest;
import com.keith.customerservice.model.dto.response.CustomerListResponse;
import com.keith.customerservice.model.dto.response.CustomerResponse;
import com.keith.customerservice.service.CustomerService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping
    public ResponseEntity<CustomerListResponse> getAllCustomers(
            @RequestParam(value = "page_number", required = false, defaultValue = "0") @Min(0) int page,
            @RequestParam(value = "page_size", required = false, defaultValue = "20") @Min(1) int limit,
            @RequestParam(value = "sort_by", required = false, defaultValue = "createdAt") String sortBy,
            @RequestParam(value = "sort_type", required = false, defaultValue = "desc") String sortType
    ){
        Sort.Direction direction = Sort.Direction.fromString(sortType);
        Sort.Order order = Sort.Order.by(sortBy).with(direction);
        Pageable pageable = PageRequest.of(page, limit, Sort.by(order));
        return ResponseEntity.ok(customerService.getAllCustomers(pageable));
    }
}
