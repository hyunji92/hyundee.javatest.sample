package apple;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Supplier;

/**
 * Created by hyunji on 2016. 7. 3..
 */
public class MethodReference {

    public static void main(String[] args) {

        Supplier<Apple> c1 = ()-> new Apple();
        Apple a1 = c1.get();
        a1.setColor("green");
        System.out.println(a1.getColor());

        //T - 1인자
        //U - 2인자
        //R -  반환값
//        BiFunction<String ,Integer, Apple > c3 = (color, weight) -> new Apple(color, weight);
        BiFunction<String ,Integer, Apple> c3 = Apple::new;
        Apple a3 = c3.apply("green",100);
        System.out.println(a3.getColor() +  " - " +a3.getWeight());

        List<Apple> inventory = new ArrayList<>() ;
        System.out.println(" ==================================== ");
        prettyPrintApple(inventory , new  AppleFancyFormatter());
        prettyPrintApple(inventory, new AppleSimpleFormatter());


        inventory.sort(Comparator.comparing());

    }

    public interface AppleFormatter{
        String accept(Apple a);
    }

    public static class AppleFancyFormatter implements AppleFormatter {

        @Override
        public String accept(Apple apple) {
            String charateristic =  apple.getWeight() > 150 ? "heavy" : "light" ;

            return "A  " +  charateristic +
                    "  " +  apple.getColor() + "  apple";
        }
    }

    /*public class AppleSimpleFormatter implements  AppleFormatter{

        @Override
        public String accept(Apple apple) {
            return "An apple of  " + apple.getWeight() + "g ";
        }
    }*/

    public static void prettyPrintApple(List<Apple> inventory , AppleFormatter appleFormatter){
        for (Apple apple : inventory){
            //for 문을 돌고  appleFormatter를 출력할 수 있도록 한다.
            String output = appleFormatter.accept(apple);
            System.out.println(output);
        }
    }
}
