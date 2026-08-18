package com.taskarray.entity;

public abstract class AbstractNumericArray {

  private final int size;

  protected AbstractNumericArray(int size) {
    this.size = size;
  }

  public int getSize() {
    return size;
  }

  public abstract String elementsAsText();
}
