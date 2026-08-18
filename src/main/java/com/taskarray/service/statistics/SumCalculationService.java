package com.taskarray.service.statistics;

import java.util.Optional;

public interface SumCalculationService {

  Optional<Integer> calculateSum(int[] values);

  Optional<Double> calculateSum(double[] values);
}
