package com.hacken.testtask.web3.transaction.service;

import com.hacken.testtask.web3.transaction.model.TransactionEntity;
import com.hacken.testtask.web3.transaction.repository.TransactionRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransactionService {
    private final TransactionRepository transactionRepository;

    public void saveTransaction(TransactionEntity transactionEntity) {
        transactionRepository.save(transactionEntity);
    }

    public void saveAllTransaction(List<TransactionEntity> transactionEntities) {
        transactionRepository.saveAll(transactionEntities);
    }

    public List<TransactionEntity> getTransactionsByBlockNumber(Long blockNumber) {
        return transactionRepository.findTransactionEntitiesByBlockNumber(blockNumber);
    }
}
