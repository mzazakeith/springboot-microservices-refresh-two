package com.keith.customerservice.service;


import com.keith.customerservice.model.dto.CustomerRequest;
import com.keith.customerservice.model.dto.CustomerResponse;

public interface CustomerService {

     CustomerResponse registerCustomer(CustomerRequest customerRequest);
}
