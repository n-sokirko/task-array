package com.taskarray.validation;

import java.util.regex.Pattern;

public final class IntegerTokenValidator implements TokenValidator {

  private static final String INTEGER_REGEX = "^-?\\d+$";
  private static final Pattern INTEGER_PATTERN = Pattern.compile(INTEGER_REGEX);

  @Override
  public boolean isValid(String token) {
    return INTEGER_PATTERN.matcher(token).matches();
  }
}
