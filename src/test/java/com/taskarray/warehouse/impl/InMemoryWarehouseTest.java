package com.taskarray.warehouse.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.taskarray.warehouse.ArrayStatistics;
import java.util.Optional;
import org.junit.jupiter.api.Test;

class InMemoryWarehouseTest {

  private static final String ENTITY_NAME = "warehouse-test-entity";

  @Test
  void shouldReturnStatisticsAfterUpdate() {
    InMemoryWarehouse warehouse = InMemoryWarehouse.getInstance();
    ArrayStatistics statistics = new ArrayStatistics(6.0, 2.0, 3.0, 1.0);

    warehouse.updateStatistics(ENTITY_NAME, statistics);

    Optional<ArrayStatistics> result = warehouse.getStatistics(ENTITY_NAME);
    assertEquals(6.0, result.get().getSum());
  }

  @Test
  void shouldReturnEmptyOptionalForUnknownEntity() {
    InMemoryWarehouse warehouse = InMemoryWarehouse.getInstance();

    Optional<ArrayStatistics> result = warehouse.getStatistics("unknown-warehouse-entity");

    assertTrue(result.isEmpty());
  }

  @Test
  void shouldRemoveStatistics() {
    InMemoryWarehouse warehouse = InMemoryWarehouse.getInstance();
    warehouse.updateStatistics(ENTITY_NAME, new ArrayStatistics(1.0, 1.0, 1.0, 1.0));

    warehouse.removeStatistics(ENTITY_NAME);

    assertTrue(warehouse.getStatistics(ENTITY_NAME).isEmpty());
  }
}
