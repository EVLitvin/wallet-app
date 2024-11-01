package ru.evlitvin.wallet.service.impl;

import jakarta.transaction.Transactional;
import java.math.BigDecimal;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.evlitvin.wallet.domain.Transaction;
import ru.evlitvin.wallet.domain.Wallet;
import ru.evlitvin.wallet.enums.OperationType;
import ru.evlitvin.wallet.repository.TransactionRepository;
import ru.evlitvin.wallet.service.TransactionService;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

  private final TransactionRepository transactionRepository;

  @Override
  @Transactional
  public void createTransaction(UUID walletId, BigDecimal amount, OperationType operationType) {
    Transaction transaction = Transaction.builder()
        .wallet(Wallet.builder().walletId(walletId).build())
        .amount(amount)
        .operationType(operationType)
        .build();

    transactionRepository.save(transaction);
  }

}
