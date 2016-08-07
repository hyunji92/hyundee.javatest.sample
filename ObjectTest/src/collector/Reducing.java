package collector;

import dish.Dish;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static dish.Dish.menu;

/**
 * Created by hyunji on 2016. 8. 7..
 */
public class Reducing  {

    public static void main(String[] args) {

        // 리듀싱 연산은 모든 스트림요소를 처리해서 값으로 도출하는 작업이다.
        // 함수형 프로그래밍 언어 용어로는 이 과정이 마치 종이를 작은 조각이 될 때까지 반복해서 접는 것과
        // 비슷하다는 의미로 폴드 (fold) 라고 부른다

        List<Integer> numbers = Arrays.asList(3,4,5,1,2);
        //1.
        int sum = numbers.stream().reduce(0, (a, b) -> a + b);
        System.out.println(sum);

        // sum변수의 초기값 0
        // 리스트의 모든 요소를 조합하는 연산 (+)
        // 요소조합으로 새로운 값을 만드는 BinaryOperatior<T>. 위에서는 (a, b) -> a + b 사용
        //2.
        int sum2 = numbers.stream().reduce(0, Integer::sum);
        System.out.println(sum2);

        //3. 초기값 설정을 자꾸 빼먹는다. 신경 쓸것
        int max3 = numbers.stream().reduce(0, (a,b) -> Integer.max(a,b));
        System.out.println(max3);

        //4.
        // *** 초기값을 받지 않도록 오버로드된 reduce도 있다 그러나 이 reduce는 Optional 객체를 반환하다.
        // *** 예를들어 스트림에 아무 요소도 없는 상황이라면 초기값이 없으므로 reduce는 아무 합계를 반환할 수 없다.
        // *** 따라서 합계가 없을 을 가리킬 수 있도록 Optional 로 감싸진 결과를 반환한다.
        // *** Optional<T>클래스는 값의 존재나 부재 여부를 표현해주는 컨테이너 클래스이다
        // *** Optional 은 값이 존재하는지 확인하고 값이 없을 때 어떻게 처리할 것인지 강제하는기능을 제공
        Optional<Integer> min4 = numbers.stream().reduce( Integer::min);
        System.out.println(min4);

        //5.
        Optional<Integer> min5 = numbers.stream().reduce(Integer::min);
        min5.ifPresent(System.out::println);

        int calories = menu.stream()
                .map(Dish::getCalories)
                .reduce(0, Integer::sum);
        System.out.println("haha calories  : " + calories);


    }
}
