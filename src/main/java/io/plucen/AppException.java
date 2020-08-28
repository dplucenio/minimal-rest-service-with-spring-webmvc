package io.plucen;

import lombok.Getter;
import org.springframework.http.HttpStatus;

public class AppException extends Exception {
  @Getter private final HttpStatus httpStatus;

  public AppException(String message, HttpStatus httpStatus) {
    super(message);
    this.httpStatus = httpStatus;
  }

  @Override
  public String toString() {
    return super.toString() + ", " + httpStatus;
  }
}
