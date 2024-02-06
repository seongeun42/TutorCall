package com.potato.TutorCall.exception.customException;

public class DuplicatedException extends RuntimeException {
  public DuplicatedException(String message) {
    super(message);
  }
}
