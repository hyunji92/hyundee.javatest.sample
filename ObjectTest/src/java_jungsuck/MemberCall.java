package java_jungsuck;

/**
 * Created by hyunji on 2016. 11. 3..
 */
public class MemberCall {
    int iv = 10;
    static int cv = 20;

    int iv2 = cv;
    /** static int cv2 = iv; //Non-static cannot be referenced 클래스변수는 인스턴스 변수를 사용할 수 없다 */
    static int cv2 = new MemberCall().iv; // 이처럼 객채를 생성해야 사용이 가능하다

    static  void staticMethod1(){
        System.out.println(cv);
        // System.out.println(iv); // 클래스메서드에서 인스턴스변수를 사용불가

    }

    void instanceMethod1(){
        System.out.println(cv);
        System.out.println(iv); //인스턴스 메서드에서는 인스턴스변수를 바로 사용가능하다.
    }

    static void staticMethod2(){
        staticMethod1();
        //instanceMethod1();//Non-static cannot be referenced
        MemberCall mc = new MemberCall();
        mc.instanceMethod1(); /** 인스턴스를 생성한 후에야 호출이 가능하다 */
    }

    void instanceMethod2(){
        staticMethod1();
        instanceMethod1(); /** 인스턴스 메서드에서는 인스턴스 메서드와 클래스 메서드 모두 인스턴스 생성 필요없이 호출이 가능한것 */
    }



}
