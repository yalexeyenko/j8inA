package behavior_parameterization_2_4.service.service;


import behavior_parameterization_2_4.Apple;
import behavior_parameterization_2_4.predicate.*;
import behavior_parameterization_2_4.service.AppleService;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class AppleServiceTest {

    private AppleService appleService;

    @Before
    public void init() {
        appleService = new AppleService();
    }

    @Test
    public void shouldPrintApples() {
        // given
        ArrayList<Apple> apples = new ArrayList<>();
        apples.add(new Apple(100, "green"));
        apples.add(new Apple(150, "yellow"));
        apples.add(new Apple(200, "red"));
        // when
        appleService.prettyPrintApple(apples, new ApplePrintColorPredicate());
        appleService.prettyPrintApple(apples, new ApplePrintWeightPredicate());
        appleService.prettyPrintApple(apples, new AppleSimpleFormatterPredicate());
        appleService.prettyPrintApple(apples, new AppleFancyFormatterPredicate());
    }

    @Test
    public void shouldPrintApplesUsingAnonymousClass() {
        // given
        ArrayList<Apple> apples = new ArrayList<>();
        apples.add(new Apple(100, "green"));
        apples.add(new Apple(150, "yellow"));
        apples.add(new Apple(200, "red"));
        // when
        appleService.prettyPrintApple(apples, new ApplePrintPredicate() {
            @Override
            public String print(Apple apple) {
                return "Using Anonymous Class: A " + apple.getColor() + " apple of " + apple.getWeight() + " grams";
            }
        });
    }

    @Test
    public void shouldPrintApplesUsingLambda() {
        // given
        ArrayList<Apple> apples = new ArrayList<>();
        apples.add(new Apple(100, "green"));
        apples.add(new Apple(150, "yellow"));
        apples.add(new Apple(200, "red"));
        // when
        appleService.prettyPrintApple(apples, (Apple apple)
                -> "Using Lambda: A " + apple.getColor() + " apple of " + apple.getWeight() + " grams");
    }

    @Test
    public void shouldFilterAndPrintRedApples() {
        // given
        ArrayList<Apple> apples = new ArrayList<>();
        apples.add(new Apple(100, "green"));
        apples.add(new Apple(150, "yellow"));
        apples.add(new Apple(200, "red"));
        // when
        List<Apple> redApples = appleService.filter(apples, (Apple apple) -> apple.getColor().equals("red"));
        for (Apple apple : redApples) {
            System.out.println("Abstracting List in filter() method: A " + apple.getColor() + " apple of " + apple.getWeight() + " grams");
        }
    }

    @Test
    public void shouldPrintNumbersLessThen3() {
        // given
        ArrayList<Integer> numbers = new ArrayList<>();
        numbers.add(0);
        numbers.add(1);
        numbers.add(2);
        numbers.add(3);
        numbers.add(4);
        numbers.add(5);
        // when
        List<Integer> filterNumbers = appleService.filter(numbers, (Integer i) -> i >= 0 && i < 3);
        for (Integer i : filterNumbers) {
            System.out.println("Abstracting List in filter() metods: " + i);
        }
    }

    @Test
    public void shouldSortApplesUsingAnonymousComparator() {
        // given
        ArrayList<Apple> apples = new ArrayList<>();
        apples.add(new Apple(150, "green"));
        apples.add(new Apple(100, "yellow"));
        apples.add(new Apple(200, "red"));
        // when
        apples.sort(new Comparator<Apple>() {
            @Override
            public int compare(Apple o1, Apple o2) {
                return o1.compareTo(o2);
            }
        });
        for (Apple apple : apples) {
            System.out.println("Sorted using anonymous comparator. Apple weight: " + apple.getWeight());
        }
    }

    @Test
    public void shouldSortApplesUsingLambdaComparator() {
        // given
        ArrayList<Apple> apples = new ArrayList<>();
        apples.add(new Apple(150, "green"));
        apples.add(new Apple(100, "yellow"));
        apples.add(new Apple(200, "red"));
        // when
        apples.sort((Apple a1, Apple a2) -> a1.compareTo(a2));
        for (Apple apple : apples) {
            System.out.println("Sorted using anonymous comparator. Apple weight: " + apple.getWeight());
        }
    }
}
