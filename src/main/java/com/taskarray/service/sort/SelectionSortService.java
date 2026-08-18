package com.taskarray.service.sort;

import java.util.Arrays;

public final class SelectionSortService implements ArraySortService {

  @Override
  public int[] sort(int[] values) {
    int[] result = Arrays.copyOf(values, values.length);
    for (int current = 0; current < result.length - 1; current++) {
      int minIndex = findMinimumIndex(result, current);
      swap(result, current, minIndex);
    }
    return result;
  }

  @Override
  public double[] sort(double[] values) {
    double[] result = Arrays.copyOf(values, values.length);
    for (int current = 0; current < result.length - 1; current++) {
      int minIndex = findMinimumIndex(result, current);
      swap(result, current, minIndex);
    }
    return result;
  }

  private int findMinimumIndex(int[] array, int startIndex) {
    int minIndex = startIndex;
    for (int index = startIndex + 1; index < array.length; index++) {
      if (array[index] < array[minIndex]) {
        minIndex = index;
      }
    }
    return minIndex;
  }

  private int findMinimumIndex(double[] array, int startIndex) {
    int minIndex = startIndex;
    for (int index = startIndex + 1; index < array.length; index++) {
      if (array[index] < array[minIndex]) {
        minIndex = index;
      }
    }
    return minIndex;
  }

  private void swap(int[] array, int firstIndex, int secondIndex) {
    int temp = array[firstIndex];
    array[firstIndex] = array[secondIndex];
    array[secondIndex] = temp;
  }

  private void swap(double[] array, int firstIndex, int secondIndex) {
    double temp = array[firstIndex];
    array[firstIndex] = array[secondIndex];
    array[secondIndex] = temp;
  }
}
