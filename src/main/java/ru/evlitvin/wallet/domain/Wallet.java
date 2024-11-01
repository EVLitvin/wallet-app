package ru.evlitvin.wallet.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "wallet")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Wallet {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Column(name = "wallet_id", updatable = false, nullable = false)
  private UUID walletId;

  @Column(name = "balance", nullable = false)
  private BigDecimal balance;

}
