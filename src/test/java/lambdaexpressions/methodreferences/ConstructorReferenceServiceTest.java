package lambdaexpressions.methodreferences;

import behavior_parameterization_2_4.Apple;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiFunction;

import static java.util.Comparator.comparing;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;

public class ConstructorReferenceServiceTest {

    private ConstructorReferenceService service;

    @Before
    public void setUp() {
        service = new ConstructorReferenceService();
    }

    @Test
    public void shouldReturnApplesWithWeight() {
        // given
        List<Integer> weights = Arrays.asList(100, 150, 200);
        // when
        List<Apple> result = service.map(weights, Apple::new);
        // then
        assertNotNull(result);
        assertThat(result.size(), is(3));
        System.out.println(result);
    }

    @Test
    public void shouldReturnAppleWithWeightAndColor() {
        // given
        BiFunction<Integer, String, Apple> function = Apple::new;
        // when
        Apple a1 = function.apply(150, "green");
        // then
        assertNotNull(a1);
        System.out.println(a1);
    }

    @Test
    public void shouldReturnFruitFromMap(){
        // when
        Apple result = service.giveMeFruitWitWeight("apple", 200);
        // then
        assertNotNull(result);
        System.out.println(result);
    }

    @Test
    public void shouldSortApples() {
        // given
        List<Apple> apples1 = Arrays.asList(new Apple(150), new Apple(200), new Apple(100));
        List<Apple> apples2 = Arrays.asList(new Apple(150), new Apple(200), new Apple(100));
        List<Apple> apples3 = Arrays.asList(new Apple(150), new Apple(200), new Apple(100));
        // when
        apples1.sort((a1, a2) -> a1.compareTo(a2));
        apples2.sort(comparing(a1 -> a1.getWeight()));
        apples3.sort(comparing(Apple::getWeight));
        // then
        System.out.println(apples1);
        System.out.println(apples2);
        System.out.println(apples3);
    }
}