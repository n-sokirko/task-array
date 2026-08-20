package com.taskarray.factory.impl;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.taskarray.entity.DoubleArrayEntity;
import com.taskarray.exception.InvalidArrayDataException;
import com.taskarray.validation.impl.DoubleTokenValidator;
import java.util.List;
import org.junit.jupiter.api.Test;

class DoubleArrayEntityFactoryTest {

  private static final List<String> VALID_TOKENS = List.of("3.5", "1.1", "2.0");
  private static final List<String> INVALID_TOKENS = List.of("3.5", "6..5", "2.0");
  private static final double[] EXPECTED_VALUES = {3.5, 1.1, 2.0};
  private static final double COMPARISON_DELTA = 0.0001;
  private static final String ENTITY_NAME = "double-array-under-test";

  @Test
  void shouldCreateDoubleArrayEntityFromValidTokens() throws InvalidArrayDataException {
    DoubleArrayEntityFactory factory = new DoubleArrayEntityFactory(new DoubleTokenValidator());

    DoubleArrayEntity entity = (DoubleArrayEntity) factory.createArrayEntity(VALID_TOKENS, ENTITY_NAME);

    assertArrayEquals(EXPECTED_VALUES, entity.getElements(), COMPARISON_DELTA);
  }

  @Test
  void shouldAssignGivenNameToCreatedEntity() throws InvalidArrayDataException {
    DoubleArrayEntityFactory factory = new DoubleArrayEntityFactory(new DoubleTokenValidator());

    DoubleArrayEntity entity = (DoubleArrayEntity) factory.createArrayEntity(VALID_TOKENS, ENTITY_NAME);

    assertEquals(ENTITY_NAME, entity.getName());
  }

  @Test
  void shouldThrowInvalidArrayDataExceptionForInvalidTokens() {
    DoubleArrayEntityFactory factory = new DoubleArrayEntityFactory(new DoubleTokenValidator());

    assertThrows(InvalidArrayDataException.class, () -> factory.createArrayEntity(INVALID_TOKENS, ENTITY_NAME));
  }
}
