package forkjoin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.stream.LongStream;

/**
 * Created by hyunji on 2016. 8. 14..
 */
public class ForkJoinSumCalculation extends RecursiveTask<Long> {

    public static final long THRESHOLD = 10_000; // 이 값 이하의 서브태스크는 더 이상 분할할 수 없다
    private final long[] numbers;
    private final int start; // 서브태스크에서 처리할 배열의 초기 위치와 최종 위치
    private final int end;


    public ForkJoinSumCalculation(long[] numbers) {
        this(numbers, 0, numbers.length);
    }

    private ForkJoinSumCalculation(long[] numbers, int start, int end) {
        // 메인 태스크의 서브태스크를 재귀적으로 만들 때 사용할 비공개 생성자.
        this.numbers = numbers;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() { // RecursiveTask의 추상 메서드 오버라이드
        int length = end - start; // 이 태스크에서 더할 배열의 길이
        if (length <= THRESHOLD){
            /** 기준값과 같거나 작으면 순차적으로 결과를 계산한다*//** 기준값과 같거나 작으면 순차적으로 결과를 계산한다*/
            return compureSequentially();
        }

        ForkJoinSumCalculation leftTask = new ForkJoinSumCalculation(numbers, start, start + length / 2);//배열의 첫번째 절반을 더하도록 서브태스크를 생성
        /** forkJoinPool의 다른 스레드로 새로 생성한 테스크를 비동기로 실행한다  leftTask.fork(); */
        leftTask.fork();
        ForkJoinSumCalculation rightTask = new ForkJoinSumCalculation(numbers, start + length / 2 , end);//배열의 나머지 절반을 더하도록 서브태스크를 생성
        Long rightResult = rightTask.compute(); // 두번째 서브태스크 동기 실행한다 / 이때 추가로 분할이 일어날 수 있다
        Long leftResult = leftTask.join();// 첫 번째 서브태스크의 결과를 읽거나 아직 결과가 없으면 기다린다
        return leftResult + rightResult;// 두 서브태스크의 결과를 조합한 값이 이 전체 태스크의 결과
    }
    /** 아래 메서드는 n까지 자연수 덧셈 작업을 병렬로 수행하는 방법을 직관적으로 보여준다
     * 다음 코드 처럼 ForkJoinSumCalculatoin 의 생성자로 원하는 수의 배열을 넘겨 줄 수 있다*/
    public static long forkJoinSum(long n){
        long[] numbers = LongStream.rangeClosed(1, n).toArray();
        // 일반적으로 application은 둘이상의 ForkJoinPool을 사용하지 않는다
        /** 소프트웨어의 필요한 곳에서 언제든 가져다 쓸 수 있도록 ForkJoinPool을 한번만 인스턴스화해서 정적메서드에 싱클던으로 저장한다
         * ForkJoinPool을 만들면서 인수가 없는 디폴트 생성자를 이용했는데, 이는 jvm 에서 이용할 수 있는 모든 프로세서가 자유롭게 pool에
         * 접근할 수 있음을 의미한다
         *
         * 더정확하게는 Runtime.availableProcessors의 반환값으로 pool에 사용할 스레드 수를 결정한다
         * availableProcessors =  '사용할 수 있는 프로세서 ' 라는 이름과는 달리 실제 프로세서 외에 하이퍼 스레딩과 관련된 기상 프로세서도 개수에 포함된다*/
        ForkJoinTask<Long> task = new ForkJoinSumCalculation(numbers);
        return new ForkJoinPool().invoke(task);
    }

    private long compureSequentially() {
    /**더 분할할 수 없을 때 서브태스크의 결과를 계산하는 단한 알고리즘이다. */
        long sum = 0;
        for(int i = start; i < end ; i++ ){
            sum += numbers[i];
        }
        return  sum;
    }

    /** 1. ForJionSumCalculator를 ForkJoinPool로 전달하면  풀의 스레드가ㄹ ForkJoinSumCalculator 릐 Compute 메서드를 실행하면서 작업을 수행한다
     * compute메서드는 병렬로 실행할 수 있을 만큼 테스크의 크기가 충분히 작아졌는지 확인하며 , 아직 태스크의 크기가 크다고 판단되면
     * 숫자배열을 반으로 분할해서 두개의 새로운 ForkJoinSumCalculator로 할당한다
     *  2. 그러면 다시 ForkJionPool 이 새로 생성된 ForkJoinSumCalculator 를 실행한다. 이과정이 재귀적으로 반복된다
     *  3. 반복되면서 주어진 조건에 만족할 때 까지 태스크 분할을 반복한다
     *  4. 각 서브 태스크는 순차적으로 처리되며 포킹 프로세스로 만들어진 이진트리의 태스크를 루트에서 역순으로 방문한다.*/
}
