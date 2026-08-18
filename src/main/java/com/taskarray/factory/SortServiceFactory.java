package com.taskarray.factory;

import com.taskarray.service.sort.ArraySortService;
import com.taskarray.service.sort.SortAlgorithm;

public interface SortServiceFactory {

  ArraySortService createSortService(SortAlgorithm algorithm);
}
