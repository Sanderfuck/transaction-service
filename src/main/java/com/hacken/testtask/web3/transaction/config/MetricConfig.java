package com.hacken.testtask.web3.transaction.config;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MetricConfig {

    @Bean
    public Counter transactionsProcessedCounter(MeterRegistry meterRegistry) {
        return meterRegistry.counter("custom.transactions.processed");
    }

    @Bean
    public Counter transactionErrorCounter(MeterRegistry meterRegistry) {
        return meterRegistry.counter("custom.transactions.errors");
    }
}
