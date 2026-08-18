package com.taskarray.service.statistics;

import java.util.Optional;

public interface AverageCalculationService {

  Optional<Double> calculateAverage(int[] values);

  Optional<Double> calculateAverage(double[] values);
}
