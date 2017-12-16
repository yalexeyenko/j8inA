package lambdaexpressions.usingfunctionalinterfaces;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;

public class PredicateFilterServiceTest {

    private PredicateFilterService service;

    @Before
    public void setUp() {
        service = new PredicateFilterService();
    }

    @Test
    public void shouldFilterStrings() {
        // given
        List<String> strings = Arrays.asList("tomato", "potato", "tobacco", "", "");
        // when
        List<String> result = service.filter(strings, item -> !item.isEmpty());
        // then
        assertNotNull(result);
        assertThat(result.size(), is(3));
    }
}