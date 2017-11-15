package behavior_parameterization_2_4.predicate;

import behavior_parameterization_2_4.Apple;

public class ApplePrintColorPredicate implements ApplePrintPredicate {
    public String print(Apple apple) {
        return apple.getColor();
    }
}
