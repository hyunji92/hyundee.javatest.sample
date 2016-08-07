package collector;

import dish.Dish;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static dish.Dish.menu;
import static java.util.stream.Collectors.groupingBy;

/**
 * Created by hyunji on 2016. 8. 7..
 */
public class GroupEx {

    enum CaloricLevel { DIET, NORMAL, FAT };
    public static void main(String[] args) {

        List<Dish> list = new ArrayList<>();
        Map<Dish.Type, List<Dish>> dishTypeListMap2 = new HashMap<>();
        // Collectors.groupingBy 를 이용해서 쉽게 메뉴를 그륩화 할수 있답니다
        Map<Dish.Type, List<Dish>> dishTypeListMap = menu.stream()
                                                         .collect(Collectors.groupingBy(Dish::getType));
        // 여기서 Dish의 Type이 키가 되겠네요
        // 위처럼 각 요리에서 Dish.type과 일치하는 모든 요리를 추출할 수 있게 해주는것이 groupingBy랍니다
        // groupingBy 메서드로 스트림이 그룹화 되니까 이를 분류 합수라고 한답니다!
        System.out.println("groupingBy 메서드는  : " + dishTypeListMap);

        // 다수준으로 그룹핑 해볼까요
        Map<Dish.Type, Map<CaloricLevel, List<Dish>>> groupDishesByCaloricLevel
                = menu.stream().collect(
                groupingBy(Dish::getType,
                        groupingBy(dish -> {
                            if (dish.getCalories() <= 400) {
                                return CaloricLevel.DIET;

                            } else if (dish.getCalories() <= 700) {
                                return CaloricLevel.NORMAL;

                            } else {
                                return CaloricLevel.FAT;
                            }
                        })));


    }
}
