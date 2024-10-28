#
# Build stage
#
FROM maven:3.8.5-openjdk-17-slim AS build
WORKDIR /transaction-service
COPY ./pom.xml /transaction-service
COPY ./src /transaction-service/src
RUN mvn clean package -DskipTests
#
# Run stage
#
FROM openjdk:17-alpine3.14
COPY --from=build transaction-service/target/transaction-service.jar transaction-service/target/transaction-service.jar
ENTRYPOINT ["java", "-jar","transaction-service/target/transaction-service.jar"]