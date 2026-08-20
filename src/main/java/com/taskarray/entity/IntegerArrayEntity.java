package com.taskarray.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class IntegerArrayEntity extends AbstractNumericArray {

  private final int[] elements;

  private IntegerArrayEntity(int[] elements, String name) {
    super(elements.length, name);
    this.elements = elements;
  }

  public int[] getElements() {
    return Arrays.copyOf(elements, elements.length);
  }

  public void setElementAt(int index, int value) {
    elements[index] = value;
    notifyObservers();
  }

  @Override
  public String elementsAsText() {
    return Arrays.toString(elements);
  }

  @Override
  public double[] getElementsAsDoubles() {
    double[] result = new double[elements.length];
    for (int index = 0; index < elements.length; index++) {
      result[index] = elements[index];
    }
    return result;
  }

  public static final class Builder {

    private final List<Integer> values = new ArrayList<>();
    private String name;

    public Builder addValue(int value) {
      values.add(value);
      return this;
    }

    public Builder withName(String builderName) {
      this.name = builderName;
      return this;
    }

    public IntegerArrayEntity build() {
      int[] result = new int[values.size()];
      for (int index = 0; index < values.size(); index++) {
        result[index] = values.get(index);
      }
      return new IntegerArrayEntity(result, name);
    }
  }
}
