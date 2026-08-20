package com.taskarray.entity;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.taskarray.observer.ArrayChangeObserver;
import org.junit.jupiter.api.Test;

class DoubleArrayEntityTest {

  private static final double[] SOURCE_VALUES = {5.5, 3.3, 8.8};
  private static final String ENTITY_NAME = "double-array-under-test";
  private static final double COMPARISON_DELTA = 0.0001;
  private static final String EXPECTED_TEXT = "[5.5, 3.3, 8.8]";

  @Test
  void shouldBuildEntityWithGivenValuesAndPreserveOrder() {
    DoubleArrayEntity.Builder builder = new DoubleArrayEntity.Builder();
    for (double value : SOURCE_VALUES) {
      builder.addValue(value);
    }

    DoubleArrayEntity entity = builder.withName(ENTITY_NAME).build();

    assertArrayEquals(SOURCE_VALUES, entity.getElements(), COMPARISON_DELTA);
  }

  @Test
  void shouldReturnSizeEqualToNumberOfAddedValues() {
    DoubleArrayEntity.Builder builder = new DoubleArrayEntity.Builder();
    for (double value : SOURCE_VALUES) {
      builder.addValue(value);
    }

    DoubleArrayEntity entity = builder.withName(ENTITY_NAME).build();

    assertEquals(SOURCE_VALUES.length, entity.getSize());
  }

  @Test
  void shouldReturnConfiguredName() {
    DoubleArrayEntity.Builder builder = new DoubleArrayEntity.Builder();
    builder.addValue(1.0).withName(ENTITY_NAME);

    DoubleArrayEntity entity = builder.build();

    assertEquals(ENTITY_NAME, entity.getName());
  }

  @Test
  void shouldRenderElementsAsText() {
    DoubleArrayEntity.Builder builder = new DoubleArrayEntity.Builder();
    for (double value : SOURCE_VALUES) {
      builder.addValue(value);
    }
    DoubleArrayEntity entity = builder.withName(ENTITY_NAME).build();

    String text = entity.elementsAsText();

    assertEquals(EXPECTED_TEXT, text);
  }

  @Test
  void shouldConvertElementsToDoubles() {
    DoubleArrayEntity.Builder builder = new DoubleArrayEntity.Builder();
    for (double value : SOURCE_VALUES) {
      builder.addValue(value);
    }
    DoubleArrayEntity entity = builder.withName(ENTITY_NAME).build();

    double[] doubles = entity.getElementsAsDoubles();

    assertArrayEquals(SOURCE_VALUES, doubles, COMPARISON_DELTA);
  }

  @Test
  void shouldNotifyObserverWhenElementChanged() {
    DoubleArrayEntity.Builder builder = new DoubleArrayEntity.Builder();
    for (double value : SOURCE_VALUES) {
      builder.addValue(value);
    }
    DoubleArrayEntity entity = builder.withName(ENTITY_NAME).build();
    RecordingObserver observer = new RecordingObserver();
    entity.addObserver(observer);

    entity.setElementAt(0, 42.0);

    assertEquals(1, observer.notificationCount);
  }

  private static final class RecordingObserver implements ArrayChangeObserver {

    private int notificationCount;

    @Override
    public void onArrayChanged(AbstractNumericArray entity) {
      notificationCount++;
    }
  }
}
