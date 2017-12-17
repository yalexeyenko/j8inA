package lambdaexpressions.usingfunctionalinterfaces;

import behavior_parameterization_2_4.Apple;
import lambdaexpressions.usingfunctionalinterfaces.domain.Letter;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

import static java.util.Comparator.comparing;

public class UsingDefaultMethodsTest {

    private PredicateFilterService service;

    @Before
    public void setUp() {
        service = new PredicateFilterService();
    }

    @Test
    public void shouldSortApplesInReverseOrder() {
        // given
        List<Apple> apples = Arrays.asList(new Apple(150), new Apple(200), new Apple(100));
        // when
        apples.sort(comparing(Apple::getWeight).reversed());
        // then
        System.out.println(apples);
    }

    @Test
    public void shouldSortApplesByWeightReversedOrderAndThenByColor() {
        // given
        List<Apple> apples = Arrays.asList(new Apple(150, "red"), new Apple(200, "yellow"), new Apple(150, "green"));
        // when
        apples.sort(comparing(Apple::getWeight).reversed().thenComparing(Apple::getColor));
        // then
        System.out.println(apples);
    }

    /*
    * Composing Predicates
    * */
    @Test
    public void shouldFilterApplesUsingComposedPredicates() {
        // given
        List<Apple> apples = Arrays.asList(new Apple(150, "red"), new Apple(200, "yellow"), new Apple(150, "green"));
        Predicate<Apple> redApple = apple -> apple.getColor().equals("red");
        Predicate<Apple> notRedApple = redApple.negate();
        Predicate<Apple> redAppleOver150orYellow = redApple.and(a -> a.getWeight() > 150).or(a -> "yellow".equals(a.getColor()));
        // when
        List<Apple> redApples = service.filter(apples, redApple);
        List<Apple> notRedApples = service.filter(apples, notRedApple);
        List<Apple> redApplesOver150orYellow = service.filter(apples, redAppleOver150orYellow);
        // then
        System.out.println(redApples);
        System.out.println(notRedApples);
        System.out.println(redApplesOver150orYellow);
    }

    /*
    * Composing Functions
    * */
    @Test
    public void shouldIncrementAndMultiplyNumber() {
        // given
        Function<Integer, Integer> incrFunction = i -> i + 1;
        Function<Integer, Integer> multiFunction = j -> j * 2;
        Function<Integer, Integer> incrAndMultiFunction = incrFunction.andThen(multiFunction);
        Function<Integer, Integer> incrAndMultiComposeFunction = multiFunction.compose(incrFunction);
        // when
        Integer i = incrFunction.apply(2);
        Integer j = multiFunction.apply(3);
        Integer k = incrAndMultiFunction.apply(2);
        Integer l = incrAndMultiComposeFunction.apply(2);
        // then
        System.out.println(i);
        System.out.println(j);
        System.out.println("using andThen");
        System.out.println(k);
        System.out.println("using compose");
        System.out.println(k);
    }

    /*
    * Creating transformation pipeline using andThen
    * */
    @Test
    public void shouldReturnTextTransformationPipeline() {
        // given
        Function<String, String> addHeaderFunction = Letter::addHeader;
        String text = "Hello everyone! Let's study lamda expressions.";
        Function<String, String> transformationPipelineFunction = addHeaderFunction.andThen(Letter::checkSpelling).andThen(Letter::addFooter);
        // when
        String letter = transformationPipelineFunction.apply(text);
        // then
        System.out.println(letter);
    }
}
