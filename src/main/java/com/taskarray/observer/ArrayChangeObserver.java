package com.taskarray.observer;

import com.taskarray.entity.AbstractNumericArray;

public interface ArrayChangeObserver {

  void onArrayChanged(AbstractNumericArray entity);
}
