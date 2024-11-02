package ru.evlitvin.wallet.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.evlitvin.wallet.domain.Wallet;
import ru.evlitvin.wallet.dto.WalletTransactionRequest;
import ru.evlitvin.wallet.enums.OperationType;
import ru.evlitvin.wallet.exception.InsufficientFundsException;
import ru.evlitvin.wallet.exception.WalletNotFoundException;
import ru.evlitvin.wallet.repository.WalletRepository;
import ru.evlitvin.wallet.service.impl.WalletServiceImpl;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class WalletServiceImplTest {

  @Mock
  private WalletRepository walletRepository;

  @InjectMocks
  private WalletServiceImpl walletService;

  private UUID walletId;
  private Wallet wallet;
  private AutoCloseable closeable;

  @BeforeEach
  void setUp() {
    closeable = MockitoAnnotations.openMocks(this);
    walletId = UUID.randomUUID();
    wallet = new Wallet();
    wallet.setWalletId(walletId);
    wallet.setBalance(BigDecimal.valueOf(5000));
  }

  @AfterEach
  void tearDown() throws Exception {
    closeable.close();
  }

  @Test
  void testProcessTransaction_Deposit_Success() {
    WalletTransactionRequest request = new WalletTransactionRequest(walletId, OperationType.DEPOSIT, BigDecimal.valueOf(1000));
    when(walletRepository.findById(walletId)).thenReturn(Optional.of(wallet));

    walletService.processTransaction(request);

    verify(walletRepository, times(1)).depositWallet(walletId, BigDecimal.valueOf(1000));
  }

  @Test
  void testProcessTransaction_Withdraw_Success() {
    WalletTransactionRequest request = new WalletTransactionRequest(walletId, OperationType.WITHDRAW, BigDecimal.valueOf(1000));
    when(walletRepository.findById(walletId)).thenReturn(Optional.of(wallet));

    walletService.processTransaction(request);

    verify(walletRepository, times(1)).withdrawWallet(walletId, BigDecimal.valueOf(1000));
  }

  @Test
  void testProcessTransaction_Withdraw_InsufficientFunds() {

    WalletTransactionRequest request = new WalletTransactionRequest(walletId, OperationType.WITHDRAW, BigDecimal.valueOf(6000));
    when(walletRepository.findById(walletId)).thenReturn(Optional.of(wallet));

    assertThrows(InsufficientFundsException.class, () -> walletService.processTransaction(request));
    verify(walletRepository, never()).withdrawWallet(any(UUID.class), any(BigDecimal.class));
  }

  @Test
  void testProcessTransaction_WalletNotFound() {
    WalletTransactionRequest request = new WalletTransactionRequest(walletId, OperationType.DEPOSIT, BigDecimal.valueOf(1000));
    when(walletRepository.findById(walletId)).thenReturn(Optional.empty());

    assertThrows(WalletNotFoundException.class, () -> walletService.processTransaction(request));
    verify(walletRepository, never()).depositWallet(any(UUID.class), any(BigDecimal.class));
  }

  @Test
  void testGetBalance_Success() {
    when(walletRepository.findById(walletId)).thenReturn(Optional.of(wallet));

    BigDecimal balance = walletService.getBalance(walletId);

    assertEquals(BigDecimal.valueOf(5000), balance);
  }

  @Test
  void testGetBalance_WalletNotFound() {
    // Arrange
    when(walletRepository.findById(walletId)).thenReturn(Optional.empty());

    // Act & Assert
    assertThrows(WalletNotFoundException.class, () -> walletService.getBalance(walletId));
  }
}

