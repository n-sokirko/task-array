package com.taskarray.specification.impl;

import com.taskarray.aggregate.AggregateType;
import com.taskarray.aggregate.ArrayAggregateCalculator;
import com.taskarray.entity.AbstractNumericArray;
import com.taskarray.specification.ComparisonOperator;
import com.taskarray.specification.Specification;

public final class AggregateValueSpecification implements Specification<AbstractNumericArray> {

  private static final double EQUALITY_TOLERANCE = 1e-9;

  private final AggregateType aggregateType;
  private final ComparisonOperator comparisonOperator;
  private final double threshold;
  private final ArrayAggregateCalculator aggregateCalculator;

  public AggregateValueSpecification(AggregateType aggregateType, ComparisonOperator comparisonOperator,
      double threshold, ArrayAggregateCalculator aggregateCalculator) {
    this.aggregateType = aggregateType;
    this.comparisonOperator = comparisonOperator;
    this.threshold = threshold;
    this.aggregateCalculator = aggregateCalculator;
  }

  @Override
  public boolean isSatisfiedBy(AbstractNumericArray candidate) {
    double value = aggregateCalculator.calculate(aggregateType, candidate.getElementsAsDoubles());
    if (comparisonOperator == ComparisonOperator.GREATER_THAN) {
      return value > threshold;
    }
    if (comparisonOperator == ComparisonOperator.LESS_THAN) {
      return value < threshold;
    }
    return Math.abs(value - threshold) < EQUALITY_TOLERANCE;
  }
}
