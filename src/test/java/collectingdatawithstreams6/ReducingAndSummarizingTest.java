package collectingdatawithstreams6;

import org.junit.Test;
import workingwithstreams5.PrimeNumbersCollector;
import workingwithstreams5.domain.CaloricLevel;
import workingwithstreams5.domain.Dish;
import workingwithstreams5.util.DishUtil;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ReducingAndSummarizingTest {

    @Test
    public void shouldFindDishWithMaximumCalories() {
        // given
        List<Dish> dishes = DishUtil.getDishes();
        // then
        dishes.stream().collect(Collectors.maxBy((Comparator.comparingInt(Dish::getCalories))))
                .ifPresent(d -> System.out.println(d.getCalories()));
    }

    @Test
    public void shouldFindSumOfCalories() {
        // given
        List<Dish> dishes = DishUtil.getDishes();
        // when
        Integer sum = dishes.stream().collect(Collectors.summingInt(Dish::getCalories));
        // then
        System.out.println(sum);
    }

    @Test
    public void shouldReturnZverageCalories() {
        // given
        List<Dish> dishes = DishUtil.getDishes();
        // when
        Double average = dishes.stream().collect(Collectors.averagingInt(Dish::getCalories));
        // then
        System.out.println(average);
    }

    @Test
    public void shouldGetSummaryStatistics() {
        // given
        List<Dish> dishes = DishUtil.getDishes();
        // when
        IntSummaryStatistics stat = dishes.stream().collect(Collectors.summarizingInt(Dish::getCalories));
        // then
        System.out.println(stat);
    }

    @Test
    public void shouldConcatenateAllDishesNames() {
        // given
        List<Dish> dishes = DishUtil.getDishes();
        // when
        String result = dishes.stream().map(Dish::getName).collect(Collectors.joining());
        // then
        System.out.println(result);
    }

    @Test
    public void shouldConcatenateAllDishesNamesWithDelimiter() {
        // given
        List<Dish> dishes = DishUtil.getDishes();
        // when
        String result = dishes.stream().map(Dish::getName).collect(Collectors.joining(",  "));
        // then
        System.out.println(result);
    }

    @Test
    public void shouldCalculateTotalCalories() { // using reduce
        // given
        List<Dish> dishes = DishUtil.getDishes();
        // when
        int value = 0;
        Integer sum = dishes.stream().collect(Collectors.reducing(value, Dish::getCalories, (v1, v2) -> v1 + v2));
        // then
        System.out.println(sum);
    }

    @Test
    public void shouldFindHighestCaloryDish() { // using reduce
        // given
        List<Dish> dishes = DishUtil.getDishes();
        // then
        int value = 0;
        dishes.stream().collect(Collectors.reducing((d1, d2) -> d1.getCalories() > d2.getCalories() ? d1 : d2))
                .ifPresent(System.out::println);
    }

    @Test
    public void shouldGroupDishesByType() {
        // given
        List<Dish> dishes = DishUtil.getDishes();
        // when
        Map<Dish.DishType, List<Dish>> result = dishes.stream().collect(Collectors.groupingBy(Dish::getType));
        // then
        System.out.println(result);
    }

    @Test
    public void shouldGroupDishesByCalories() {
        // given
        List<Dish> dishes = DishUtil.getDishes();
        // when
        Map<CaloricLevel, List<Dish>> result = dishes.stream().collect(Collectors.groupingBy(dish -> {
            if (dish.getCalories() <= 400) {
                return CaloricLevel.DIET;
            } else if (dish.getCalories() <= 700) {
                return CaloricLevel.NORMAL;
            } else {
                return CaloricLevel.FAT;
            }
        }));
        // then
        System.out.println(result);
    }

    @Test
    public void shouldGroupDishesByTypeAndCalories() {
        // given
        List<Dish> dishes = DishUtil.getDishes();
        // when
        Map<Dish.DishType, Map<CaloricLevel, List<Dish>>> result = dishes.stream().collect(Collectors.groupingBy(Dish::getType, Collectors.groupingBy(dish -> {
            if (dish.getCalories() <= 400) {
                return CaloricLevel.DIET;
            } else if (dish.getCalories() <= 700) {
                return CaloricLevel.NORMAL;
            } else {
                return CaloricLevel.FAT;
            }
        })));
        // then
        System.out.println(result);
    }

    @Test
    public void shouldCountTheNumberOfDishesForEachType() {
        // given
        List<Dish> dishes = DishUtil.getDishes();
        // when
        Map<Dish.DishType, Long> result = dishes.stream().collect(Collectors.groupingBy(Dish::getType, Collectors.counting()));
        // then
        System.out.println(result);
    }

    @Test
    public void shouldFindHighestCaloryDishAndGroupByType() {
        // given
        List<Dish> dishes = DishUtil.getDishes();
        // when
        Map<Dish.DishType, Optional<Dish>> result = dishes.stream()
                .collect(Collectors.groupingBy(Dish::getType, Collectors.maxBy(Comparator.comparingInt(Dish::getCalories))));
        // then
        System.out.println(result);
    }

    @Test
    public void shouldFindHighestCaloryDishesAndGroupByType() {
        // given
        List<Dish> dishes = DishUtil.getDishes();
        // when
        Map<Dish.DishType, Dish> result = dishes.stream()
                .collect(Collectors.groupingBy(Dish::getType, Collectors.collectingAndThen(Collectors.maxBy(Comparator.comparingInt(Dish::getCalories)), Optional::get)));
        // then
        System.out.println(result);
    }

    @Test
    public void shouldSumDishesCaloriesAndGroupByType() {
        // given
        List<Dish> dishes = DishUtil.getDishes();
        // when
        Map<Dish.DishType, Integer> result = dishes.stream()
                .collect(Collectors.groupingBy(Dish::getType, Collectors.summingInt(Dish::getCalories)));
        // then
        System.out.println(result);
    }

    @Test
    public void shouldGetCaloricLevelsFromMenuAndGroupByType() {
        // given
        List<Dish> dishes = DishUtil.getDishes();
        // when
        Map<Dish.DishType, Set<CaloricLevel>> result = dishes.stream().collect(Collectors.groupingBy(Dish::getType, Collectors.mapping(dish -> {
            if (dish.getCalories() <= 400) {
                return CaloricLevel.DIET;
            } else if (dish.getCalories() <= 700) {
                return CaloricLevel.NORMAL;
            } else {
                return CaloricLevel.FAT;
            }
        }, Collectors.toSet())));
        // then
        System.out.println(result);
    }

    @Test
    public void shouldGetCaloricLevelsFromMenuAndGroupByTypeAndCollectToHasShet() {
        // given
        List<Dish> dishes = DishUtil.getDishes();
        // when
        Map<Dish.DishType, Set<CaloricLevel>> result = dishes.stream().collect(Collectors.groupingBy(Dish::getType, Collectors.mapping(dish -> {
            if (dish.getCalories() <= 400) {
                return CaloricLevel.DIET;
            } else if (dish.getCalories() <= 700) {
                return CaloricLevel.NORMAL;
            } else {
                return CaloricLevel.FAT;
            }
        }, Collectors.toCollection(HashSet::new))));
        // then
        System.out.println(result);
    }

    @Test
    public void shouldPartitionDishesOnVegetarianAndNonVegetarian() {
        // given
        List<Dish> dishes = DishUtil.getDishes();
        // when
        Map<Boolean, List<Dish>> result = dishes.stream().collect(Collectors.partitioningBy(Dish::isVegetarian));
        // then
        System.out.println(result);
    }

    @Test
    public void shouldPartitionDishesOnVegetarianAndNonVegetarianAndGroupByType() {
        // given
        List<Dish> dishes = DishUtil.getDishes();
        // when
        Map<Boolean, Map<Dish.DishType, List<Dish>>> result = dishes.stream()
                .collect(Collectors.partitioningBy(Dish::isVegetarian, Collectors.groupingBy(Dish::getType)));
        // then
        System.out.println(result);
    }

    @Test
    public void shouldFindMostCaloricDishesAmongVegetarianAndNonVegetarian() {
        // given
        List<Dish> dishes = DishUtil.getDishes();
        // when
        Map<Boolean, Dish> result = dishes.stream()
                .collect(Collectors.partitioningBy(Dish::isVegetarian, Collectors.collectingAndThen(Collectors.maxBy(Comparator.comparingInt(Dish::getCalories)), Optional::get)));
        // then
        System.out.println(result);
    }

    private boolean isPrime(int candidate) {
        int candidateRoot = (int) Math.sqrt((double) candidate);
        return IntStream.rangeClosed(2, candidateRoot).noneMatch(i -> candidate % i == 0);
    }

    @Test
    public void shouldPartitionOnPrimeAndNonPrimeNumbers() {
        // given
        int candidate = 100;
        // when
        Map<Boolean, List<Integer>> result = IntStream.rangeClosed(2, candidate).boxed().collect(Collectors.partitioningBy(this::isPrime));
        // then
        System.out.println(result);
    }

    @Test
    public void shouldPartitionOnPrimeAndNonPrimeNumbersUsingCustomCollector() {
        // given
        int candidate = 100;
        // when
        Map<Boolean, List<Integer>> result = IntStream.rangeClosed(2, candidate).boxed().collect(new PrimeNumbersCollector());
        // then
        System.out.println(result);
    }

}
