package behavior_parameterization_2_4.predicate;

import behavior_parameterization_2_4.Apple;

public class ApplePrintWeightPredicate implements ApplePrintPredicate {
    public String print(Apple apple) {
        return String.valueOf(apple.getWeight());
    }
}
