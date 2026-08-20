package com.taskarray.service.statistics.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;
import org.junit.jupiter.api.Test;

class ArrayMinMaxSearchServiceTest {

  private static final int[] INT_VALUES = {5, 3, 8, 1, 9};
  private static final double[] DOUBLE_VALUES = {5.5, 3.3, 8.8, 1.1};
  private static final int[] EMPTY_INT_VALUES = {};

  @Test
  void shouldFindMinimumOfIntArray() {
    ArrayMinMaxSearchService service = new ArrayMinMaxSearchService();

    Optional<Integer> minimum = service.findMinimum(INT_VALUES);

    assertEquals(Optional.of(1), minimum);
  }

  @Test
  void shouldFindMaximumOfIntArray() {
    ArrayMinMaxSearchService service = new ArrayMinMaxSearchService();

    Optional<Integer> maximum = service.findMaximum(INT_VALUES);

    assertEquals(Optional.of(9), maximum);
  }

  @Test
  void shouldFindMinimumOfDoubleArray() {
    ArrayMinMaxSearchService service = new ArrayMinMaxSearchService();

    Optional<Double> minimum = service.findMinimum(DOUBLE_VALUES);

    assertEquals(Optional.of(1.1), minimum);
  }

  @Test
  void shouldFindMaximumOfDoubleArray() {
    ArrayMinMaxSearchService service = new ArrayMinMaxSearchService();

    Optional<Double> maximum = service.findMaximum(DOUBLE_VALUES);

    assertEquals(Optional.of(8.8), maximum);
  }

  @Test
  void shouldReturnEmptyOptionalForEmptyIntArray() {
    ArrayMinMaxSearchService service = new ArrayMinMaxSearchService();

    Optional<Integer> minimum = service.findMinimum(EMPTY_INT_VALUES);

    assertTrue(minimum.isEmpty());
  }
}
