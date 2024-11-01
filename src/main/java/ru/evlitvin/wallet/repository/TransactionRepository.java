package ru.evlitvin.wallet.repository;

import ru.evlitvin.wallet.domain.Transaction;
import ru.evlitvin.wallet.domain.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface TransactionRepository extends JpaRepository<Transaction, UUID> {

  List<Transaction> findByWallet(Wallet wallet);
}