package com.hacken.testtask.web3.transaction.actuator.metric;

import io.micrometer.core.instrument.Counter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TransactionMetrics {
    private final Counter transactionsProcessedCounter;
    private final Counter transactionErrorCounter;

    public void incrementTransactionsProcessed() {
        transactionsProcessedCounter.increment();
    }

    public void incrementTransactionErrors() {
        transactionErrorCounter.increment();
    }
}
