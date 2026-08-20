package com.taskarray.warehouse.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.taskarray.aggregate.impl.DoubleStreamAggregateCalculator;
import com.taskarray.entity.IntegerArrayEntity;
import com.taskarray.warehouse.ArrayStatistics;
import com.taskarray.warehouse.Warehouse;
import java.util.Optional;
import org.junit.jupiter.api.Test;

class WarehouseSyncObserverTest {

  private static final String ENTITY_NAME = "sync-test-entity";

  @Test
  void shouldPushComputedStatisticsToWarehouseOnChange() {
    RecordingWarehouse warehouse = new RecordingWarehouse();
    WarehouseSyncObserver observer = new WarehouseSyncObserver(warehouse, new DoubleStreamAggregateCalculator());
    IntegerArrayEntity.Builder builder = new IntegerArrayEntity.Builder();
    builder.addValue(2);
    builder.addValue(4);
    builder.addValue(6);
    IntegerArrayEntity entity = builder.withName(ENTITY_NAME).build();

    observer.onArrayChanged(entity);

    ArrayStatistics statistics = warehouse.lastStatistics;
    assertEquals(12.0, statistics.getSum());
    assertEquals(4.0, statistics.getAverage());
    assertEquals(6.0, statistics.getMax());
    assertEquals(2.0, statistics.getMin());
  }

  private static final class RecordingWarehouse implements Warehouse {

    private ArrayStatistics lastStatistics;

    @Override
    public void updateStatistics(String entityName, ArrayStatistics statistics) {
      lastStatistics = statistics;
    }

    @Override
    public void removeStatistics(String entityName) {
      lastStatistics = null;
    }

    @Override
    public Optional<ArrayStatistics> getStatistics(String entityName) {
      return Optional.ofNullable(lastStatistics);
    }
  }
}
