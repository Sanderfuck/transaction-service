package com.hacken.testtask.web3.transaction.service;

import com.hacken.testtask.web3.transaction.model.SyncFetchingStatusEntity;
import com.hacken.testtask.web3.transaction.repository.SyncFetchingStatusRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SyncFetchingStatusService {
    private final SyncFetchingStatusRepository syncFetchingStatusRepository;

    public void save(SyncFetchingStatusEntity syncFetchingStatusEntity) {
        syncFetchingStatusRepository.save(syncFetchingStatusEntity);
    }

    public Optional<SyncFetchingStatusEntity> getLastSyncFetchingStatusEntity() {
        return syncFetchingStatusRepository.findTopByOrderByIdDesc();
    }
}
