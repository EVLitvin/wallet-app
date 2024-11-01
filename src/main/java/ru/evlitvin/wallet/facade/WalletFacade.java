package ru.evlitvin.wallet.facade;

import ru.evlitvin.wallet.dto.WalletTransactionRequest;

public interface WalletFacade {

  void transactionWallet(WalletTransactionRequest request);

}
