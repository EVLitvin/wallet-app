package ru.evlitvin.wallet.repository;

import jakarta.transaction.Transactional;
import java.math.BigDecimal;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import ru.evlitvin.wallet.domain.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface WalletRepository extends JpaRepository<Wallet, UUID> {

  @Transactional
  @Modifying
  @Query(value = "update wallet set balance = wallet.balance + ?2 where wallet_id = ?1", nativeQuery = true)
  void depositWallet(UUID walletId, BigDecimal amount);

  @Transactional
  @Modifying
  @Query(value = "update wallet set balance = wallet.balance - ?2 where wallet_id = ?1", nativeQuery = true)
  void withdrawWallet(UUID walletId, BigDecimal amount);

}
