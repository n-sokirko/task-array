package com.taskarray.factory.impl;

import com.taskarray.factory.SortServiceFactory;
import com.taskarray.service.sort.ArraySortService;
import com.taskarray.service.sort.SortAlgorithm;
import com.taskarray.service.sort.impl.BubbleSortService;
import com.taskarray.service.sort.impl.SelectionSortService;

public final class SortAlgorithmServiceFactory implements SortServiceFactory {

  @Override
  public ArraySortService createSortService(SortAlgorithm algorithm) {
    if (algorithm == SortAlgorithm.BUBBLE) {
      return new BubbleSortService();
    }
    return new SelectionSortService();
  }
}
