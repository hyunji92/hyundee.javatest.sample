package collector;

import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

/**
 * Created by hyunji on 2016. 8. 10..
 */
public class ToListCollector<T> implements Collector<T, List<T>, List<T>> {
    @Override
    public Supplier<List<T>> supplier() {
        return null;
    }

    @Override
    public BiConsumer<List<T>, T> accumulator() {
        return null;
    }

    @Override
    public BinaryOperator<List<T>> combiner() {
        return null;
    }

    @Override
    public Function<List<T>, List<T>> finisher() {
        return null;
    }

    @Override
    public Set<Characteristics> characteristics() {
        // Characteristics - Characteristics 메서드는 컬렉터의 연산을 정의하는 Characteristics형식의 불변 집합을 반환한다 (열거형)
        // Characteristics는
        // 1. 스트림을 병렬로 리듀스할것인지
        // 2. 병렬로 리듀스 한다면 어떤 최적화를 선택해야 할지 힌트를 제공한다
        /**
         * 1. UNODERED - 리듀싱 결과는 스트림 요소의 방문 순서나 누적 순서에 영향을 받지 않는다
         * 2. CONCURRENT - 다중스레드에서 accumulator( 스트림 항목추가 ) 함수를 동시에 호출가능, 이 컬렉터는 스트림의 병렬 리듀싱 수행가능
         *                 컬렉터 플래그에 UNODERED를 함께 설정하지 않았다면 테이터 소스가 정렬되 있지 않는 상황에서 병렬 리듀싱 가능
         * 3. IDENTITY_FINISH - finisher메서드가 반환하는 함수는 단순히 identity를 적용하고 생략도 가능
         *                      따라서 최종결과로 누적자 객체를 바로 사용할 수 있다.
         * */
        return null;
    }
}
