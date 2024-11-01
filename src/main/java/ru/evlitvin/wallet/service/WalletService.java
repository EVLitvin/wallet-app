package ru.evlitvin.wallet.service;

import ru.evlitvin.wallet.dto.WalletTransactionRequest;
import java.math.BigDecimal;
import java.util.UUID;

public interface WalletService {

  void processTransaction(WalletTransactionRequest request);
  BigDecimal getBalance(UUID walletId);
}