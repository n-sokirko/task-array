package com.taskarray.specification.impl;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.taskarray.aggregate.AggregateType;
import com.taskarray.aggregate.impl.DoubleStreamAggregateCalculator;
import com.taskarray.entity.IntegerArrayEntity;
import com.taskarray.specification.ComparisonOperator;
import org.junit.jupiter.api.Test;

class AggregateValueSpecificationTest {

  private static final String ENTITY_NAME = "int-array-under-test";

  @Test
  void shouldBeSatisfiedWhenSumIsGreaterThanThreshold() {
    IntegerArrayEntity entity = buildEntityWithValues(10, 20, 30);
    AggregateValueSpecification specification = new AggregateValueSpecification(
        AggregateType.SUM, ComparisonOperator.GREATER_THAN, 50.0, new DoubleStreamAggregateCalculator());

    boolean satisfied = specification.isSatisfiedBy(entity);

    assertTrue(satisfied);
  }

  @Test
  void shouldNotBeSatisfiedWhenSumIsNotGreaterThanThreshold() {
    IntegerArrayEntity entity = buildEntityWithValues(1, 2, 3);
    AggregateValueSpecification specification = new AggregateValueSpecification(
        AggregateType.SUM, ComparisonOperator.GREATER_THAN, 50.0, new DoubleStreamAggregateCalculator());

    boolean satisfied = specification.isSatisfiedBy(entity);

    assertFalse(satisfied);
  }

  @Test
  void shouldBeSatisfiedWhenMaxIsEqualToThreshold() {
    IntegerArrayEntity entity = buildEntityWithValues(1, 5, 3);
    AggregateValueSpecification specification = new AggregateValueSpecification(
        AggregateType.MAX, ComparisonOperator.EQUAL_TO, 5.0, new DoubleStreamAggregateCalculator());

    boolean satisfied = specification.isSatisfiedBy(entity);

    assertTrue(satisfied);
  }

  @Test
  void shouldBeSatisfiedWhenMinIsLessThanThreshold() {
    IntegerArrayEntity entity = buildEntityWithValues(1, 5, 3);
    AggregateValueSpecification specification = new AggregateValueSpecification(
        AggregateType.MIN, ComparisonOperator.LESS_THAN, 2.0, new DoubleStreamAggregateCalculator());

    boolean satisfied = specification.isSatisfiedBy(entity);

    assertTrue(satisfied);
  }

  private IntegerArrayEntity buildEntityWithValues(int first, int second, int third) {
    IntegerArrayEntity.Builder builder = new IntegerArrayEntity.Builder();
    builder.addValue(first);
    builder.addValue(second);
    builder.addValue(third);
    return builder.withName(ENTITY_NAME).build();
  }
}
