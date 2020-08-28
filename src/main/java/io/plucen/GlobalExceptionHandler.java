package io.plucen;

import javax.servlet.http.HttpServletRequest;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(AppException.class)
  public ResponseEntity<ErrorDTO> handleAppException(AppException appException) {
    return new ResponseEntity<>(
        new ErrorDTO(appException.getMessage()), appException.getHttpStatus());
  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(NoHandlerFoundException.class)
  public ErrorDTO handleNoHandlerFoundException(NoHandlerFoundException exception) {
    return new ErrorDTO(exception.getMessage());
  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
  public ErrorDTO handleHttpRequestMethodNotSupportedException(
      HttpRequestMethodNotSupportedException exception, HttpServletRequest request) {
    return new ErrorDTO("" + exception.getMessage() + " for " + request.getRequestURI());
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
