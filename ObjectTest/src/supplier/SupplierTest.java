package supplier;

/**
 * Created by hyunji on 2016. 8. 7..
 */

import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

import static supplier.ABC.itIsTooExpensive;

public class SupplierTest {

    public static void main(String[] args) {
        final Supplier<String> helloSupplier = () -> "Hello ";

        long start = System.currentTimeMillis();

        for(int i = 0; i < 20; i++) {
            printStringWhenIndexIsUnderFive(i, itIsTooExpensive());
            // itIsTooExpensive() - 무조건 메소드를 타게되어 시간이 걸리게 된다.
            //printStringWhenIndexIsUnderFive(i, () -> itIsTooExpensive());
            // () -> itIsTooExpensive() 이렇게 하면 Supplier를 넘겨주며,
            // get 메소드를 호출할 때 lazy 하게 해당함수가 호출된다.
        }

        System.out.println("It tooks " + (System.currentTimeMillis() - start) / 1000 + " seconds.");

        System.out.println(helloSupplier.get() + "world");
    }

    public static void printStringWhenIndexIsUnderFive(int index, Supplier<String> supplier) {
        if(index < 5) {
            System.out.println(supplier.get());
        } else {
            System.out.println("abababababaaba");
        }
    }

    public static void printStringWhenIndexIsUnderFive(int index, String someString) {
        if(index < 5) {
            System.out.println(someString);
        } else {
            System.out.println("abababababaaba");
        }
    }


}

class ABC {

    // public static void main() {
    //    SupplierTest.printStringWhenIndexIsUnderFive(1, () -> itIsTooExpensive());
    //}

    public static String itIsTooExpensive() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return "Kuke";
    }

}