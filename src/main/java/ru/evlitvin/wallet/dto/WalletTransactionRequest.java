package ru.evlitvin.wallet.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import ru.evlitvin.wallet.enums.OperationType;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@Builder
public class WalletTransactionRequest {

  private UUID walletId;

  private OperationType operationType;

  private BigDecimal amount;
}
