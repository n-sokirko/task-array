package com.taskarray.validation.impl;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class IntegerTokenValidatorTest {

  private static final String VALID_TOKEN = "42";
  private static final String NEGATIVE_TOKEN = "-17";
  private static final String LETTER_TOKEN = "1y1";
  private static final String DECIMAL_TOKEN = "6.5";

  @Test
  void shouldAcceptPlainIntegerToken() {
    IntegerTokenValidator validator = new IntegerTokenValidator();

    boolean isValid = validator.isValid(VALID_TOKEN);

    assertTrue(isValid);
  }

  @Test
  void shouldAcceptNegativeIntegerToken() {
    IntegerTokenValidator validator = new IntegerTokenValidator();

    boolean isValid = validator.isValid(NEGATIVE_TOKEN);

    assertTrue(isValid);
  }

  @Test
  void shouldRejectTokenContainingLetters() {
    IntegerTokenValidator validator = new IntegerTokenValidator();

    boolean isValid = validator.isValid(LETTER_TOKEN);

    assertFalse(isValid);
  }

  @Test
  void shouldRejectDecimalToken() {
    IntegerTokenValidator validator = new IntegerTokenValidator();

    boolean isValid = validator.isValid(DECIMAL_TOKEN);

    assertFalse(isValid);
  }
}
