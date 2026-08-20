package com.taskarray.warehouse;

import java.util.Optional;

public interface Warehouse {

  void updateStatistics(String entityName, ArrayStatistics statistics);

  void removeStatistics(String entityName);

  Optional<ArrayStatistics> getStatistics(String entityName);
}
