package com.taskarray.specification;

public interface Specification<T> {

  boolean isSatisfiedBy(T candidate);
}
