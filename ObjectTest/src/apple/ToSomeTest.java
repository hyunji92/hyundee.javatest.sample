package apple;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hyunji on 2016. 7. 3..
 */
public class ToSomeTest {

    public  interface Predicate {

        public boolean test(Apple apple);
    }

    // Filter에 정의된 형식으로 형식 추론을 할 수 있다.
    public static List<Apple> filter(List<Apple> inventory, Predicate p){
        System.out.println("filter");
        List<Apple> result = new ArrayList<>();
        for(Apple apple : inventory){
            if(p.test(apple)){
                result.add(apple);
            }
        }
        return  result;
    }



    public static void main(String[] args) {

        List<Apple> inventory = new ArrayList<>();
        Apple a = new Apple();
        a.setColor("blue");
        inventory.add(a);
        Apple a1= new Apple();
        a1.setColor("green");
        inventory.add(a1);
        Apple a2 = new Apple();
        a2.setColor("red");
        inventory.add(a2);
        Apple a3 = new Apple();
        a3.setColor("green");
        inventory.add(a3);
        Apple a4  = new Apple();
        a4.setColor("green");
        inventory.add(a4);

        for(Apple apple : inventory){
            System.out.println(apple.getColor());
        }

        //Predicate predicate = (apple) -> "blue".equals(apple.getColor());
        //풀어서 쓴것 .
        List<Apple> greenApple = filter(inventory, (apple) -> "blue".equals(apple.getColor()));
        // 뒤에 인자 그냥 predicate 가능
        // 반환하고 싶은 것을 그냥 바꿔도 가능!


        for(Apple apple : greenApple){
            System.out.println(apple.getColor());
        }
    }
}
