package com.keith.fraud.controller;

import com.keith.fraud.model.dto.FraudCheckResponse;
import com.keith.fraud.service.FraudCheckHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/fraud-check")
public class FraudCheckController {

    private final FraudCheckHistoryService fraudCheckHistoryService;

    @GetMapping
    public FraudCheckResponse checkIfCustomerIsFraudster(
            @RequestParam(value = "customer_id") UUID customerId){
        boolean customerIsFraudulent = fraudCheckHistoryService.isFraudulentCustomer(customerId);
        return FraudCheckResponse
                .builder()
                .isFraudster(customerIsFraudulent)
                .build();
    }
}
