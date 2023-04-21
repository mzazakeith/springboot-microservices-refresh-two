package com.keith.fraud.helper;

import com.keith.fraud.model.FraudCheckHistory;
import com.keith.fraud.repository.FraudCheckHistoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
@RequiredArgsConstructor
public class FraudCheckHelper {

    private final FraudCheckHistoryRepository fraudCheckHistoryRepository;

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Throwable.class)
    public void saveFraudCheckHistory(FraudCheckHistory fraudCheckHistory){
        fraudCheckHistoryRepository.save(fraudCheckHistory);
        log.info("Fraud Check History saved : {}", fraudCheckHistory);
    }
}
