package ru.evlitvin.wallet.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import ru.evlitvin.wallet.controller.impl.WalletControllerImpl;
import ru.evlitvin.wallet.dto.WalletTransactionRequest;
import ru.evlitvin.wallet.enums.OperationType;
import ru.evlitvin.wallet.facade.WalletFacade;
import ru.evlitvin.wallet.service.WalletService;

import java.math.BigDecimal;
import java.util.UUID;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(WalletControllerImpl.class)
@ActiveProfiles("test")
class WalletControllerImplTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private WalletService walletService;

  @MockBean
  private WalletFacade walletFacade;

  private WalletTransactionRequest request;
  private UUID walletId;
  private BigDecimal expectedBalance;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    walletId = UUID.fromString("63d83fbd-c131-4f8b-8c75-a2ce4e89cbb7");
    expectedBalance = BigDecimal.valueOf(1000);
    request = new WalletTransactionRequest(walletId, OperationType.DEPOSIT, BigDecimal.valueOf(1000));

    doNothing().when(walletFacade).transactionWallet(request);
    when(walletService.getBalance(walletId)).thenReturn(expectedBalance);
  }

  @Test
  void testProcessTransaction() throws Exception {
    mockMvc.perform(post("/api/v1/wallet")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{ \"walletId\": \"" + request.getWalletId() + "\", \"operationType\": \"DEPOSIT\", \"amount\": 500 }"))
        .andExpect(status().isOk());
  }

  @Test
  void testGetBalance() throws Exception {
    when(walletService.getBalance(walletId)).thenReturn(expectedBalance);

    mockMvc.perform(get("/api/v1/wallet/" + walletId)
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().string(expectedBalance.toString()));
  }
}
