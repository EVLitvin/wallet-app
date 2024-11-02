package ru.evlitvin.wallet.facade;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.evlitvin.wallet.dto.WalletTransactionRequest;
import ru.evlitvin.wallet.enums.OperationType;
import ru.evlitvin.wallet.facade.impl.WalletFacadeImpl;
import ru.evlitvin.wallet.service.TransactionService;
import ru.evlitvin.wallet.service.WalletService;

import java.math.BigDecimal;
import java.util.UUID;

import static org.mockito.Mockito.*;

class WalletFacadeImplTest {

  @Mock
  private WalletService walletService;

  @Mock
  private TransactionService transactionService;

  @InjectMocks
  private WalletFacadeImpl walletFacade;

  private WalletTransactionRequest request;
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
    request = new WalletTransactionRequest(walletId, operationType, amount);
  }

  @AfterEach
  void tearDown() throws Exception {
    closeable.close();
  }

  @Test
  void testTransactionWallet() {
    walletFacade.transactionWallet(request);

    verify(walletService, times(1)).processTransaction(request);
    verify(transactionService, times(1)).createTransaction(walletId, amount, operationType);
  }
}