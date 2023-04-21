package com.keith.customerservice.service.impl;

import com.keith.customerservice.model.dto.CustomerRequest;
import com.keith.customerservice.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService {
    @Override
    public void registerCustomer(CustomerRequest customerRequest) {
        customerRepository.save(customerRequest);
        log.info("New customer registered:{}", customerRequest);
    }
}
