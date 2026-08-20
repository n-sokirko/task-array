package com.taskarray.app;

import com.taskarray.aggregate.AggregateType;
import com.taskarray.aggregate.ArrayAggregateCalculator;
import com.taskarray.aggregate.impl.DoubleStreamAggregateCalculator;
import com.taskarray.comparator.ElementCountComparator;
import com.taskarray.entity.AbstractNumericArray;
import com.taskarray.entity.DoubleArrayEntity;
import com.taskarray.entity.IntegerArrayEntity;
import com.taskarray.exception.InvalidArrayDataException;
import com.taskarray.factory.ArrayEntityFactory;
import com.taskarray.factory.SortServiceFactory;
import com.taskarray.factory.impl.DoubleArrayEntityFactory;
import com.taskarray.factory.impl.IntegerArrayEntityFactory;
import com.taskarray.factory.impl.SortAlgorithmServiceFactory;
import com.taskarray.file.parser.ArrayLineParser;
import com.taskarray.file.parser.impl.DelimiterArrayLineParser;
import com.taskarray.file.reader.ArrayDataFileReader;
import com.taskarray.file.reader.impl.TextArrayDataFileReader;
import com.taskarray.repository.ArrayEntityRepository;
import com.taskarray.repository.impl.InMemoryArrayEntityRepository;
import com.taskarray.service.sort.ArraySortService;
import com.taskarray.service.sort.SortAlgorithm;
import com.taskarray.service.statistics.AverageCalculationService;
import com.taskarray.service.statistics.MinMaxSearchService;
import com.taskarray.service.statistics.SumCalculationService;
import com.taskarray.service.statistics.impl.ArrayAverageCalculationService;
import com.taskarray.service.statistics.impl.ArrayMinMaxSearchService;
import com.taskarray.service.statistics.impl.ArraySumCalculationService;
import com.taskarray.specification.ComparisonOperator;
import com.taskarray.specification.Specification;
import com.taskarray.specification.impl.AggregateValueSpecification;
import com.taskarray.validation.impl.DoubleTokenValidator;
import com.taskarray.validation.impl.IntegerTokenValidator;
import com.taskarray.warehouse.Warehouse;
import com.taskarray.warehouse.impl.InMemoryWarehouse;
import java.io.IOException;
import java.util.ArrayList;
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
  private static final double LARGE_SUM_THRESHOLD = 50.0;

  private final ArrayDataFileReader fileReader;
  private final ArrayLineParser lineParser;
  private final ArrayEntityFactory integerArrayEntityFactory;
  private final ArrayEntityFactory doubleArrayEntityFactory;
  private final MinMaxSearchService minMaxSearchService;
  private final SumCalculationService sumCalculationService;
  private final AverageCalculationService averageCalculationService;
  private final SortServiceFactory sortServiceFactory;
  private final ArrayEntityRepository repository;
  private final Warehouse warehouse;
  private final ArrayAggregateCalculator aggregateCalculator;

  public Application() {
    this.fileReader = new TextArrayDataFileReader();
    this.lineParser = new DelimiterArrayLineParser();
    this.integerArrayEntityFactory = new IntegerArrayEntityFactory(new IntegerTokenValidator());
    this.doubleArrayEntityFactory = new DoubleArrayEntityFactory(new DoubleTokenValidator());
    this.minMaxSearchService = new ArrayMinMaxSearchService();
    this.sumCalculationService = new ArraySumCalculationService();
    this.averageCalculationService = new ArrayAverageCalculationService();
    this.sortServiceFactory = new SortAlgorithmServiceFactory();
    this.repository = InMemoryArrayEntityRepository.getInstance();
    this.warehouse = InMemoryWarehouse.getInstance();
    this.aggregateCalculator = new DoubleStreamAggregateCalculator();
  }

  public static void main(String[] args) {
    Application application = new Application();
    application.run();
  }

  public void run() {
    processIntegerArrays();
    processDoubleArrays();
    demonstrateRepositoryFeatures();
  }

  private void processIntegerArrays() {
    List<String> lines = readDataLines(INTEGER_DATA_FILE);
    for (int index = 0; index < lines.size(); index++) {
      String line = lines.get(index);
      List<String> tokens = lineParser.parseTokens(line);
      if (tokens.isEmpty()) {
        LOGGER.info("Skipping blank line in {}", INTEGER_DATA_FILE);
        continue;
      }
      String name = "int-line-" + (index + 1);
      handleIntegerLine(tokens, line, name);
    }
  }

  private void processDoubleArrays() {
    List<String> lines = readDataLines(DOUBLE_DATA_FILE);
    for (int index = 0; index < lines.size(); index++) {
      String line = lines.get(index);
      List<String> tokens = lineParser.parseTokens(line);
      if (tokens.isEmpty()) {
        LOGGER.info("Skipping blank line in {}", DOUBLE_DATA_FILE);
        continue;
      }
      String name = "double-line-" + (index + 1);
      handleDoubleLine(tokens, line, name);
    }
  }

  private void handleIntegerLine(List<String> tokens, String sourceLine, String name) {
    try {
      IntegerArrayEntity entity = (IntegerArrayEntity) integerArrayEntityFactory.createArrayEntity(tokens, name);
      repository.add(entity);
      reportIntegerEntity(entity);
    } catch (InvalidArrayDataException exception) {
      LOGGER.warn("Rejected line '{}': {}", sourceLine, exception.getMessage());
    }
  }

  private void handleDoubleLine(List<String> tokens, String sourceLine, String name) {
    try {
      DoubleArrayEntity entity = (DoubleArrayEntity) doubleArrayEntityFactory.createArrayEntity(tokens, name);
      repository.add(entity);
      reportDoubleEntity(entity);
    } catch (InvalidArrayDataException exception) {
      LOGGER.warn("Rejected line '{}': {}", sourceLine, exception.getMessage());
    }
  }

  private void reportIntegerEntity(IntegerArrayEntity entity) {
    int[] values = entity.getElements();
    LOGGER.info("Integer array '{}': {}", entity.getName(), entity.elementsAsText());

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
    LOGGER.info("Double array '{}': {}", entity.getName(), entity.elementsAsText());

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

  private void demonstrateRepositoryFeatures() {
    List<AbstractNumericArray> allEntities = repository.findAll();
    LOGGER.info("Repository holds {} arrays", allEntities.size());

    List<AbstractNumericArray> sortedByCount = repository.sortedBy(new ElementCountComparator());
    LOGGER.info("Sorted by element count: {}", namesOf(sortedByCount));

    Specification<AbstractNumericArray> largeSumSpecification = new AggregateValueSpecification(
        AggregateType.SUM, ComparisonOperator.GREATER_THAN, LARGE_SUM_THRESHOLD, aggregateCalculator);
    List<AbstractNumericArray> largeSumMatches = repository.findBySpecification(largeSumSpecification);
    LOGGER.info("Arrays with sum > {}: {}", LARGE_SUM_THRESHOLD, namesOf(largeSumMatches));

    if (allEntities.isEmpty()) {
      return;
    }
    demonstrateMutationAndRemoval(allEntities.get(0));
  }

  private void demonstrateMutationAndRemoval(AbstractNumericArray sample) {
    String name = sample.getName();
    LOGGER.info("Warehouse stats for '{}' before mutation: {}", name, warehouse.getStatistics(name));

    if (sample instanceof IntegerArrayEntity) {
      ((IntegerArrayEntity) sample).setElementAt(0, 999);
    } else if (sample instanceof DoubleArrayEntity) {
      ((DoubleArrayEntity) sample).setElementAt(0, 999.0);
    }
    LOGGER.info("Warehouse stats for '{}' after mutation: {}", name, warehouse.getStatistics(name));

    repository.remove(name);
    LOGGER.info("Warehouse stats for '{}' after removal: {}", name, warehouse.getStatistics(name));
  }

  private List<String> namesOf(List<AbstractNumericArray> entities) {
    List<String> names = new ArrayList<>();
    for (AbstractNumericArray entity : entities) {
      names.add(entity.getName());
    }
    return names;
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
