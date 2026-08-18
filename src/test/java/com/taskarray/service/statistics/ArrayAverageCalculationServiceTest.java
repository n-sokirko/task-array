package com.taskarray.service.statistics;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;
import org.junit.jupiter.api.Test;

class ArrayAverageCalculationServiceTest {

  private static final int[] INT_VALUES = {2, 4, 6};
  private static final double[] DOUBLE_VALUES = {1.0, 2.0, 3.0};
  private static final int[] EMPTY_INT_VALUES = {};

  @Test
  void shouldCalculateAverageOfIntArray() {
    ArrayAverageCalculationService service = new ArrayAverageCalculationService();

    Optional<Double> average = service.calculateAverage(INT_VALUES);

    assertEquals(Optional.of(4.0), average);
  }

  @Test
  void shouldCalculateAverageOfDoubleArray() {
    ArrayAverageCalculationService service = new ArrayAverageCalculationService();

    Optional<Double> average = service.calculateAverage(DOUBLE_VALUES);

    assertEquals(Optional.of(2.0), average);
  }

  @Test
  void shouldReturnEmptyOptionalForEmptyArray() {
    ArrayAverageCalculationService service = new ArrayAverageCalculationService();

    Optional<Double> average = service.calculateAverage(EMPTY_INT_VALUES);

    assertTrue(average.isEmpty());
  }
}
