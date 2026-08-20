package com.taskarray.repository.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.taskarray.comparator.ElementCountComparator;
import com.taskarray.entity.AbstractNumericArray;
import com.taskarray.entity.IntegerArrayEntity;
import com.taskarray.specification.impl.NameSpecification;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;

class InMemoryArrayEntityRepositoryTest {

  @Test
  void shouldFindEntityByNameAfterAdd() {
    InMemoryArrayEntityRepository repository = InMemoryArrayEntityRepository.getInstance();
    IntegerArrayEntity.Builder builder = new IntegerArrayEntity.Builder();
    builder.addValue(1);
    IntegerArrayEntity entity = builder.withName("repo-find-test").build();

    repository.add(entity);

    Optional<AbstractNumericArray> found = repository.findByName("repo-find-test");
    assertTrue(found.isPresent());
  }

  @Test
  void shouldNotFindEntityAfterRemove() {
    InMemoryArrayEntityRepository repository = InMemoryArrayEntityRepository.getInstance();
    IntegerArrayEntity.Builder builder = new IntegerArrayEntity.Builder();
    builder.addValue(1);
    IntegerArrayEntity entity = builder.withName("repo-remove-test").build();
    repository.add(entity);

    repository.remove("repo-remove-test");

    assertFalse(repository.findByName("repo-remove-test").isPresent());
  }

  @Test
  void shouldFindEntitiesBySpecification() {
    InMemoryArrayEntityRepository repository = InMemoryArrayEntityRepository.getInstance();
    IntegerArrayEntity.Builder builder = new IntegerArrayEntity.Builder();
    builder.addValue(1);
    IntegerArrayEntity entity = builder.withName("repo-spec-test").build();
    repository.add(entity);

    List<AbstractNumericArray> found = repository.findBySpecification(new NameSpecification("repo-spec-test"));

    assertEquals(1, found.size());
  }

  @Test
  void shouldSortEntitiesByComparatorWithoutMutatingStorageOrder() {
    InMemoryArrayEntityRepository repository = InMemoryArrayEntityRepository.getInstance();
    IntegerArrayEntity.Builder largerBuilder = new IntegerArrayEntity.Builder();
    largerBuilder.addValue(1);
    largerBuilder.addValue(2);
    largerBuilder.addValue(3);
    IntegerArrayEntity larger = largerBuilder.withName("repo-sort-larger").build();

    IntegerArrayEntity.Builder smallerBuilder = new IntegerArrayEntity.Builder();
    smallerBuilder.addValue(1);
    IntegerArrayEntity smaller = smallerBuilder.withName("repo-sort-smaller").build();

    repository.add(larger);
    repository.add(smaller);

    List<AbstractNumericArray> sorted = repository.sortedBy(new ElementCountComparator());

    int largerIndex = sorted.indexOf(larger);
    int smallerIndex = sorted.indexOf(smaller);
    assertTrue(smallerIndex < largerIndex);
  }
}
