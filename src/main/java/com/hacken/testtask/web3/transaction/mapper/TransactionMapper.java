package com.hacken.testtask.web3.transaction.mapper;

import com.hacken.testtask.web3.transaction.model.TransactionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.web3j.protocol.core.methods.response.Transaction;

@Mapper(componentModel = "spring")
public interface TransactionMapper {

    @Mapping(source = "from", target = "fromAddress")
    @Mapping(source = "to", target = "toAddress")
    TransactionEntity toTransactionEntity(Transaction transaction);
}
