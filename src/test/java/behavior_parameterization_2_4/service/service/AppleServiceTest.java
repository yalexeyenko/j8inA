package behavior_parameterization_2_4.service.service;


import behavior_parameterization_2_4.Apple;
import behavior_parameterization_2_4.predicate.AppleFancyFormatterPredicate;
import behavior_parameterization_2_4.predicate.ApplePrintColorPredicate;
import behavior_parameterization_2_4.predicate.ApplePrintWeightPredicate;
import behavior_parameterization_2_4.predicate.AppleSimpleFormatterPredicate;
import behavior_parameterization_2_4.service.AppleService;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

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
}
