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
        BiFunction<String ,Integer, Apple > c3 = Apple::new;
        Apple a3 = c3.apply("green",100);
        System.out.println(a3.getColor() +  " - " +a3.getWeight());



    }

}
