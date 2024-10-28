package com.hacken.testtask.web3.transaction.service;

import com.hacken.testtask.web3.transaction.actuator.metric.TransactionMetrics;
import com.hacken.testtask.web3.transaction.mapper.TransactionMapper;
import com.hacken.testtask.web3.transaction.model.SyncFetchingStatusEntity;
import com.hacken.testtask.web3.transaction.model.TransactionEntity;
import java.io.IOException;
import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthBlock;
import org.web3j.protocol.core.methods.response.Transaction;

@Slf4j
@Service
@RequiredArgsConstructor
public class FetchingTransactionsService {
    private final Web3j Web3jProtocol;
    private final TransactionService transactionService;
    private final TransactionMapper transactionMapper;
    private final SyncFetchingStatusService syncFetchingStatusService;
    private final TransactionMetrics transactionMetrics;

    private boolean isRunning = false;

    public void startFetching() {
        isRunning = true;
    }

    public void stopFetching() {
        isRunning = false;
    }

    public void resumeFetching() {
        isRunning = true;
    }

    @Scheduled(fixedDelay = 10000)
    public void fetchLatestTransactions() {
        if (!isRunning) {
            return;
        }

        BigInteger lastProcessedBlock = getLastProcessedBlock();

        try {
            BigInteger latestBlockNumber = Web3jProtocol.ethGetBlockByNumber(
                DefaultBlockParameterName.LATEST, true).send().getBlock().getNumber();

            BigInteger lastProcessedBlockNumber = lastProcessedBlock.add(BigInteger.ONE);

            if (lastProcessedBlockNumber.compareTo(latestBlockNumber) > 0) {

                EthBlock ethBlock = Web3jProtocol.ethGetBlockByNumber(
                    DefaultBlockParameter.valueOf(lastProcessedBlockNumber), true
                ).send();

                processTransactions(ethBlock);
                commitLastFetchedBlock(ethBlock);
            }
        } catch (IOException e) {
            log.error("Error! Fetch Latest Transactions failed", e);
            transactionMetrics.incrementTransactionErrors();
        }
    }

    private void processTransactions(EthBlock ethBlock) {
        List<EthBlock.TransactionResult> transactions = ethBlock.getBlock().getTransactions();

        List<TransactionEntity> transactionEntities = transactions.stream()
            .map(transactionResult -> (Transaction) transactionResult.get())
            .map(transactionMapper::toTransactionEntity)
            .collect(Collectors.toList());

        transactionService.saveAllTransaction(transactionEntities);
        log.info("Processed {} Transactions, From Block: {}",
            transactionEntities.size(), ethBlock.getBlock().getNumber());
    }

    private void commitLastFetchedBlock(EthBlock ethBlock) {
        BigInteger currentBlock = ethBlock.getBlock().getNumber();
        SyncFetchingStatusEntity currentStatusEntity = SyncFetchingStatusEntity
            .builder()
            .lastProcessedBlock(currentBlock)
            .build();
        syncFetchingStatusService.save(currentStatusEntity);
        log.info("Saved Fetched Last Processed Block: {}", currentBlock);
        transactionMetrics.incrementTransactionsProcessed();
    }

    private BigInteger getLastProcessedBlock() {
        return syncFetchingStatusService.getLastSyncFetchingStatusEntity()
            .map(entity -> {
                BigInteger lastProcessedBlock = entity.getLastProcessedBlock();
                log.info("Fetching Latest Transactions From Block {}", lastProcessedBlock);
                return lastProcessedBlock;
            })
            .orElseGet(() -> {
                log.debug("Not Found Last SyncFetchingStatusEntity");
                return BigInteger.ZERO;
            });
    }
}
