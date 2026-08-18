package com.taskarray.entity;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class IntegerArrayEntityTest {

  private static final int[] SOURCE_VALUES = {5, 3, 8, 1};
  private static final String EXPECTED_TEXT = "[5, 3, 8, 1]";

  @Test
  void shouldBuildEntityWithGivenValuesAndPreserveOrder() {
    IntegerArrayEntity.Builder builder = new IntegerArrayEntity.Builder();
    for (int value : SOURCE_VALUES) {
      builder.addValue(value);
    }

    IntegerArrayEntity entity = builder.build();

    assertArrayEquals(SOURCE_VALUES, entity.getElements());
  }

  @Test
  void shouldReturnSizeEqualToNumberOfAddedValues() {
    IntegerArrayEntity.Builder builder = new IntegerArrayEntity.Builder();
    for (int value : SOURCE_VALUES) {
      builder.addValue(value);
    }

    IntegerArrayEntity entity = builder.build();

    assertEquals(SOURCE_VALUES.length, entity.getSize());
  }

  @Test
  void shouldRenderElementsAsText() {
    IntegerArrayEntity.Builder builder = new IntegerArrayEntity.Builder();
    for (int value : SOURCE_VALUES) {
      builder.addValue(value);
    }
    IntegerArrayEntity entity = builder.build();

    String text = entity.elementsAsText();

    assertEquals(EXPECTED_TEXT, text);
  }

  @Test
  void shouldReturnDefensiveCopyOfElements() {
    IntegerArrayEntity.Builder builder = new IntegerArrayEntity.Builder();
    builder.addValue(1);
    IntegerArrayEntity entity = builder.build();
    int[] firstCall = entity.getElements();
    firstCall[0] = 999;

    int[] secondCall = entity.getElements();

    assertEquals(1, secondCall[0]);
  }
}
