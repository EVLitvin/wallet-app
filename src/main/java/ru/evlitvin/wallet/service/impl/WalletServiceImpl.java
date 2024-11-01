package ru.evlitvin.wallet.service.impl;

import jakarta.transaction.Transactional;
import java.math.BigDecimal;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.evlitvin.wallet.domain.Wallet;
import ru.evlitvin.wallet.dto.WalletTransactionRequest;
import ru.evlitvin.wallet.enums.OperationType;
import ru.evlitvin.wallet.exception.InsufficientFundsException;
import ru.evlitvin.wallet.exception.WalletNotFoundException;
import ru.evlitvin.wallet.repository.WalletRepository;
import ru.evlitvin.wallet.service.WalletService;


@Service
@AllArgsConstructor
public class WalletServiceImpl implements WalletService {

  private WalletRepository walletRepository;

  @Override
  @Transactional
  public void processTransaction(WalletTransactionRequest request) {
    Wallet wallet = walletRepository.findById(request.getWalletId())
        .orElseThrow(() -> new WalletNotFoundException("Wallet not found"));

    if (request.getOperationType() == OperationType.WITHDRAW && wallet.getBalance().compareTo(request.getAmount()) < 0) {
      throw new InsufficientFundsException("Insufficient funds");
    }

    if (request.getOperationType() == OperationType.DEPOSIT)
      walletRepository.depositWallet(request.getWalletId(), request.getAmount());
    else
      walletRepository.withdrawWallet(request.getWalletId(), request.getAmount());
  }

  @Override
  public BigDecimal getBalance(UUID walletId) {
    return walletRepository.findById(walletId)
        .orElseThrow(() -> new WalletNotFoundException("Wallet not found"))
        .getBalance();
  }
}