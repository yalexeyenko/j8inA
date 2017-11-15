package behavior_parameterization_2_4.predicate;

import behavior_parameterization_2_4.Apple;

public class AppleFancyFormatterPredicate implements ApplePrintPredicate {

    public String print(Apple apple) {
        return "A " + apple.getColor() + " apple of " + apple.getWeight() + " grams";
    }
}
