package com.taskarray.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class DoubleArrayEntity extends AbstractNumericArray {

  private final double[] elements;

  private DoubleArrayEntity(double[] elements) {
    super(elements.length);
    this.elements = elements;
  }

  public double[] getElements() {
    return Arrays.copyOf(elements, elements.length);
  }

  @Override
  public String elementsAsText() {
    return Arrays.toString(elements);
  }

  public static final class Builder {

    private final List<Double> values = new ArrayList<>();

    public Builder addValue(double value) {
      values.add(value);
      return this;
    }

    public DoubleArrayEntity build() {
      double[] result = new double[values.size()];
      for (int index = 0; index < values.size(); index++) {
        result[index] = values.get(index);
      }
      return new DoubleArrayEntity(result);
    }
  }
}
