package com.taskarray.service.sort.impl;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

class BubbleSortServiceTest {

  private static final int[] UNSORTED_INT_VALUES = {5, 3, 8, 1, 9};
  private static final int[] SORTED_INT_VALUES = {1, 3, 5, 8, 9};
  private static final double[] UNSORTED_DOUBLE_VALUES = {5.5, 1.1, 3.3};
  private static final double[] SORTED_DOUBLE_VALUES = {1.1, 3.3, 5.5};
  private static final double COMPARISON_DELTA = 0.0001;

  @Test
  void shouldSortIntArrayInAscendingOrder() {
    BubbleSortService service = new BubbleSortService();

    int[] result = service.sort(UNSORTED_INT_VALUES);

    assertArrayEquals(SORTED_INT_VALUES, result);
  }

  @Test
  void shouldSortDoubleArrayInAscendingOrder() {
    BubbleSortService service = new BubbleSortService();

    double[] result = service.sort(UNSORTED_DOUBLE_VALUES);

    assertArrayEquals(SORTED_DOUBLE_VALUES, result, COMPARISON_DELTA);
  }

  @Test
  void shouldNotMutateOriginalArray() {
    int[] original = {5, 3, 8, 1, 9};
    BubbleSortService service = new BubbleSortService();

    service.sort(original);

    assertArrayEquals(UNSORTED_INT_VALUES, original);
  }
}
