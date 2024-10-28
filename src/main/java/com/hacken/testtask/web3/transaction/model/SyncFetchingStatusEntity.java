package com.hacken.testtask.web3.transaction.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.math.BigInteger;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SyncFetchingStatusEntity {
    @Id
    private Long id;
    private BigInteger lastProcessedBlock;
}
