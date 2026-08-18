package com.taskarray.service.statistics;

import java.util.Optional;

public interface MinMaxSearchService {

  Optional<Integer> findMinimum(int[] values);

  Optional<Integer> findMaximum(int[] values);

  Optional<Double> findMinimum(double[] values);

  Optional<Double> findMaximum(double[] values);
}
