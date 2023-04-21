package com.keith.fraud.repository;

import com.keith.fraud.model.FraudCheckHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FraudCheckHistoryRepository extends JpaRepository<FraudCheckHistory, UUID> {

}
