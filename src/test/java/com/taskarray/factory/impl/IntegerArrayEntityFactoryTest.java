package com.taskarray.factory.impl;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.taskarray.entity.IntegerArrayEntity;
import com.taskarray.exception.InvalidArrayDataException;
import com.taskarray.validation.impl.IntegerTokenValidator;
import java.util.List;
import org.junit.jupiter.api.Test;

class IntegerArrayEntityFactoryTest {

  private static final List<String> VALID_TOKENS = List.of("3", "1", "2");
  private static final List<String> INVALID_TOKENS = List.of("3", "1y", "2");
  private static final int[] EXPECTED_VALUES = {3, 1, 2};
  private static final String ENTITY_NAME = "int-array-under-test";

  @Test
  void shouldCreateIntegerArrayEntityFromValidTokens() throws InvalidArrayDataException {
    IntegerArrayEntityFactory factory = new IntegerArrayEntityFactory(new IntegerTokenValidator());

    IntegerArrayEntity entity = (IntegerArrayEntity) factory.createArrayEntity(VALID_TOKENS, ENTITY_NAME);

    assertArrayEquals(EXPECTED_VALUES, entity.getElements());
  }

  @Test
  void shouldAssignGivenNameToCreatedEntity() throws InvalidArrayDataException {
    IntegerArrayEntityFactory factory = new IntegerArrayEntityFactory(new IntegerTokenValidator());

    IntegerArrayEntity entity = (IntegerArrayEntity) factory.createArrayEntity(VALID_TOKENS, ENTITY_NAME);

    assertEquals(ENTITY_NAME, entity.getName());
  }

  @Test
  void shouldThrowInvalidArrayDataExceptionForInvalidTokens() {
    IntegerArrayEntityFactory factory = new IntegerArrayEntityFactory(new IntegerTokenValidator());

    assertThrows(InvalidArrayDataException.class, () -> factory.createArrayEntity(INVALID_TOKENS, ENTITY_NAME));
  }
}
