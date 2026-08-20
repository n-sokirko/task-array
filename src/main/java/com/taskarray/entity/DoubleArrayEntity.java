package com.taskarray.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class DoubleArrayEntity extends AbstractNumericArray {

  private final double[] elements;

  private DoubleArrayEntity(double[] elements, String name) {
    super(elements.length, name);
    this.elements = elements;
  }

  public double[] getElements() {
    return Arrays.copyOf(elements, elements.length);
  }

  public void setElementAt(int index, double value) {
    elements[index] = value;
    notifyObservers();
  }

  @Override
  public String elementsAsText() {
    return Arrays.toString(elements);
  }

  @Override
  public double[] getElementsAsDoubles() {
    return Arrays.copyOf(elements, elements.length);
  }

  public static final class Builder {

    private final List<Double> values = new ArrayList<>();
    private String name;

    public Builder addValue(double value) {
      values.add(value);
      return this;
    }

    public Builder withName(String builderName) {
      this.name = builderName;
      return this;
    }

    public DoubleArrayEntity build() {
      double[] result = new double[values.size()];
      for (int index = 0; index < values.size(); index++) {
        result[index] = values.get(index);
      }
      return new DoubleArrayEntity(result, name);
    }
  }
}
