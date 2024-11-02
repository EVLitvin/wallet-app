package ru.evlitvin.wallet.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.evlitvin.wallet.domain.Transaction;
import ru.evlitvin.wallet.enums.OperationType;
import ru.evlitvin.wallet.repository.TransactionRepository;
import ru.evlitvin.wallet.service.impl.TransactionServiceImpl;

import java.math.BigDecimal;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class TransactionServiceImplTest {

  @Mock
  private TransactionRepository transactionRepository;

  @InjectMocks
  private TransactionServiceImpl transactionService;

  private UUID walletId;
  private BigDecimal amount;
  private OperationType operationType;
  private AutoCloseable closeable;

  @BeforeEach
  void setUp() {
    closeable = MockitoAnnotations.openMocks(this);
    walletId = UUID.randomUUID();
    amount = BigDecimal.valueOf(1000);
    operationType = OperationType.DEPOSIT;
  }

  @AfterEach
  void tearDown() throws Exception {
    closeable.close();
  }

  @Test
  void testCreateTransaction() {
    when(transactionRepository.save(any(Transaction.class))).thenAnswer(invocation -> invocation.getArgument(0));

    transactionService.createTransaction(walletId, amount, operationType);

    verify(transactionRepository, times(1)).save(argThat(transaction ->
        transaction.getWallet().getWalletId().equals(walletId) &&
            transaction.getAmount().compareTo(amount) == 0 &&
            transaction.getOperationType() == operationType
    ));
  }
}
