package com.taskarray.comparator;

import com.taskarray.entity.AbstractNumericArray;
import java.util.Comparator;

public final class FirstElementComparator implements Comparator<AbstractNumericArray> {

  private static final double DEFAULT_VALUE_FOR_EMPTY_ARRAY = 0.0;

  @Override
  public int compare(AbstractNumericArray first, AbstractNumericArray second) {
    double firstValue = firstElementOf(first);
    double secondValue = firstElementOf(second);
    return Double.compare(firstValue, secondValue);
  }

  private double firstElementOf(AbstractNumericArray entity) {
    double[] values = entity.getElementsAsDoubles();
    if (values.length == 0) {
      return DEFAULT_VALUE_FOR_EMPTY_ARRAY;
    }
    return values[0];
  }
}
