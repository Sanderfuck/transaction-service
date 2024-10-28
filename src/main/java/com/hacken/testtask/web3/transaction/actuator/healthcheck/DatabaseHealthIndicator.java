package com.hacken.testtask.web3.transaction.actuator.healthcheck;

import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class DatabaseHealthIndicator implements HealthIndicator {
    private final DataSource dataSource;

    @Override
    public Health health() {
        return checkDatabaseConnection() ?
            Health.up().withDetail("Database", "Available").build() :
            Health.down().withDetail("Database", "Unavailable").build();
    }

    public boolean checkDatabaseConnection() {
        try (Connection connection = dataSource.getConnection()) {
            return connection.isValid(1);
        } catch (SQLException e) {
            log.error("Check database connection error", e);
            return false;
        }
    }
}
