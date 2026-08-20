package com.taskarray.comparator;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.taskarray.entity.IntegerArrayEntity;
import org.junit.jupiter.api.Test;

class NameComparatorTest {

  @Test
  void shouldOrderEntitiesByNameAscending() {
    IntegerArrayEntity first = new IntegerArrayEntity.Builder().addValue(1).withName("alpha").build();
    IntegerArrayEntity second = new IntegerArrayEntity.Builder().addValue(1).withName("beta").build();
    NameComparator comparator = new NameComparator();

    int result = comparator.compare(first, second);

    assertTrue(result < 0);
  }
}
