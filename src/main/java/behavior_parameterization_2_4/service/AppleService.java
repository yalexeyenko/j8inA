package behavior_parameterization_2_4.service;

import behavior_parameterization_2_4.Apple;
import behavior_parameterization_2_4.predicate.ApplePrintPredicate;

import java.util.List;

public class AppleService {

    public void prettyPrintApple(List<Apple> apples, ApplePrintPredicate p) {
        for (Apple apple : apples) {
            System.out.println(p.print(apple));
        }
    }
}
