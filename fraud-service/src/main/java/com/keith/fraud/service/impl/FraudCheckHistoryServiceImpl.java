package com.keith.fraud.service.impl;

import com.keith.fraud.helper.FraudCheckHelper;
import com.keith.fraud.model.FraudCheckHistory;
import com.keith.fraud.repository.FraudCheckHistoryRepository;
import com.keith.fraud.service.FraudCheckHistoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class FraudCheckHistoryServiceImpl implements FraudCheckHistoryService {

    private final FraudCheckHelper fraudCheckHelper;

    @Override
    public boolean isFraudulentCustomer(UUID customerId) {
        log.info("In isFraudulent method customer id:{}", customerId);
        FraudCheckHistory fraudCheckHistory = FraudCheckHistory
                .builder()
                .customerId(customerId)
                .isFraud(false)
                .build();
        fraudCheckHelper.saveFraudCheckHistory(fraudCheckHistory);
        return false;
    }
}
