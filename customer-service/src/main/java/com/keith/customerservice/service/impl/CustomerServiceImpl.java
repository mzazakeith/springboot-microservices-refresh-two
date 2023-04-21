package com.keith.customerservice.service.impl;

import com.keith.customerservice.model.Customer;
import com.keith.customerservice.model.dto.CustomerRequest;
import com.keith.customerservice.repository.CustomerRepository;
import com.keith.customerservice.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    @Override
    public void registerCustomer(CustomerRequest customerRequest) {
        Customer customer = Customer.builder()
                .firstName(customerRequest.getFirstName())
                .lastName(customerRequest.getLastName())
                .email(customerRequest.getEmail())
                .build();
        customerRepository.save(customer);
        log.info("New customer registered:{}", customerRequest);
    }
}
