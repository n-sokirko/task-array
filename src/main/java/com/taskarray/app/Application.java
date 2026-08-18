package com.taskarray.app;

import com.taskarray.entity.DoubleArrayEntity;
import com.taskarray.entity.IntegerArrayEntity;
import com.taskarray.exception.InvalidArrayDataException;
import com.taskarray.factory.ArrayEntityFactory;
import com.taskarray.factory.DoubleArrayEntityFactory;
import com.taskarray.factory.IntegerArrayEntityFactory;
import com.taskarray.factory.SortAlgorithmServiceFactory;
import com.taskarray.factory.SortServiceFactory;
import com.taskarray.file.parser.ArrayLineParser;
import com.taskarray.file.parser.DelimiterArrayLineParser;
import com.taskarray.file.reader.ArrayDataFileReader;
import com.taskarray.file.reader.TextArrayDataFileReader;
import com.taskarray.service.sort.ArraySortService;
import com.taskarray.service.sort.SortAlgorithm;
import com.taskarray.service.statistics.ArrayAverageCalculationService;
import com.taskarray.service.statistics.ArrayMinMaxSearchService;
import com.taskarray.service.statistics.ArraySumCalculationService;
import com.taskarray.service.statistics.AverageCalculationService;
import com.taskarray.service.statistics.MinMaxSearchService;
import com.taskarray.service.statistics.SumCalculationService;
import com.taskarray.validation.DoubleTokenValidator;
import com.taskarray.validation.IntegerTokenValidator;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class Application {

  private static final Logger LOGGER = LogManager.getLogger(Application.class);
  private static final String INTEGER_DATA_FILE = "data/int-arrays.txt";
  private static final String DOUBLE_DATA_FILE = "data/double-arrays.txt";

  private final ArrayDataFileReader fileReader;
  private final ArrayLineParser lineParser;
  private final ArrayEntityFactory integerArrayEntityFactory;
  private final ArrayEntityFactory doubleArrayEntityFactory;
  private final MinMaxSearchService minMaxSearchService;
  private final SumCalculationService sumCalculationService;
  private final AverageCalculationService averageCalculationService;
  private final SortServiceFactory sortServiceFactory;

  public Application() {
    this.fileReader = new TextArrayDataFileReader();
    this.lineParser = new DelimiterArrayLineParser();
    this.integerArrayEntityFactory = new IntegerArrayEntityFactory(new IntegerTokenValidator());
    this.doubleArrayEntityFactory = new DoubleArrayEntityFactory(new DoubleTokenValidator());
    this.minMaxSearchService = new ArrayMinMaxSearchService();
    this.sumCalculationService = new ArraySumCalculationService();
    this.averageCalculationService = new ArrayAverageCalculationService();
    this.sortServiceFactory = new SortAlgorithmServiceFactory();
  }

  public static void main(String[] args) {
    Application application = new Application();
    application.run();
  }

  public void run() {
    processIntegerArrays();
    processDoubleArrays();
  }

  private void processIntegerArrays() {
    List<String> lines = readDataLines(INTEGER_DATA_FILE);
    for (String line : lines) {
      List<String> tokens = lineParser.parseTokens(line);
      if (tokens.isEmpty()) {
        LOGGER.info("Skipping blank line in {}", INTEGER_DATA_FILE);
        continue;
      }
      handleIntegerLine(tokens, line);
    }
  }

  private void processDoubleArrays() {
    List<String> lines = readDataLines(DOUBLE_DATA_FILE);
    for (String line : lines) {
      List<String> tokens = lineParser.parseTokens(line);
      if (tokens.isEmpty()) {
        LOGGER.info("Skipping blank line in {}", DOUBLE_DATA_FILE);
        continue;
      }
      handleDoubleLine(tokens, line);
    }
  }

  private void handleIntegerLine(List<String> tokens, String sourceLine) {
    try {
      IntegerArrayEntity entity = (IntegerArrayEntity) integerArrayEntityFactory.createArrayEntity(tokens);
      reportIntegerEntity(entity);
    } catch (InvalidArrayDataException exception) {
      LOGGER.warn("Rejected line '{}': {}", sourceLine, exception.getMessage());
    }
  }

  private void handleDoubleLine(List<String> tokens, String sourceLine) {
    try {
      DoubleArrayEntity entity = (DoubleArrayEntity) doubleArrayEntityFactory.createArrayEntity(tokens);
      reportDoubleEntity(entity);
    } catch (InvalidArrayDataException exception) {
      LOGGER.warn("Rejected line '{}': {}", sourceLine, exception.getMessage());
    }
  }

  private void reportIntegerEntity(IntegerArrayEntity entity) {
    int[] values = entity.getElements();
    LOGGER.info("Integer array: {}", entity.elementsAsText());

    Optional<Integer> minimum = minMaxSearchService.findMinimum(values);
    Optional<Integer> maximum = minMaxSearchService.findMaximum(values);
    Optional<Integer> sum = sumCalculationService.calculateSum(values);
    Optional<Double> average = averageCalculationService.calculateAverage(values);
    LOGGER.info("Min={}, Max={}, Sum={}, Average={}", minimum, maximum, sum, average);

    ArraySortService bubbleSortService = sortServiceFactory.createSortService(SortAlgorithm.BUBBLE);
    ArraySortService selectionSortService = sortServiceFactory.createSortService(SortAlgorithm.SELECTION);
    int[] bubbleSorted = bubbleSortService.sort(values);
    int[] selectionSorted = selectionSortService.sort(values);
    LOGGER.info("Bubble sorted: {}", Arrays.toString(bubbleSorted));
    LOGGER.info("Selection sorted: {}", Arrays.toString(selectionSorted));
  }

  private void reportDoubleEntity(DoubleArrayEntity entity) {
    double[] values = entity.getElements();
    LOGGER.info("Double array: {}", entity.elementsAsText());

    Optional<Double> minimum = minMaxSearchService.findMinimum(values);
    Optional<Double> maximum = minMaxSearchService.findMaximum(values);
    Optional<Double> sum = sumCalculationService.calculateSum(values);
    Optional<Double> average = averageCalculationService.calculateAverage(values);
    LOGGER.info("Min={}, Max={}, Sum={}, Average={}", minimum, maximum, sum, average);

    ArraySortService bubbleSortService = sortServiceFactory.createSortService(SortAlgorithm.BUBBLE);
    ArraySortService selectionSortService = sortServiceFactory.createSortService(SortAlgorithm.SELECTION);
    double[] bubbleSorted = bubbleSortService.sort(values);
    double[] selectionSorted = selectionSortService.sort(values);
    LOGGER.info("Bubble sorted: {}", Arrays.toString(bubbleSorted));
    LOGGER.info("Selection sorted: {}", Arrays.toString(selectionSorted));
  }

  private List<String> readDataLines(String filePath) {
    try {
      return fileReader.readLines(filePath);
    } catch (IOException exception) {
      LOGGER.error("Failed to read file {}", filePath, exception);
      return Collections.emptyList();
    }
  }
}
