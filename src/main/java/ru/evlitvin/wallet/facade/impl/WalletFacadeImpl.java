package ru.evlitvin.wallet.facade.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.evlitvin.wallet.dto.WalletTransactionRequest;
import ru.evlitvin.wallet.facade.WalletFacade;
import ru.evlitvin.wallet.service.TransactionService;
import ru.evlitvin.wallet.service.WalletService;

@Service
@RequiredArgsConstructor
public class WalletFacadeImpl implements WalletFacade {

  private final WalletService walletService;
  private final TransactionService transactionService;

  @Override
  @Transactional
  public void transactionWallet(WalletTransactionRequest request) {
      walletService.processTransaction(request);
      transactionService.createTransaction(request.getWalletId(), request.getAmount(), request.getOperationType());
  }

}
