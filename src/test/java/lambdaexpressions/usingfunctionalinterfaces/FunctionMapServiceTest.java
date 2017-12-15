package lambdaexpressions.usingfunctionalinterfaces;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class FunctionMapServiceTest {

    private FunctionMapService service;

    @Before
    public void setUp() {
        service = new FunctionMapService();
    }

    @Test
    public void shouldMapListOfStringsToListOfIntegers() {
        // given
        List<String> strings = Arrays.asList("one", "two", "three", "four");
        // when
        List<Integer> result = service.map(strings, String::length);
        // then
        System.out.println(result);
    }
}