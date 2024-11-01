package ru.evlitvin.wallet.controller;

import ru.evlitvin.wallet.dto.WalletTransactionRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.UUID;

@RequestMapping("/api/v1/wallet")
public interface WalletController {

  @Operation(summary = "Process a wallet transaction", description = "Processes a deposit or withdrawal for a specific wallet.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Transaction processed successfully"),
      @ApiResponse(responseCode = "400", description = "Invalid request", content = @Content(schema = @Schema(implementation = Error.class))),
      @ApiResponse(responseCode = "404", description = "Wallet not found", content = @Content(schema = @Schema(implementation = Error.class)))
  })
  @PostMapping
  ResponseEntity<Void> processTransaction(@RequestBody WalletTransactionRequest request);

  @Operation(summary = "Get wallet balance", description = "Retrieves the balance of a specific wallet by ID.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Balance retrieved successfully"),
      @ApiResponse(responseCode = "404", description = "Wallet not found", content = @Content(schema = @Schema(implementation = Error.class)))
  })
  @GetMapping("/{walletId}")
  ResponseEntity<BigDecimal> getBalance(@PathVariable UUID walletId);
}