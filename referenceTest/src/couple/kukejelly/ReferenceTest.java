package couple.kukejelly;

/**
 * Created by hyunji on 2016. 8. 3..
 */
public class ReferenceTest {
    public static void main(String[] args) {
        int[] intArray = new int[1]; // { 0 }
        int primitiveVar = 0;

        System.out.println("before intArray[0] : " + intArray[0]);
        System.out.println("before primitiveVar : " + primitiveVar);

        referenceInt(intArray);
        int i = primitiveInt(primitiveVar);

        System.out.println("after intArray[0] : " + intArray[0]);
        System.out.println("after primitiveVar : " + primitiveVar);
        System.out.println("after i : " + i);

        Integer integer = new Integer(0);
        System.out.println("Before integer " + integer);

        Integer refInteger = referenceInt(integer);

        System.out.println("After integer " + integer);
        System.out.println("After integer " + refInteger);
    }

    public static void referenceInt(int[] i) {
        i[0]++;
        // i++  |||    i += 1    |||    i = i + 1
        // i[0] ||| i[0] += 1    ||| i[0] = i[0] + 1
    }

    public static Integer referenceInt(Integer integer) {
        // TODO VO객체에서도 불변인지 확인 필요
        // Wrapper
        // Long, Double, Integer, Boolean, Short, Float, Byte, Char
        // long, double, int, boolean, short, float, byte, char
        integer += 1;
        return integer;
    }

    public static int primitiveInt(int i) {
        i++;
        return i;
    }
}
