package com.taskarray.aggregate.impl;

import com.taskarray.aggregate.AggregateType;
import com.taskarray.aggregate.ArrayAggregateCalculator;
import java.util.stream.DoubleStream;

public final class DoubleStreamAggregateCalculator implements ArrayAggregateCalculator {

  @Override
  public double calculate(AggregateType aggregateType, double[] values) {
    if (aggregateType == AggregateType.SUM) {
      return DoubleStream.of(values).sum();
    }
    if (aggregateType == AggregateType.AVERAGE) {
      return DoubleStream.of(values).average().orElse(0.0);
    }
    if (aggregateType == AggregateType.MAX) {
      return DoubleStream.of(values).max().orElse(0.0);
    }
    if (aggregateType == AggregateType.MIN) {
      return DoubleStream.of(values).min().orElse(0.0);
    }
    return values.length;
  }
}
