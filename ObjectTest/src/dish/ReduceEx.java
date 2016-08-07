package dish;

import java.util.Arrays;
import java.util.List;

/**
 * Created by hyunji on 2016. 7. 10..
 */
public class ReduceEx {

    public static void main(String[] args) {

        List<Integer> nums = Arrays.asList(1,2,3,4,5,6,7,8,9,10);

        int sum;

        sum = nums.stream()
                .reduce(0, (a,b) -> a + b);
        System.out.println(sum);

    }
}
