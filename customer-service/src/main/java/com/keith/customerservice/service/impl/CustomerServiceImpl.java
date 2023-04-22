package com.keith.customerservice.service.impl;

import com.keith.customerservice.helper.CustomerHelper;
import com.keith.customerservice.model.Customer;
import com.keith.customerservice.model.dto.CustomerRequest;
import com.keith.customerservice.model.dto.CustomerResponse;
import com.keith.customerservice.model.dto.FraudCheckResponse;
import com.keith.customerservice.repository.CustomerRepository;
import com.keith.customerservice.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Slf4j
@Service
@Validated
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerHelper customerHelper;
    private final RestTemplate restTemplate;
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
}
