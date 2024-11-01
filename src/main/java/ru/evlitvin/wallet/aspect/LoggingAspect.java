package ru.evlitvin.wallet.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import ru.evlitvin.wallet.dto.WalletTransactionRequest;

@Aspect
@Slf4j
@Component
public class LoggingAspect {

  @Before("execution(* ru.evlitvin.wallet.controller..*(..))")
  public void logControllerMethod(JoinPoint joinPoint) {
    String methodName = joinPoint.getSignature().getName();

    Object[] args = joinPoint.getArgs();
    if (args.length > 0) {
      for (Object arg : args) {
        if (arg instanceof WalletTransactionRequest wallet && methodName.equals("processTransaction")) {
          log.info(String.format("Input parameters for method %s: walletId: %s, operationType: %s, amount: %s",
              methodName, wallet.getWalletId(), wallet.getOperationType(), wallet.getAmount()));
        } else {
          log.info(String.format("Input parameters for method %s: walletId: %s", methodName, arg));
        }
      }
    } else {
      log.info("No input parameters for method {}", methodName);
    }
  }
}