package com.keith.customerservice.helper;

import com.keith.customerservice.model.Customer;
import com.keith.customerservice.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
@RequiredArgsConstructor
public class CustomerHelper {

    private final CustomerRepository customerRepository;

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Throwable.class)
    public void saveCustomer(Customer customer){
        customerRepository.save(customer);
        log.info("Customer saved : {}", customer);
    }
}
