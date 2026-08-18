package com.taskarray.entity;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class DoubleArrayEntityTest {

  private static final double[] SOURCE_VALUES = {5.5, 3.3, 8.8};
  private static final double COMPARISON_DELTA = 0.0001;
  private static final String EXPECTED_TEXT = "[5.5, 3.3, 8.8]";

  @Test
  void shouldBuildEntityWithGivenValuesAndPreserveOrder() {
    DoubleArrayEntity.Builder builder = new DoubleArrayEntity.Builder();
    for (double value : SOURCE_VALUES) {
      builder.addValue(value);
    }

    DoubleArrayEntity entity = builder.build();

    assertArrayEquals(SOURCE_VALUES, entity.getElements(), COMPARISON_DELTA);
  }

  @Test
  void shouldReturnSizeEqualToNumberOfAddedValues() {
    DoubleArrayEntity.Builder builder = new DoubleArrayEntity.Builder();
    for (double value : SOURCE_VALUES) {
      builder.addValue(value);
    }

    DoubleArrayEntity entity = builder.build();

    assertEquals(SOURCE_VALUES.length, entity.getSize());
  }

  @Test
  void shouldRenderElementsAsText() {
    DoubleArrayEntity.Builder builder = new DoubleArrayEntity.Builder();
    for (double value : SOURCE_VALUES) {
      builder.addValue(value);
    }
    DoubleArrayEntity entity = builder.build();

    String text = entity.elementsAsText();

    assertEquals(EXPECTED_TEXT, text);
  }
}
