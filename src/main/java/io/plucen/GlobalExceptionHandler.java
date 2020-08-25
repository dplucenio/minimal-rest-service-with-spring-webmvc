package io.plucen;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(AppException.class)
  public ResponseEntity<ErrorDTO> handleAppException(AppException appException) {
    return new ResponseEntity<>(
        new ErrorDTO(appException.getMessage()), appException.getHttpStatus());
  }

  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  @ExceptionHandler(Exception.class)
  public ErrorDTO handleInternalError(Exception exception) {
    return new ErrorDTO(exception.getMessage());
  }

  @Data
  private static class ErrorDTO {
    private final String message;
  }
}
