package com.taskarray.specification.impl;

import com.taskarray.entity.AbstractNumericArray;
import com.taskarray.specification.Specification;

public final class NameSpecification implements Specification<AbstractNumericArray> {

  private final String name;

  public NameSpecification(String name) {
    this.name = name;
  }

  @Override
  public boolean isSatisfiedBy(AbstractNumericArray candidate) {
    return candidate.getName().equals(name);
  }
}
