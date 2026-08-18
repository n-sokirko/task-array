package com.taskarray.factory;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.taskarray.entity.IntegerArrayEntity;
import com.taskarray.exception.InvalidArrayDataException;
import com.taskarray.validation.IntegerTokenValidator;
import java.util.List;
import org.junit.jupiter.api.Test;

class IntegerArrayEntityFactoryTest {

  private static final List<String> VALID_TOKENS = List.of("3", "1", "2");
  private static final List<String> INVALID_TOKENS = List.of("3", "1y", "2");
  private static final int[] EXPECTED_VALUES = {3, 1, 2};

  @Test
  void shouldCreateIntegerArrayEntityFromValidTokens() throws InvalidArrayDataException {
    IntegerArrayEntityFactory factory = new IntegerArrayEntityFactory(new IntegerTokenValidator());

    IntegerArrayEntity entity = (IntegerArrayEntity) factory.createArrayEntity(VALID_TOKENS);

    assertArrayEquals(EXPECTED_VALUES, entity.getElements());
  }

  @Test
  void shouldThrowInvalidArrayDataExceptionForInvalidTokens() {
    IntegerArrayEntityFactory factory = new IntegerArrayEntityFactory(new IntegerTokenValidator());

    assertThrows(InvalidArrayDataException.class, () -> factory.createArrayEntity(INVALID_TOKENS));
  }
}
