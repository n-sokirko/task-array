package com.taskarray.warehouse;

public final class ArrayStatistics {

  private final double sum;
  private final double average;
  private final double max;
  private final double min;

  public ArrayStatistics(double sum, double average, double max, double min) {
    this.sum = sum;
    this.average = average;
    this.max = max;
    this.min = min;
  }

  public double getSum() {
    return sum;
  }

  public double getAverage() {
    return average;
  }

  public double getMax() {
    return max;
  }

  public double getMin() {
    return min;
  }

  @Override
  public String toString() {
    return "ArrayStatistics{sum=" + sum + ", average=" + average + ", max=" + max + ", min=" + min + "}";
  }
}
