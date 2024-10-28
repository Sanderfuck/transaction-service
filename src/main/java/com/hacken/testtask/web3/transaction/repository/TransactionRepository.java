package com.hacken.testtask.web3.transaction.repository;

import com.hacken.testtask.web3.transaction.model.TransactionEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {
    List<TransactionEntity> findTransactionEntitiesByBlockNumber(Long blockNumber);
}
