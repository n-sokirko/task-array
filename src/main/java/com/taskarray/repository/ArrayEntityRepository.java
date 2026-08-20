package com.taskarray.repository;

import com.taskarray.entity.AbstractNumericArray;
import com.taskarray.specification.Specification;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public interface ArrayEntityRepository {

  void add(AbstractNumericArray entity);

  void remove(String name);

  Optional<AbstractNumericArray> findByName(String name);

  List<AbstractNumericArray> findAll();

  List<AbstractNumericArray> findBySpecification(Specification<AbstractNumericArray> specification);

  List<AbstractNumericArray> sortedBy(Comparator<AbstractNumericArray> comparator);
}
