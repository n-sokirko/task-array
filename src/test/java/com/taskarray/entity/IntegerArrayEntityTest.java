package com.taskarray.entity;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.taskarray.observer.ArrayChangeObserver;
import org.junit.jupiter.api.Test;

class IntegerArrayEntityTest {

  private static final int[] SOURCE_VALUES = {5, 3, 8, 1};
  private static final String ENTITY_NAME = "int-array-under-test";
  private static final String EXPECTED_TEXT = "[5, 3, 8, 1]";
  private static final double[] EXPECTED_DOUBLES = {5.0, 3.0, 8.0, 1.0};
  private static final double COMPARISON_DELTA = 0.0001;

  @Test
  void shouldBuildEntityWithGivenValuesAndPreserveOrder() {
    IntegerArrayEntity.Builder builder = new IntegerArrayEntity.Builder();
    for (int value : SOURCE_VALUES) {
      builder.addValue(value);
    }

    IntegerArrayEntity entity = builder.withName(ENTITY_NAME).build();

    assertArrayEquals(SOURCE_VALUES, entity.getElements());
  }

  @Test
  void shouldReturnSizeEqualToNumberOfAddedValues() {
    IntegerArrayEntity.Builder builder = new IntegerArrayEntity.Builder();
    for (int value : SOURCE_VALUES) {
      builder.addValue(value);
    }

    IntegerArrayEntity entity = builder.withName(ENTITY_NAME).build();

    assertEquals(SOURCE_VALUES.length, entity.getSize());
  }

  @Test
  void shouldReturnConfiguredName() {
    IntegerArrayEntity.Builder builder = new IntegerArrayEntity.Builder();
    builder.addValue(1).withName(ENTITY_NAME);

    IntegerArrayEntity entity = builder.build();

    assertEquals(ENTITY_NAME, entity.getName());
  }

  @Test
  void shouldRenderElementsAsText() {
    IntegerArrayEntity.Builder builder = new IntegerArrayEntity.Builder();
    for (int value : SOURCE_VALUES) {
      builder.addValue(value);
    }
    IntegerArrayEntity entity = builder.withName(ENTITY_NAME).build();

    String text = entity.elementsAsText();

    assertEquals(EXPECTED_TEXT, text);
  }

  @Test
  void shouldConvertElementsToDoubles() {
    IntegerArrayEntity.Builder builder = new IntegerArrayEntity.Builder();
    for (int value : SOURCE_VALUES) {
      builder.addValue(value);
    }
    IntegerArrayEntity entity = builder.withName(ENTITY_NAME).build();

    double[] doubles = entity.getElementsAsDoubles();

    assertArrayEquals(EXPECTED_DOUBLES, doubles, COMPARISON_DELTA);
  }

  @Test
  void shouldReturnDefensiveCopyOfElements() {
    IntegerArrayEntity.Builder builder = new IntegerArrayEntity.Builder();
    builder.addValue(1).withName(ENTITY_NAME);
    IntegerArrayEntity entity = builder.build();
    int[] firstCall = entity.getElements();
    firstCall[0] = 999;

    int[] secondCall = entity.getElements();

    assertEquals(1, secondCall[0]);
  }

  @Test
  void shouldNotifyObserverWhenElementChanged() {
    IntegerArrayEntity.Builder builder = new IntegerArrayEntity.Builder();
    for (int value : SOURCE_VALUES) {
      builder.addValue(value);
    }
    IntegerArrayEntity entity = builder.withName(ENTITY_NAME).build();
    RecordingObserver observer = new RecordingObserver();
    entity.addObserver(observer);

    entity.setElementAt(0, 42);

    assertEquals(1, observer.notificationCount);
  }

  @Test
  void shouldUpdateElementValueWhenSet() {
    IntegerArrayEntity.Builder builder = new IntegerArrayEntity.Builder();
    for (int value : SOURCE_VALUES) {
      builder.addValue(value);
    }
    IntegerArrayEntity entity = builder.withName(ENTITY_NAME).build();

    entity.setElementAt(0, 42);

    assertEquals(42, entity.getElements()[0]);
  }

  private static final class RecordingObserver implements ArrayChangeObserver {

    private int notificationCount;

    @Override
    public void onArrayChanged(AbstractNumericArray entity) {
      notificationCount++;
    }
  }
}
