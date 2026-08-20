package com.taskarray.aggregate.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.taskarray.aggregate.AggregateType;
import org.junit.jupiter.api.Test;

class DoubleStreamAggregateCalculatorTest {

  private static final double[] VALUES = {2.0, 4.0, 6.0};
  private static final double COMPARISON_DELTA = 0.0001;

  @Test
  void shouldCalculateSum() {
    DoubleStreamAggregateCalculator calculator = new DoubleStreamAggregateCalculator();

    double result = calculator.calculate(AggregateType.SUM, VALUES);

    assertEquals(12.0, result, COMPARISON_DELTA);
  }

  @Test
  void shouldCalculateAverage() {
    DoubleStreamAggregateCalculator calculator = new DoubleStreamAggregateCalculator();

    double result = calculator.calculate(AggregateType.AVERAGE, VALUES);

    assertEquals(4.0, result, COMPARISON_DELTA);
  }

  @Test
  void shouldCalculateMax() {
    DoubleStreamAggregateCalculator calculator = new DoubleStreamAggregateCalculator();

    double result = calculator.calculate(AggregateType.MAX, VALUES);

    assertEquals(6.0, result, COMPARISON_DELTA);
  }

  @Test
  void shouldCalculateMin() {
    DoubleStreamAggregateCalculator calculator = new DoubleStreamAggregateCalculator();

    double result = calculator.calculate(AggregateType.MIN, VALUES);

    assertEquals(2.0, result, COMPARISON_DELTA);
  }

  @Test
  void shouldCalculateCount() {
    DoubleStreamAggregateCalculator calculator = new DoubleStreamAggregateCalculator();

    double result = calculator.calculate(AggregateType.COUNT, VALUES);

    assertEquals(3.0, result, COMPARISON_DELTA);
  }
}
