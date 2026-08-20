package com.taskarray.entity;

import com.taskarray.observer.ArrayChangeObserver;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractNumericArray {

  private final int size;
  private final String name;
  private final List<ArrayChangeObserver> observers;

  protected AbstractNumericArray(int size, String name) {
    this.size = size;
    this.name = name;
    this.observers = new ArrayList<>();
  }

  public int getSize() {
    return size;
  }

  public String getName() {
    return name;
  }

  public void addObserver(ArrayChangeObserver observer) {
    observers.add(observer);
  }

  public void removeObserver(ArrayChangeObserver observer) {
    observers.remove(observer);
  }

  protected void notifyObservers() {
    for (ArrayChangeObserver observer : observers) {
      observer.onArrayChanged(this);
    }
  }

  public abstract String elementsAsText();

  public abstract double[] getElementsAsDoubles();
}
