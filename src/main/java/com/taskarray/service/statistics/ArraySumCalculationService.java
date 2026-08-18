package com.taskarray.service.statistics;

import java.util.Optional;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

public final class ArraySumCalculationService implements SumCalculationService {

  @Override
  public Optional<Integer> calculateSum(int[] values) {
    if (values.length == 0) {
      return Optional.empty();
    }
    int sum = IntStream.of(values).sum();
    return Optional.of(sum);
  }

  @Override
  public Optional<Double> calculateSum(double[] values) {
    if (values.length == 0) {
      return Optional.empty();
    }
    double sum = DoubleStream.of(values).sum();
    return Optional.of(sum);
  }
}
