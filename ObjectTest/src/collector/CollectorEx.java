package collector;

import dish.Dish;

import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.Optional;
import java.util.stream.Collectors;

import static dish.Dish.menu;

/**
 * Created by hyunji on 2016. 8. 7..
 */
public class CollectorEx  {

    // collector 란
    // collector 메서드로 collector인터페이스 구현
    // *** Collector 인터페이스구현은 스트림은 요소를 어떤 식으로 도출할지 지정한다
    // *** 스트림에 collect를 호출하면 스트림의 요소에 컬렉터로 파라미터화된 리듀싱 연산이 수행된다

    public static void main(String[] args) {
        // List<Transaction> transactions = transactionStream.collect(Collectors.toList());
        // collector에서는 리듀싱 연산을 이요해서 스트림의 각 요소를 방문하면서 컬렉터가 작업을 처리한다
        // Collectors 유틸 클래스는 자주사용되는 컬렉터 인스턴스를 생성쉽게 정적팩토리 메서드 제공  - Collectors.toList()
        // groupingBy 도 Collectors클래스에서 제공하는 팩토리 메서드
        /**
         * Stream.Collect메서드의 인수 Collector로 스트림의 항목을 컬렉션으로 재구성할 수있다
         * */
        long howManyDish = menu.stream()
                               .collect(Collectors.counting());
        System.out.println("디쉬는 얼마나 많을까용 : " + howManyDish);

        // 요것을 다시 생략
        long howManyDish2 = menu.stream().count();
        System.out.println("디쉬가 쉽게 나와용  : " + howManyDish2);

        // 스트림값에서 최댓값과 최솟값
        // *** 요소를 비교하는데 사용한 Compator를 인수로 받는다
        // *** 칼로리로 요리를 비교해 보자
        Comparator<Dish> dishComparatorCalories = Comparator.comparingInt(Dish::getCalories); //Comparator 구현
        Dish mostCaloriesDish = menu.stream()
                                              .collect(Collectors.maxBy(dishComparatorCalories)).get(); // Collect에 전달

        System.out.println(mostCaloriesDish);
        /**  Optional<Dish> 는 무슨 역할을 수행할까용 ( menu ) */
        // 스트림에 있는 객체의 숫자 필드의 합계나 평균등을 반환하는 연산에도 리듀싱 기능이 사용된다 = 요약 연산이라 한다

        /** 요약연산 */
        int totalCalories =  menu.stream()
                                 .collect(Collectors.summingInt(Dish::getCalories));
        System.out.println("칼로리 폭탄 !!! :  " + totalCalories);

        // 평균도 해볼까요
        double totalAverage = menu.stream()
                                  .collect(Collectors.averagingInt(Dish::getCalories));
        System.out.println("칼로리 폭탄 평균 !!! : " + totalAverage);

        // 두개이상의 계산도 해볼수 있데요
        IntSummaryStatistics  menuStatistics = menu.stream()
                                                   .collect(Collectors.summarizingInt(Dish::getCalories));
        // 이렇게 하면 IntSummaryStatistics 클래스로 menu의 모든 정보가 수집된데요

        // joining은문자열 연결이고 // 맵으로나온 메뉴 이름들을 보여주겠쭁
        String shortMenu = menu.stream()
                                .map(Dish::getName)
                                .collect(Collectors.joining(",   "));
        /** 범용 리듀싱 요약 연사도 있습니다 . - 범용이뭘까용 */
        //[명사] 여러 분야나 용도로 널리 쓰는 것. - 네이버 국어사전 이렇다고 하네요
        // 위에 totalCalories를 다시 해볼까봐요
        int totalCalories2 =  menu.stream()
                                    .collect(Collectors.reducing(
                                    0, Dish::getCalories , (i,j) -> i + j ));
        System.out.println("범용 요약 리듀싱 연사으로 한 total계산은 이렇습니다 : " + totalCalories2);
        // 리듀싱 첫번째 인수는 연산의 초기값이거나 스트림에 인수가 없을때 반환값 이랍니다
        // 자그럼 meun가 없을 수도 있으니까 Optional 써볼까용

        Optional<Dish> mostCaloriesDish2 = menu.stream()
                                                .collect(Collectors
                                                        .reducing((dish1 ,dish2) -> dish1.getCalories() > dish2.getCalories() ?dish1 :dish2));
        // 딱봐도 dish1, dish2 비교해 나가면서 큰 칼로리 출력 할꺼 같죠?
        System.out.println("칼로리 폭탄을 다시한번 알아봅니다  : " + mostCaloriesDish2);

        // 같은 연산도 다양한 방식으로 수행가능?
        int totalCalories3 = menu.stream()
                                    .collect(Collectors.reducing(0, // 0 - 초깃값
                                            Dish::getCalories, // 변환함수
                                            Integer::sum)); // 합계함수
        // 1. 누적자를 초기값으로 초기화 2. 변환함수를 적용한 결과 숫자  3. 합계합수를 이용해서 각요소에 반복적으로 조합합니다


    }
}
