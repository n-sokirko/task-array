package com.taskarray.repository.impl;

import com.taskarray.aggregate.impl.DoubleStreamAggregateCalculator;
import com.taskarray.entity.AbstractNumericArray;
import com.taskarray.observer.ArrayChangeObserver;
import com.taskarray.repository.ArrayEntityRepository;
import com.taskarray.specification.Specification;
import com.taskarray.warehouse.Warehouse;
import com.taskarray.warehouse.impl.InMemoryWarehouse;
import com.taskarray.warehouse.impl.WarehouseSyncObserver;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public final class InMemoryArrayEntityRepository implements ArrayEntityRepository {

  private static InMemoryArrayEntityRepository instance;

  private final Map<String, AbstractNumericArray> entitiesByName;
  private final Warehouse warehouse;
  private final ArrayChangeObserver warehouseSyncObserver;

  private InMemoryArrayEntityRepository() {
    this.entitiesByName = new LinkedHashMap<>();
    this.warehouse = InMemoryWarehouse.getInstance();
    this.warehouseSyncObserver = new WarehouseSyncObserver(warehouse, new DoubleStreamAggregateCalculator());
  }

  public static InMemoryArrayEntityRepository getInstance() {
    if (instance == null) {
      instance = new InMemoryArrayEntityRepository();
    }
    return instance;
  }

  @Override
  public void add(AbstractNumericArray entity) {
    entitiesByName.put(entity.getName(), entity);
    entity.addObserver(warehouseSyncObserver);
    warehouseSyncObserver.onArrayChanged(entity);
  }

  @Override
  public void remove(String name) {
    AbstractNumericArray removedEntity = entitiesByName.remove(name);
    if (removedEntity != null) {
      removedEntity.removeObserver(warehouseSyncObserver);
      warehouse.removeStatistics(name);
    }
  }

  @Override
  public Optional<AbstractNumericArray> findByName(String name) {
    AbstractNumericArray entity = entitiesByName.get(name);
    if (entity == null) {
      return Optional.empty();
    }
    return Optional.of(entity);
  }

  @Override
  public List<AbstractNumericArray> findAll() {
    return new ArrayList<>(entitiesByName.values());
  }

  @Override
  public List<AbstractNumericArray> findBySpecification(Specification<AbstractNumericArray> specification) {
    List<AbstractNumericArray> result = new ArrayList<>();
    for (AbstractNumericArray entity : entitiesByName.values()) {
      if (specification.isSatisfiedBy(entity)) {
        result.add(entity);
      }
    }
    return result;
  }

  @Override
  public List<AbstractNumericArray> sortedBy(Comparator<AbstractNumericArray> comparator) {
    List<AbstractNumericArray> result = findAll();
    result.sort(comparator);
    return result;
  }
}
