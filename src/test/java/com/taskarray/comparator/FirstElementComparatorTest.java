package com.taskarray.comparator;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.taskarray.entity.IntegerArrayEntity;
import org.junit.jupiter.api.Test;

class FirstElementComparatorTest {

  @Test
  void shouldOrderEntitiesByFirstElementAscending() {
    IntegerArrayEntity first = new IntegerArrayEntity.Builder().addValue(10).withName("first").build();
    IntegerArrayEntity second = new IntegerArrayEntity.Builder().addValue(20).withName("second").build();
    FirstElementComparator comparator = new FirstElementComparator();

    int result = comparator.compare(first, second);

    assertTrue(result < 0);
  }
}
