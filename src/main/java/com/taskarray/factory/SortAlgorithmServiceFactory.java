package com.taskarray.factory;

import com.taskarray.service.sort.ArraySortService;
import com.taskarray.service.sort.BubbleSortService;
import com.taskarray.service.sort.SelectionSortService;
import com.taskarray.service.sort.SortAlgorithm;

public final class SortAlgorithmServiceFactory implements SortServiceFactory {

  @Override
  public ArraySortService createSortService(SortAlgorithm algorithm) {
    if (algorithm == SortAlgorithm.BUBBLE) {
      return new BubbleSortService();
    }
    return new SelectionSortService();
  }
}
