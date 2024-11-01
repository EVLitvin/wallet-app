package ru.evlitvin.wallet.service;

import java.math.BigDecimal;
import java.util.UUID;
import ru.evlitvin.wallet.enums.OperationType;

public interface TransactionService {

  void createTransaction(UUID walletId, BigDecimal amount, OperationType operationType);

}
