package com.taskarray.service.statistics;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;
import org.junit.jupiter.api.Test;

class ArraySumCalculationServiceTest {

  private static final int[] INT_VALUES = {1, 2, 3, 4};
  private static final double[] DOUBLE_VALUES = {1.5, 2.5, 3.0};
  private static final int[] EMPTY_INT_VALUES = {};

  @Test
  void shouldCalculateSumOfIntArray() {
    ArraySumCalculationService service = new ArraySumCalculationService();

    Optional<Integer> sum = service.calculateSum(INT_VALUES);

    assertEquals(Optional.of(10), sum);
  }

  @Test
  void shouldCalculateSumOfDoubleArray() {
    ArraySumCalculationService service = new ArraySumCalculationService();

    Optional<Double> sum = service.calculateSum(DOUBLE_VALUES);

    assertEquals(Optional.of(7.0), sum);
  }

  @Test
  void shouldReturnEmptyOptionalForEmptyArray() {
    ArraySumCalculationService service = new ArraySumCalculationService();

    Optional<Integer> sum = service.calculateSum(EMPTY_INT_VALUES);

    assertTrue(sum.isEmpty());
  }
}
