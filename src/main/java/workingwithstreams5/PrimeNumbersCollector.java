package workingwithstreams5;

import java.util.*;
import java.util.function.*;
import java.util.stream.Collector;

import static java.util.stream.Collector.Characteristics.IDENTITY_FINISH;

public class PrimeNumbersCollector implements Collector<Integer, Map<Boolean, List<Integer>>, Map<Boolean, List<Integer>>> {

    @Override
    public Supplier<Map<Boolean, List<Integer>>> supplier() {
        return () -> new HashMap<Boolean, List<Integer>>() {
            {
                put(true, new ArrayList<>());
                put(false, new ArrayList<>());
            }
        };
    }

    @Override
    public BiConsumer<Map<Boolean, List<Integer>>, Integer> accumulator() {
        return (acc, candidate) -> {
            acc.get(isPrime(acc.get(true), candidate)).add(candidate);
        };
    }

    private Boolean isPrime(List<Integer> primes, Integer candidate) {
        int candidateRoot = (int) Math.sqrt((double)candidate);
        return takeWhile(primes, i -> i <= candidateRoot).stream().noneMatch(p -> candidate % p ==0);
    }

    public <A> List<A> takeWhile(List<A> list, Predicate<A> p) {
        int i = 0;
        for (A j : list) {
            if (!p.test(j)) {
                continue;
            }
            i++;
        }
        return list;
    }

    @Override
    public BinaryOperator<Map<Boolean, List<Integer>>> combiner() {
        return (booleanListMap, booleanListMap2) -> {
            booleanListMap.get(true).addAll(booleanListMap2.get(true));
            booleanListMap.get(false).addAll(booleanListMap2.get(false));
            return booleanListMap;
        };
//        throw new UnsupportedOperationException();
    }

    @Override
    public Function<Map<Boolean, List<Integer>>, Map<Boolean, List<Integer>>> finisher() {
        return Function.identity();
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Collections.unmodifiableSet(EnumSet.of(IDENTITY_FINISH));
    }
}
