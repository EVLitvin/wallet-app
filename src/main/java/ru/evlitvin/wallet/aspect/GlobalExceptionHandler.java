package ru.evlitvin.wallet.aspect;

import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.evlitvin.wallet.exception.InvalidJsonException;

@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(HttpMessageNotReadableException.class)
  public void handleHttpMessageNotReadable(HttpMessageNotReadableException ex) {
    String errorMessage = "Invalid JSON: " + ex.getMessage();
    throw new InvalidJsonException(errorMessage);
  }

}

