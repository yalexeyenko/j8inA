package lambdaexpressions.usingfunctionalinterfaces;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class PredicateFilterService {

    public <T> List<T> filter(List<T> list, Predicate<T> predicate) {
        List<T> result = new ArrayList<>();
        list.forEach(item -> {
            if (predicate.test(item)) {
                result.add(item);
            }
        });
        return result;
    }
}
