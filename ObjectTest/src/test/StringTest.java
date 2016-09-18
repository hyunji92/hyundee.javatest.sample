package test;

/**
 * Created by hyunji on 2016. 9. 18..
 */
class StringTest {
    public static void main(String[] args){
        String  test1 = "";
        String  test2 = null ;
        String  test3 = "I love you";
        if(isNotEmpty(test1));

        if("".equals(test2));
        // if(test2.equals(""));

        if(isNotEmpty(test3));




        if("I love you".equals(test1)) {
            System.out.println("test1 true");
        }
        if("I love you".equals(test2)) {
            System.out.println("test2 true");
        }
        if("I love you".equals(test3)) {
            System.out.println("test3 true");
        }

    }

    public static boolean isNotEmpty(String stringtest){
        if(stringtest == null || stringtest.length() == 0){
            return false;
        }

        return true;
    }

}
