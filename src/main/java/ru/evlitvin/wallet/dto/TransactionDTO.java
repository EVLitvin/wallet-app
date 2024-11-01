package ru.evlitvin.wallet.dto;

import java.math.BigDecimal;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.evlitvin.wallet.enums.OperationType;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransactionDTO {

  private UUID transactionId;

  private UUID walletId;

  private BigDecimal amount;

  private OperationType operationType;
}
