package com.taskarray.service.statistics;

import java.util.Optional;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

public final class ArrayMinMaxSearchService implements MinMaxSearchService {

  @Override
  public Optional<Integer> findMinimum(int[] values) {
    OptionalInt result = IntStream.of(values).min();
    return result.isPresent() ? Optional.of(result.getAsInt()) : Optional.empty();
  }

  @Override
  public Optional<Integer> findMaximum(int[] values) {
    OptionalInt result = IntStream.of(values).max();
    return result.isPresent() ? Optional.of(result.getAsInt()) : Optional.empty();
  }

  @Override
  public Optional<Double> findMinimum(double[] values) {
    OptionalDouble result = DoubleStream.of(values).min();
    return result.isPresent() ? Optional.of(result.getAsDouble()) : Optional.empty();
  }

  @Override
  public Optional<Double> findMaximum(double[] values) {
    OptionalDouble result = DoubleStream.of(values).max();
    return result.isPresent() ? Optional.of(result.getAsDouble()) : Optional.empty();
  }
}
