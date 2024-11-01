package ru.evlitvin.wallet.controller.impl;

import lombok.AllArgsConstructor;
import ru.evlitvin.wallet.controller.WalletController;
import ru.evlitvin.wallet.dto.WalletTransactionRequest;
import ru.evlitvin.wallet.facade.WalletFacade;
import ru.evlitvin.wallet.service.WalletService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.UUID;

@RestController
@AllArgsConstructor
public class WalletControllerImpl implements WalletController {

  private WalletFacade walletFacade;
  private WalletService walletService;

  @Override
  public ResponseEntity<Void> processTransaction(WalletTransactionRequest request) {
    walletFacade.transactionWallet(request);
    return ResponseEntity.ok().build();
  }

  @Override
  public ResponseEntity<BigDecimal> getBalance(UUID walletId) {
    BigDecimal balance = walletService.getBalance(walletId);
    return ResponseEntity.ok(balance);
  }
}