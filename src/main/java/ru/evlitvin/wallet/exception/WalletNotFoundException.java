package ru.evlitvin.wallet.exception;

public class WalletNotFoundException extends RuntimeException {

  public WalletNotFoundException(String message) {
    super(message);
  }
}