package lambdaexpressions.methodreferences;

import behavior_parameterization_2_4.Apple;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class ConstructorReferenceService {

    private static Map<String, Function<Integer, Apple>> fruits = new HashMap<>();
    static {
        fruits.put("apple", Apple::new);
    }

    public List<Apple> map(List<Integer> weights, Function<Integer, Apple> function) {
        List<Apple> result = new ArrayList<>();
        for (Integer weight : weights) {
            result.add(new Apple(weight));
        }
        return result;
    }

    public Apple giveMeFruitWitWeight(String fruit, Integer weight) {
        return fruits.get(fruit.toLowerCase()).apply(weight);
    }
}
