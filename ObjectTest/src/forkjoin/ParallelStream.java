package forkjoin;

import aroundpattern.AroundPatternTest;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.stream.Stream;

/**
 * Created by hyunji on 2016. 8. 17..
 */
public class ParallelStream {

    /** 무한 스트림을 만들고 인수로 주어진 크기로 스트림을 제한하고, 두 숫자를 더하는 BinaryOperator로 리듀싱 작업을 수행할 수 있다
     *  병렬 스트림이란 각각의 스레드에서 처리할 수 있도록 스트림 요소를 여러 덩어리로 분할한 스트림이다.
     *  병렬 스트림을 이용하면 모든 멀티코어 프로세서가 각각의 청크를 처리하도록 할당할 수 있다. */
    public static long SequentialSum(long n){
        return Stream.iterate(1L, i -> i+1) // 무한 자연수 스트림 생성
                     .limit(n) // n개 이하로 제한
                     .reduce(0L, Long::sum); // 모든 숫자를 더하는 스트림 리듀싱 연산

    }
    public static String inPut(AroundPatternTest.BufferReaderProcessor bufferReaderProcessor) throws IOException {

        try (BufferedReader br = new BufferedReader(new FileReader("data2.txt"))) {
//new InputStreamReader(System.in)
            return bufferReaderProcessor.process(br);
        }
    }

    public static long iterativeSum(long n){
        long result = 0;
        for (long i = 1L; i <= n; i++){
            result +=1;
        }
        return  result;
    }


}
