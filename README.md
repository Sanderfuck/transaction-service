# Blockchain Transaction Service

## Description

This blockchain service allows connecting to an EVM node to fetch real-time transactions, store them in a PostgreSQL database, and search through stored transactions using a REST API.

## Features
1. Connect to an EVM node and retrieve real-time transactions.
2. Store transaction data in a PostgreSQL database.
3. REST API for accessing stored transactions and manage transactions fetching process.
4. **Healthcheck** and **Metrics** system for service monitoring (based on Spring Actuator).
5. API documentation via Swagger.

## Requirements

- Java 17
- Docker
- Maven
- Infura account or another EVM node for blockchain data access

## Setup

### 1. Clone the Repository

git clone <repository URL>

### 2. Start locally
 - Set your url in application.properties:
  provider.node.url={YOUR_NODE_URL}
- Set database host by env var **DB_HOST=localhost** or setup in application.properties file
  spring.datasource.url=jdbc:postgresql://localhost:5432/transactions_db?currentSchema=transaction_service
- Run this command in terminal:
```
docker-compose up postgres
```

- Run application using Intellij IDEA

### 3. Start application using docker
- Set your url in docker-compose file:
  PROVIDER_NODE_URL: {YOUR_NODE_URL}
- Run this command in terminal:
```
docker-compose up
```

### 4. Links for work with application
For start fetching transactions you need to trigger start endpoint in swagger ui
- Follow this link for open swagger and test API:
```
http://localhost:8095/swagger-ui/index.html
```

- For healthcheck database follow this link:
```
http://localhost:8095/actuator/health
```

- For checking transaction metrics follow this link:
```
http://localhost:8095/actuator/metrics
```
### 5. Additional 
- For convenience you can use PGAdmin 
```
localhost:5050
```
- Debug in container from IDEA you can connect to port 5005