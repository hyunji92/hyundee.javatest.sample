package dish;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by hyunji on 2016. 7. 10..
 */
public class FaltMapEX {
    public static void printListIntArray(List<int[]> listIntArray) {
        for (int[] numberArray : listIntArray) {
            System.out.print("(");
            for (int i = 0; i < numberArray.length; i++) {
                System.out.print(numberArray[i]);
                if (i != numberArray.length - 1) {
                    System.out.print(",");
                }
            }
            System.out.print(") ");
        }
    }


    public static void main(String[] args) {

        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

        List<Integer> squares =
                numbers.stream()
                        .map(n -> n * n)
                        .collect(Collectors.toList());

        List<Integer> num1 = Arrays.asList(1, 2, 3);
        List<Integer> num2 = Arrays.asList(3, 4);

        List<int[]> test = num2.stream()
                .map(j -> new int[]{1, j}) // Stream<int[]>
                .collect(Collectors.toList()); // List<int[]>


        List<int[]> pairs = num1.stream()
                .flatMap(i -> num2.stream()
                        .map(j -> new int[]{i, j}))
                .collect(Collectors.toList());

        printListIntArray(pairs);

    }
}
