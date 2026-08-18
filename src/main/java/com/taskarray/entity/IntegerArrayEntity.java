package com.taskarray.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class IntegerArrayEntity extends AbstractNumericArray {

  private final int[] elements;

  private IntegerArrayEntity(int[] elements) {
    super(elements.length);
    this.elements = elements;
  }

  public int[] getElements() {
    return Arrays.copyOf(elements, elements.length);
  }

  @Override
  public String elementsAsText() {
    return Arrays.toString(elements);
  }

  public static final class Builder {

    private final List<Integer> values = new ArrayList<>();

    public Builder addValue(int value) {
      values.add(value);
      return this;
    }

    public IntegerArrayEntity build() {
      int[] result = new int[values.size()];
      for (int index = 0; index < values.size(); index++) {
        result[index] = values.get(index);
      }
      return new IntegerArrayEntity(result);
    }
  }
}
