package ru.evlitvin.wallet.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.evlitvin.wallet.domain.Wallet;
import ru.evlitvin.wallet.dto.WalletDTO;

@Mapper
public interface WalletMapper {

  WalletMapper INSTANCE = Mappers.getMapper(WalletMapper.class);

  WalletDTO toDto(Wallet wallet);

  Wallet toEntity(WalletDTO walletDTO);
}
