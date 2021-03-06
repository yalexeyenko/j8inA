package lambdaexpressions.usingfunctionalinterfaces;

import java.util.List;
import java.util.function.Consumer;

public class ConsumerForeachService {

    public <T> void foreach(List<T> list, Consumer consumer) {
        for (T item : list) {
            consumer.accept(item);
        }
    }
}