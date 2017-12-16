package lambdaexpressions.methodreferences;

import behavior_parameterization_2_4.Apple;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;

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
}