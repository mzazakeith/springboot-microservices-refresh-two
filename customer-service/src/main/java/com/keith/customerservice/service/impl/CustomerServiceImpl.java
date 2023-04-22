package com.keith.customerservice.service.impl;

import com.keith.customerservice.helper.CustomerHelper;
import com.keith.customerservice.model.Customer;
import com.keith.customerservice.model.dto.request.CustomerRequest;
import com.keith.customerservice.model.dto.response.CustomerListResponse;
import com.keith.customerservice.model.dto.response.CustomerResponse;
import com.keith.customerservice.model.dto.response.FraudCheckResponse;
import com.keith.customerservice.repository.CustomerRepository;
import com.keith.customerservice.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Validated
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerHelper customerHelper;
    private final RestTemplate restTemplate;
    private final ModelMapper modelMapper;
    private final CustomerRepository customerRepository;

    @Override
    public CustomerResponse registerCustomer(CustomerRequest customerRequest) {
        log.info("In register customer method :{}", customerRequest);
        Customer customer = Customer.builder()
                .firstName(customerRequest.getFirstName())
                .lastName(customerRequest.getLastName())
                .email(customerRequest.getEmail())
                .build();
//        todo: check if email is valid
//        todo: check if email is taken
        Customer savedCustomer = customerHelper.saveCustomer(customer);
        //        todo: check if fraudster
        FraudCheckResponse fraudCheckResponse = restTemplate.getForObject(
                "http://FRAUD-SERVICE/api/v1/fraud-check?customer_id={customerId}",
                FraudCheckResponse.class,
                savedCustomer.getId()
        );
        assert fraudCheckResponse != null;
        if(fraudCheckResponse.getIsFraudster()){
            throw new IllegalStateException("This customer is a fraudster");
        }
//        todo: send notification
        return CustomerResponse
                .builder()
                .id(savedCustomer.getId())
                .firstName(savedCustomer.getFirstName())
                .lastName(savedCustomer.getLastName())
                .email(savedCustomer.getEmail())
                .createdAt(savedCustomer.getCreatedAt())
                .updatedAt(savedCustomer.getUpdatedAt())
                .build();
    }

    @Override
    public CustomerListResponse getAllCustomers(Pageable pageable) {
        log.info("In Customer service - Attempting to retrieve all customer");
        Page<Customer> customers = customerRepository.findAll(pageable);
        List<CustomerResponse> customerResponses = customers
                .getContent()
                .stream()
                .map(customer -> modelMapper.map(customer, CustomerResponse.class))
                .toList();

        CustomerListResponse customersFound =  CustomerListResponse.builder()
                .data(customerResponses
                        .stream()
                        .map(customerResponse -> modelMapper.map(customerResponse, CustomerResponse.class))
                        .collect(Collectors.toList()))
                .meta(CustomerListResponse.MetaData.builder()
                        .recordCount(customers.getTotalElements())
                        .currentPage(customers.getNumber())
                        .numberOfPages(customers.getTotalPages())
                        .limit(customers.getSize())
                        .build())
                .build();

        log.info("Customers Meta for this request {}", customersFound.getMeta());
        return customersFound;
    }
}
