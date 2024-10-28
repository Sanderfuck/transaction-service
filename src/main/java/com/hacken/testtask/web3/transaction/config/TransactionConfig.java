package com.hacken.testtask.web3.transaction.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;

@Configuration
public class TransactionConfig {
    @Value("${provider.node.url}")
    private String providerNodeUrl;

    @Bean
    public Web3j Web3jProtocol() {
        return Web3j.build(new HttpService(providerNodeUrl));
    }
}
