package com.taskarray.validation.impl;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class DoubleTokenValidatorTest {

  private static final String INTEGER_LOOKING_TOKEN = "77";
  private static final String DECIMAL_TOKEN = "6.5";
  private static final String DOUBLE_DOT_TOKEN = "6..5";
  private static final String LETTER_TOKEN = "x3.3";

  @Test
  void shouldAcceptIntegerLookingToken() {
    DoubleTokenValidator validator = new DoubleTokenValidator();

    boolean isValid = validator.isValid(INTEGER_LOOKING_TOKEN);

    assertTrue(isValid);
  }

  @Test
  void shouldAcceptDecimalToken() {
    DoubleTokenValidator validator = new DoubleTokenValidator();

    boolean isValid = validator.isValid(DECIMAL_TOKEN);

    assertTrue(isValid);
  }

  @Test
  void shouldRejectTokenWithDoubleDot() {
    DoubleTokenValidator validator = new DoubleTokenValidator();

    boolean isValid = validator.isValid(DOUBLE_DOT_TOKEN);

    assertFalse(isValid);
  }

  @Test
  void shouldRejectTokenContainingLetters() {
    DoubleTokenValidator validator = new DoubleTokenValidator();

    boolean isValid = validator.isValid(LETTER_TOKEN);

    assertFalse(isValid);
  }
}
