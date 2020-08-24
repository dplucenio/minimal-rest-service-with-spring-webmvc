package io.plucen;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
  @ExceptionHandler(RuntimeException.class)
  @ResponseStatus(HttpStatus.ACCEPTED)
  public ResponseEntity<ErrorDTO> handleRuntimeException(RuntimeException exception) {
    return new ResponseEntity<>(
        new ErrorDTO(exception.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @Data
  @AllArgsConstructor
  @NoArgsConstructor
  private class ErrorDTO {
    private String message;
  }
}
