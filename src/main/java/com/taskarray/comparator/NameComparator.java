package com.taskarray.comparator;

import com.taskarray.entity.AbstractNumericArray;
import java.util.Comparator;

public final class NameComparator implements Comparator<AbstractNumericArray> {

  @Override
  public int compare(AbstractNumericArray first, AbstractNumericArray second) {
    return first.getName().compareTo(second.getName());
  }
}
