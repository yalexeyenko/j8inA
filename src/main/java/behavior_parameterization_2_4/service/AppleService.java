package behavior_parameterization_2_4.service;

import behavior_parameterization_2_4.Apple;
import behavior_parameterization_2_4.predicate.ApplePrintPredicate;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class AppleService {

    public void prettyPrintApple(List<Apple> apples, ApplePrintPredicate p) {
        for (Apple apple : apples) {
            System.out.println(p.print(apple));
        }
    }

    public <T> List<T> filter(List<T> list, Predicate<T> predicate) {
        List<T> filteredList = new ArrayList<>();
        for (T t : list) {
            if (predicate.test(t)) {
                filteredList.add(t);
            }
        }
        return filteredList;
    }
}
