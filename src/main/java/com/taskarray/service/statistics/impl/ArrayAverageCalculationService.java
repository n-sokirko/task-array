package com.taskarray.service.statistics.impl;

import com.taskarray.service.statistics.AverageCalculationService;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

public final class ArrayAverageCalculationService implements AverageCalculationService {

  @Override
  public Optional<Double> calculateAverage(int[] values) {
    OptionalDouble result = IntStream.of(values).average();
    return result.isPresent() ? Optional.of(result.getAsDouble()) : Optional.empty();
  }

  @Override
  public Optional<Double> calculateAverage(double[] values) {
    OptionalDouble result = DoubleStream.of(values).average();
    return result.isPresent() ? Optional.of(result.getAsDouble()) : Optional.empty();
  }
}
