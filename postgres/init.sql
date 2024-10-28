CREATE DATABASE transactions_db;

DROP SCHEMA IF EXISTS transaction_service;

CREATE SCHEMA transaction_service;

CREATE TABLE transaction_service.transactions
(
    id                SERIAL PRIMARY KEY,
    hash              VARCHAR(66) UNIQUE,
    from_address      VARCHAR(42)    NOT NULL,
    to_address        VARCHAR(42),
    value             NUMERIC(38, 0) NOT NULL,
    gas               NUMERIC(38, 0) NOT NULL,
    block_number      BIGINT         NOT NULL,
    block_hash        VARCHAR(66),
    transaction_index VARCHAR(10),
    public_key        VARCHAR(130)
);

CREATE INDEX idx_block_number ON transaction_service.transactions (block_number);

CREATE TABLE transaction_service.sync_fetching_status
(
    id                   BIGINT PRIMARY KEY,
    last_processed_block NUMERIC(38, 0) NOT NULL
);