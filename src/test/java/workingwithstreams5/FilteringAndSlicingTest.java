package workingwithstreams5;

import org.junit.Test;
import workingwithstreams5.domain.Dish;
import workingwithstreams5.domain.Trader;
import workingwithstreams5.domain.Transaction;
import workingwithstreams5.util.DishUtil;
import workingwithstreams5.util.TransactionUtil;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class FilteringAndSlicingTest {

    @Test
    public void shouldFilterVegetarianDishes() {
        // given
        List<Dish> dishes = DishUtil.getDishes();
        // when
        List<Dish> vegetarianDishes = dishes.stream().filter(Dish::isVegetarian).collect(Collectors.toList());
        // then
        System.out.println(vegetarianDishes);
    }

    @Test
    public void shouldFilterEvenNonRepeatableNumbers() {
        // given
        List<Integer> numbers = Arrays.asList(1, 2, 2, 1, 3, 3, 4);
        // when
        List<Integer> evenNumbers = numbers.stream().filter(i -> i % 2 == 0).distinct().collect(Collectors.toList());
        // then
        System.out.println(evenNumbers);
    }

    @Test
    public void shouldReturnFilteredDishesLimitedBy3Values() {
        // given
        List<Dish> dishes = DishUtil.getDishes();
        // when
        List<Dish> filteredDishes = dishes.stream().filter(d -> d.getCalories() > 300).limit(3).collect(Collectors.toList());
        // then
        System.out.println(filteredDishes);
    }

    @Test
    public void shouldReturnFilteredDishesAndSkipFirst2Elements() {
        // given
        List<Dish> dishes = DishUtil.getDishes();
        // when
        List<Dish> filteredDishes = dishes.stream().filter(d -> d.getCalories() > 300).skip(2).collect(Collectors.toList());
        // then
        System.out.println(filteredDishes);
    }

    @Test
    public void shouldReturnNamesOfTheDishes() {
        // given
        List<Dish> dishes = DishUtil.getDishes();
        // when
        List<String> names = dishes.stream().map(d -> d.getName()).collect(Collectors.toList());
        // then
        System.out.println(names);
    }

    @Test
    public void shouldReturnNamesLengthsOgTheDishes() {
        // given
        List<Dish> dishes = DishUtil.getDishes();
        // when
        List<Integer> lengths = dishes.stream().map(d -> d.getName().length()).collect(Collectors.toList());
        // then
        System.out.println(lengths);
    }

    @Test
    public void shouldReturnDistinctLetters() {
        // given
        List<String> words = Arrays.asList("hello", "world");
        // when
        List<String> letters = words.stream().map(w -> w.split("")).flatMap(Arrays::stream).distinct().collect(Collectors.toList());
        // then
        System.out.println(letters);
    }

    @Test
    public void shouldReturnListOfSquaresOfEachNumber() {
        // given
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        // when
        List<Integer> resultNumbers = numbers.stream().map(n -> n * n).collect(Collectors.toList());
        // then
        System.out.println(resultNumbers);
    }

    @Test
    public void shouldReturnPairsOfNumbers() {
        // given
        List<Integer> numbers1 = Arrays.asList(1, 2, 3);
        List<Integer> numbers2 = Arrays.asList(3, 4);
        // when
        List<int[]> pairs = numbers1.stream().flatMap(n1 -> numbers2.stream().map(n2 -> new int[]{n1, n2})).collect(Collectors.toList());
        // then
        pairs.forEach(r -> {
            System.out.print(r[0]);
            System.out.print(r[1]);
            System.out.println();
        });
    }

    @Test
    public void shouldReturnPairsOfNumbersWhichSumIsDivisibleByThree() {
        // given
        List<Integer> numbers1 = Arrays.asList(1, 2, 3);
        List<Integer> numbers2 = Arrays.asList(3, 4);
        // when
        List<int[]> pairs = numbers1.stream().flatMap(n1 -> numbers2.stream().filter(n2 -> (n1 + n2) % 3 == 0).map(n2 -> new int[]{n1, n2})).collect(Collectors.toList());
        // then
        pairs.forEach(r -> {
            System.out.print(r[0]);
            System.out.print(r[1]);
            System.out.println();
        });
    }

    @Test
    public void shouldCheckIfStreamContainsAnyVegetarianDishes() {
        // given
        List<Dish> dishes = DishUtil.getDishes();
        // when
        boolean containsVegetarian = dishes.stream().anyMatch(Dish::isVegetarian);
        // then
        if (containsVegetarian) {
            System.out.println("The menu contains vegetarian food.");
        }
    }

    @Test
    public void shouldCheckIfAllDishesAreBellow1000Calories() {
        // given
        List<Dish> dishes = DishUtil.getDishes();
        // when
        boolean isBellow1000Calories = dishes.stream().allMatch(d1 -> d1.getCalories() < 1000);
        // then
        if (isBellow1000Calories) {
            System.out.println("All dishes are bellow 1000 calories.");
        }
    }

    @Test
    public void shouldCheckIfAllDishesAreBellow1000Calories2() {
        // given
        List<Dish> dishes = DishUtil.getDishes();
        // when
        boolean isBellow1000Calories = dishes.stream().noneMatch(d1 -> d1.getCalories() >= 1000);
        // then
        if (isBellow1000Calories) {
            System.out.println("All dishes are bellow 1000 calories.");
        }
    }

    @Test
    public void shouldFindAnyVegetarianDish() {
        // given
        List<Dish> dishes = DishUtil.getDishes();
        // when
        dishes.stream().filter(Dish::isVegetarian).findAny().ifPresent(System.out::println);
    }

    @Test
    public void shouldFindFirstSquareDivisibleByThree() {
        // given
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        // when
        numbers.stream().map(n -> n * n).filter(n -> n % 3 == 0).findFirst().ifPresent(System.out::println);
    }

    @Test
    public void shouldSumAllNumbersFromAList() {
        // given
        List<Integer> numbers = Arrays.asList(4, 5, 3, 9);
        // when
        numbers.stream().reduce(Integer::sum).ifPresent(System.out::println);

        int sum2 = 0;
        Integer result = numbers.stream().reduce(sum2, Integer::sum);
        System.out.println(result);
    }

    @Test
    public void shouldFindMaxAndMinValue() {
        // given
        List<Integer> numbers = Arrays.asList(4, 5, 3, 9);
        // then
        numbers.stream().reduce(Integer::max).ifPresent(System.out::println);
        numbers.stream().reduce(Integer::min).ifPresent(System.out::println);
    }

    @Test
    public void shouldCountNumberOfDishes() {
        // given
        List<Dish> dishes = DishUtil.getDishes();
        // then
        dishes.stream().map(d -> 1).reduce(Integer::sum).ifPresent(System.out::println);
        System.out.println(dishes.stream().count());
    }

    @Test
    public void shouldFind2011TransactionsAndSortThem() {
        // given
        TransactionUtil util = new TransactionUtil();
        List<Transaction> transactions = util.getTransactions();
        // when
        List<Transaction> result = transactions.stream().filter(tr -> tr.getYear() == 2011).sorted(Comparator.comparingInt(Transaction::getYear)).collect(Collectors.toList());
        // then
        System.out.println(result);
    }

    @Test
    public void shouldFindUniqueCitiesOfTraders() {
        // given
        TransactionUtil util = new TransactionUtil();
        List<Transaction> transactions = util.getTransactions();
        // when
        List<String> cities = transactions.stream().map(t -> t.getTrader().getCity()).distinct().collect(Collectors.toList());
        // then
        System.out.println(cities);
    }

    @Test
    public void shouldFindTradersFromCambridgeAndSortThemByName() {
        // given
        TransactionUtil util = new TransactionUtil();
        List<Transaction> transactions = util.getTransactions();
        // when
        List<Trader> traders = transactions.stream().map(Transaction::getTrader).distinct()
                .filter(td -> "Cambridge".equals(td.getCity())).sorted(Comparator.comparing(Trader::getName))
                .collect(Collectors.toList());
        // then
        System.out.println(traders);
    }

    @Test
    public void shouldReturnStringOfNamesOfAllTradersSorted() {
        // given
        TransactionUtil util = new TransactionUtil();
        List<Transaction> transactions = util.getTransactions();
        // then
        transactions.stream()
                .map(Transaction::getTrader)
                .sorted(Comparator.comparing(Trader::getName))
                .distinct()
                .map(Trader::getName)
                .reduce(String::concat).ifPresent(System.out::println);
    }

    @Test
    public void shouldCheckIfAnyTradersFromMilan() {
        // given
        TransactionUtil util = new TransactionUtil();
        List<Transaction> transactions = util.getTransactions();
        // then
        boolean isFromMilan = transactions.stream()
                .anyMatch(t -> "Milan".equals(t.getTrader().getCity()));
        System.out.println(isFromMilan);
    }

    @Test
    public void shouldPrintTransactionValuesOfTradersFromCambridge() {
        // given
        TransactionUtil util = new TransactionUtil();
        List<Transaction> transactions = util.getTransactions();
        // when
        List<Integer> values = transactions.stream()
                .filter(t -> "Cambridge".equals(t.getTrader().getCity()))
                .map(Transaction::getValue)
                .collect(Collectors.toList());
        // then
        System.out.println(values);
    }

    @Test
    public void shouldFindMaxValueOfAllTransactions() {
        // given
        TransactionUtil util = new TransactionUtil();
        List<Transaction> transactions = util.getTransactions();
        // then
        transactions.stream().map(Transaction::getValue).reduce(Integer::max).ifPresent(System.out::println);
    }

    @Test
    public void shouldFindMinValueOfAllTransactions() {
        // given
        TransactionUtil util = new TransactionUtil();
        List<Transaction> transactions = util.getTransactions();
        // then
        transactions.stream().map(Transaction::getValue).reduce(Integer::min).ifPresent(System.out::println);
    }

    @Test
    public void shouldCalculateCaloriesOfTheMenu() {
        // given
        List<Dish> dishes = DishUtil.getDishes();
        // when
        int sum = dishes.stream().mapToInt(Dish::getCalories).sum();
        // then
        System.out.println(sum);
    }

    @Test
    public void shouldReturnZeroWhenListIsEmpty() {
        // given
        List<Dish> dishes = new ArrayList<>();
        // when
        int sum = dishes.stream().mapToInt(Dish::getCalories).sum();
        // then
        System.out.println(sum);
    }

    @Test
    public void shouldReturnMaxCalories() {
        // given
        List<Dish> dishes = DishUtil.getDishes();
        // when
        dishes.stream().mapToInt(Dish::getCalories).max().ifPresent(System.out::println);
    }

    @Test
    public void shouldReturnDefaultValueIfListIsEmpty() {
        // given
        List<Dish> dishes = new ArrayList<>();
        // when
        int max = dishes.stream().mapToInt(Dish::getCalories).max().orElse(1);
        // then
        System.out.println(max);
    }

    @Test
    public void shouldReturnNumberOfEvenNumbersFrom1To100Inclusively() {
        // when
        long sum = IntStream.rangeClosed(1, 100).filter(n -> n % 2 == 0).count();
        // then
        System.out.println(sum);
    }

    @Test
    public void shouldReturnRangesOfPythagoreanTriples() {
        // then
        IntStream.rangeClosed(1, 100).boxed()
                .flatMap(a -> IntStream.rangeClosed(a, 100).mapToObj(b -> new double[]{a, b, Math.sqrt(a * a + b * b)}))
                .filter(t -> t[2] % 1 == 0)
                .forEach(t -> System.out.printf("%g %g %g\n", t[0], t[1], t[2]));
    }

    @Test
    public void shouldConvertStringsToUppercase() {
        // given
        Stream<String> stream = Stream.of("Java 8", "streams", "in", "Action");
        // then
        stream.map(String::toUpperCase).forEach(System.out::println);
    }

    @Test
    public void shouldSumNumbersInArray() {
        // given
        int[] numbers = {2,3,5,7,11,13};
        // when
        int sum = Arrays.stream(numbers).sum();
        // then
        System.out.println(sum);
    }

    @Test
    public void shouldFindNumberOfUniqueWords() {
        // given
        long count = 0;
        try (Stream<String> lines = Files.lines(Paths.get("F:/WS/IdeaWorkspace/j8inA/src/main/resources/data2.txt"), Charset.defaultCharset())) {
            count = lines.flatMap(l -> Arrays.stream(l.split(" "))).distinct().count();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // then
        System.out.println(count);
    }

    @Test
    public void shouldPrint20FibonacciNumbers() {
        // then
        Stream.iterate(new int[]{0, 1}, t -> new int[]{t[1], t[0] + t[1]}).limit(20)
                .forEach(t -> System.out.printf("(%d, %5d)\n", t[0], t[1]));
        Stream.iterate(new int[]{0, 1}, t -> new int[]{t[1], t[0] + t[1]}).limit(20).map(t -> t[0])
                .forEach(System.out::println);
    }

    @Test
    public void shouldPrint5RandomDoubleNumbers() {
        // then
        Stream.generate(Math::random).limit(5).forEach(System.out::println);
    }
}