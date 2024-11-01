package ru.evlitvin.wallet.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.evlitvin.wallet.domain.Transaction;
import ru.evlitvin.wallet.dto.TransactionDTO;

@Mapper
public interface TransactionMapper {

  TransactionMapper INSTANCE = Mappers.getMapper(TransactionMapper.class);

  @Mapping(source = "wallet.walletId", target = "walletId")
  TransactionDTO toDto(Transaction transaction);

  @Mapping(target = "wallet.walletId", source = "walletId")
  Transaction toEntity(TransactionDTO transactionDTO);
}
