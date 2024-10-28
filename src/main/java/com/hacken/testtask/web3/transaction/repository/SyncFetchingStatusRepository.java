package com.hacken.testtask.web3.transaction.repository;


import com.hacken.testtask.web3.transaction.model.SyncFetchingStatusEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SyncFetchingStatusRepository extends JpaRepository<SyncFetchingStatusEntity, Long> {
    Optional<SyncFetchingStatusEntity> findTopByOrderByIdDesc();
}
