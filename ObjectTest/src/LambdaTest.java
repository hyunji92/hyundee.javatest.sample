import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * Created by hyunji on 2016. 6. 29..
 */
public class LambdaTest {
    public  static  void printAll(List<Develop> list) {
        list.forEach(System.out::println);
        // list.forEach(() -> System.out::println());
    }
    public static void printDevOderBySkill(List<Develop> list){
        Collections.sort(list, (Develop d1, Develop d2) -> d1.getSkill().compareTo(d2.getSkill()));
        list.forEach(System.out::println);

    }

    public static int sumAll(List<Integer > numbers, Predicate<Integer> p){
        int total= 0;
        for(int number:numbers){
            if (p.test(number)){
                total += number;
            }
        }
        return total;
    }

    public static void main(String argp[]){
        new Thread(new Runnable() {
            @Override
            public void run() {

                System.out.println("java community thread 1 ");
            }
        }).start();

        new Thread(()-> System.out.println("java community thread 1 ")).start();
        List<Develop> dev = new ArrayList<>();
        dev.add(new Develop("java....",10));
        dev.add(new Develop("lambda....",5));
        dev.add(new Develop("c....8....",1));

        Stream<Develop> developStream =
                dev
                    .stream()
                    .peek(d -> d.setCarri(20));
        System.out.println(dev.get(0).getCarri());

        developStream.count();

        printAll(dev);

        System.out.println("----------------------------");

        printDevOderBySkill(dev);

        System.out.println("----------------------------");

        List<Integer> list = Arrays.asList(2,5,7,3,8,10);
        Predicate<Integer> predicate = n->true;
        int s =  sumAll(list, predicate);
        System.out.println(s);
        list.stream();



//        LongStream.range(1, 1000000000000000000L);




    }
}
