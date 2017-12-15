package lambdaexpressions.usingfunctionalinterfaces;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class ConsumerForeachServiceTest {

    private ConsumerForeachService service;

    @Before
    public void setUp() {
        service = new ConsumerForeachService();
    }

    @Test
    public void shouldPerformActionOnEachListMember() {
        // given
        List<String> strings = Arrays.asList("one", "two", "three");
        // when
        service.foreach(strings, System.out::println);
    }
}