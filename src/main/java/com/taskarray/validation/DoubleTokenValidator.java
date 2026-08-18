package com.taskarray.validation;

import java.util.regex.Pattern;

public final class DoubleTokenValidator implements TokenValidator {

  private static final String DOUBLE_REGEX = "^-?\\d+(\\.\\d+)?$";
  private static final Pattern DOUBLE_PATTERN = Pattern.compile(DOUBLE_REGEX);

  @Override
  public boolean isValid(String token) {
    return DOUBLE_PATTERN.matcher(token).matches();
  }
}
