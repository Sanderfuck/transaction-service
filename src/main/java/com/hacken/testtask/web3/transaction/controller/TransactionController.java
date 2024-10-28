package com.hacken.testtask.web3.transaction.controller;

import com.hacken.testtask.web3.transaction.model.TransactionEntity;
import com.hacken.testtask.web3.transaction.service.FetchingTransactionsService;
import com.hacken.testtask.web3.transaction.service.TransactionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionService transactionService;
    private final FetchingTransactionsService fetchingTransactionsService;

    @Operation(summary = "Get transactions by block number")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<TransactionEntity> getTransactions(@Parameter(description = "Set Block Number Parameter")
                                                   Long blockNumber) {
        return transactionService.getTransactionsByBlockNumber(blockNumber);
    }

    @Operation(summary = "Start fetching transactions")
    @PostMapping("/start")
    public ResponseEntity<String> startFetching() {
        fetchingTransactionsService.startFetching();
        return ResponseEntity.ok("Transaction fetch started");
    }

    @Operation(summary = "Stop fetching transactions")
    @PostMapping("/stop")
    public ResponseEntity<String> stopFetching() {
        fetchingTransactionsService.stopFetching();
        return ResponseEntity.ok("Transaction fetch stopped");
    }

    @Operation(summary = "Resume fetching transactions")
    @PostMapping("/resume")
    public ResponseEntity<String> resumeFetching() {
        fetchingTransactionsService.resumeFetching();
        return ResponseEntity.ok("Transaction fetch resumed");
    }
}
