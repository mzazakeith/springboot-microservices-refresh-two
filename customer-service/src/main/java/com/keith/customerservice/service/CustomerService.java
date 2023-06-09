package com.keith.customerservice.service;


import com.keith.customerservice.model.dto.request.CustomerRequest;
import com.keith.customerservice.model.dto.response.CustomerListResponse;
import com.keith.customerservice.model.dto.response.CustomerResponse;
import org.springframework.data.domain.Pageable;

import java.util.UUID;


public interface CustomerService {

     CustomerResponse registerCustomer(CustomerRequest customerRequest);

     CustomerListResponse getAllCustomers(Pageable pageable);

     CustomerResponse getCustomerById(UUID customerId);
}
