package collector;

import java.util.*;
import java.util.function.*;
import java.util.stream.Collector;

/**
 * Created by hyunji on 2016. 8. 10..
 */
public class PrimeNumberCollector implements Collector<Integer, // 스트림 요소의 형식
        Map<Boolean, List<Integer>>, // 누적자 형식
        Map<Boolean, List<Integer>> >{ // 수집 연산의 결과 형

    /*public static boolean isPrime(int candidate) {
        return IntStream.rangeClosed(2, candidate-1)
                .limit((long) Math.floor(Math.sqrt((double) candidate)) - 1)
                .noneMatch(i -> candidate % i == 0);
    }*/

    public static boolean isPrime(List<Integer> primes, Integer candidate) {
        double candidateRoot = Math.sqrt((double) candidate);
        //return primes.stream().filter(p -> p < candidateRoot).noneMatch(p -> candidate % p == 0);
        return takeWhile(primes, i -> i <= candidateRoot).stream().noneMatch(i -> candidate % i == 0);
    }

    // 그동안 앞에서 했던 예제도 모여있군요
    // 이건 Lazy가아닌 열심히 일하는 메서드입니다
    public static <A> List<A> takeWhile(List<A> list, Predicate<A> p) {
        int i = 0;
        for (A item : list) {
            if (!p.test(item)) {
                return list.subList(0, i);
            }
            i++;
        }
        return list;
    }

    @Override
    public Supplier<Map<Boolean, List<Integer>>> supplier() {
        // supplier 메서드는 누적자를 만드는 함수를 반환 해야한다.
        return ()-> new HashMap<Boolean, List<Integer>>(){{
            put(true, new ArrayList<Integer>());
            put(false, new ArrayList<Integer>());
            // 누적자로 사용할 맵을 만들면서 true, false 키와 빈리스트로 초기화를 했다
        }};
    }

    @Override
    public BiConsumer<Map<Boolean, List<Integer>>, Integer> accumulator() {
        // 스트림에서 요소를 어떻게 수집할지 경정하는 것은 accumulator메서드. accumulator는 최적화의 핵심 이기도 하다
        // 언제든지 원할때 수집의 과정의 중간 결과, 누적자에 접근할 수 있다
        return (Map<Boolean, List<Integer>> acc , Integer candidate) ->{
            acc.get( isPrime(acc.get(true), candidate))
                    .add(candidate);
        };
        // 지금까지 발견한 소수 리스트 와 소스여부를 확인하고싶은 candidate 를 인수로 isPrime메서드를 호출했다
        // (누적 앱의 true키로 이들 값에 접근할 수 있다)
        // isPrime 의 호출 결과 -> 소스리스트 또는 비소수리스트 중 알맞은 리스트로 candidate를 추가한다

    }

    // 아래는 병렬 수집 과정에서 두 부분 누적자를 합칠 수 있는 메서드를 만든다
    @Override
    public BinaryOperator<Map<Boolean, List<Integer>>> combiner() {
        // BinaryOperator<T> 즉 , 두인수를 받아 같은 형식을 반환하는 함수
        return (Map<Boolean, List<Integer>> map1,
                Map<Boolean, List<Integer>> map2) ->{
                    map1.get(true).addAll(map2.get(true));
                    map2.get(false).addAll(map2.get(false));
            return  map1;
        };
        // 참고 : 이 알고리즘 자체가 순차적이여서 컬렉터를 실제 병렬처리 할 수는 없다
        // 따라서 combiner메서드는 호출될 일이 없으므로 빈 구현으로 남겨둘 수 있다 ( 학습 목적 구현 )
    }

    @Override
    public Function<Map<Boolean, List<Integer>>, Map<Boolean, List<Integer>>> finisher() {
        return Function.identity();
    }

    /**
     * 계속 상기하자 , accumualtor의 형식은 컬렉터 결과 형식과 같아서 변환과정이 필요없다.
     * 따라서 항등 함수 identity를 반환하도록 finisher메서드를 구현한다
     * */
    @Override
    public Set<Characteristics> characteristics() {
        return Collections.unmodifiableSet(EnumSet.of(Characteristics.IDENTITY_FINISH));
    }
}
