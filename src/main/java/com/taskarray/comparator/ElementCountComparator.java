package com.taskarray.comparator;

import com.taskarray.entity.AbstractNumericArray;
import java.util.Comparator;

public final class ElementCountComparator implements Comparator<AbstractNumericArray> {

  @Override
  public int compare(AbstractNumericArray first, AbstractNumericArray second) {
    return Integer.compare(first.getSize(), second.getSize());
  }
}
