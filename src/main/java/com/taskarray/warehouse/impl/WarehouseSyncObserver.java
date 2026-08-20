package com.taskarray.warehouse.impl;

import com.taskarray.aggregate.AggregateType;
import com.taskarray.aggregate.ArrayAggregateCalculator;
import com.taskarray.entity.AbstractNumericArray;
import com.taskarray.observer.ArrayChangeObserver;
import com.taskarray.warehouse.ArrayStatistics;
import com.taskarray.warehouse.Warehouse;

public final class WarehouseSyncObserver implements ArrayChangeObserver {

  private final Warehouse warehouse;
  private final ArrayAggregateCalculator aggregateCalculator;

  public WarehouseSyncObserver(Warehouse warehouse, ArrayAggregateCalculator aggregateCalculator) {
    this.warehouse = warehouse;
    this.aggregateCalculator = aggregateCalculator;
  }

  @Override
  public void onArrayChanged(AbstractNumericArray entity) {
    double[] values = entity.getElementsAsDoubles();
    double sum = aggregateCalculator.calculate(AggregateType.SUM, values);
    double average = aggregateCalculator.calculate(AggregateType.AVERAGE, values);
    double max = aggregateCalculator.calculate(AggregateType.MAX, values);
    double min = aggregateCalculator.calculate(AggregateType.MIN, values);
    warehouse.updateStatistics(entity.getName(), new ArrayStatistics(sum, average, max, min));
  }
}
