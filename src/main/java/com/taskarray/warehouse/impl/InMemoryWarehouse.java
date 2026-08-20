package com.taskarray.warehouse.impl;

import com.taskarray.warehouse.ArrayStatistics;
import com.taskarray.warehouse.Warehouse;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public final class InMemoryWarehouse implements Warehouse {

  private static InMemoryWarehouse instance;

  private final Map<String, ArrayStatistics> statisticsByEntityName;

  private InMemoryWarehouse() {
    this.statisticsByEntityName = new HashMap<>();
  }

  public static InMemoryWarehouse getInstance() {
    if (instance == null) {
      instance = new InMemoryWarehouse();
    }
    return instance;
  }

  @Override
  public void updateStatistics(String entityName, ArrayStatistics statistics) {
    statisticsByEntityName.put(entityName, statistics);
  }

  @Override
  public void removeStatistics(String entityName) {
    statisticsByEntityName.remove(entityName);
  }

  @Override
  public Optional<ArrayStatistics> getStatistics(String entityName) {
    ArrayStatistics statistics = statisticsByEntityName.get(entityName);
    if (statistics == null) {
      return Optional.empty();
    }
    return Optional.of(statistics);
  }
}
