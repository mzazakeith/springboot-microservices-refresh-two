package com.keith.fraud.service;

import java.util.UUID;

public interface FraudCheckHistoryService {

    boolean isFraudulentCustomer(UUID customerId);
}
