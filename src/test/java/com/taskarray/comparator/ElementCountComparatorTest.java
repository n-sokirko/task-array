package com.taskarray.comparator;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.taskarray.entity.IntegerArrayEntity;
import org.junit.jupiter.api.Test;

class ElementCountComparatorTest {

  @Test
  void shouldOrderEntitiesByElementCountAscending() {
    IntegerArrayEntity.Builder smallerBuilder = new IntegerArrayEntity.Builder();
    smallerBuilder.addValue(1);
    IntegerArrayEntity smaller = smallerBuilder.withName("smaller").build();

    IntegerArrayEntity.Builder largerBuilder = new IntegerArrayEntity.Builder();
    largerBuilder.addValue(1);
    largerBuilder.addValue(2);
    largerBuilder.addValue(3);
    IntegerArrayEntity larger = largerBuilder.withName("larger").build();

    ElementCountComparator comparator = new ElementCountComparator();

    int result = comparator.compare(smaller, larger);

    assertTrue(result < 0);
  }
}
