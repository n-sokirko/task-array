package com.taskarray.specification.impl;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.taskarray.entity.IntegerArrayEntity;
import org.junit.jupiter.api.Test;

class NameSpecificationTest {

  private static final String MATCHING_NAME = "int-array-1";
  private static final String OTHER_NAME = "int-array-2";

  @Test
  void shouldBeSatisfiedByEntityWithMatchingName() {
    IntegerArrayEntity entity = new IntegerArrayEntity.Builder().addValue(1).withName(MATCHING_NAME).build();
    NameSpecification specification = new NameSpecification(MATCHING_NAME);

    boolean satisfied = specification.isSatisfiedBy(entity);

    assertTrue(satisfied);
  }

  @Test
  void shouldNotBeSatisfiedByEntityWithDifferentName() {
    IntegerArrayEntity entity = new IntegerArrayEntity.Builder().addValue(1).withName(OTHER_NAME).build();
    NameSpecification specification = new NameSpecification(MATCHING_NAME);

    boolean satisfied = specification.isSatisfiedBy(entity);

    assertFalse(satisfied);
  }
}
