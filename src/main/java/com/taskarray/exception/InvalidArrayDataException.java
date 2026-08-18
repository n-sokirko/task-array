package com.taskarray.exception;

public class InvalidArrayDataException extends Exception {

  private static final long serialVersionUID = 1L;

  public InvalidArrayDataException(String message) {
    super(message);
  }

  public InvalidArrayDataException(String message, Throwable cause) {
    super(message, cause);
  }
}
