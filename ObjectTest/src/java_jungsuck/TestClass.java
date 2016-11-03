package java_jungsuck;

/**
 * Created by hyunji on 2016. 11. 3..
 */
public class TestClass {
    void instanceMethod(){} // i
    static void staticMethid(){}

    void instanceMethod2(){
        instanceMethod(); // 다른 인스턴스 메서드를 호출한다
        staticMethid(); // static메서드를 호출한다
    }

    static void staticMethod2(){
        staticMethid();
        //instanceMethod(); // Non-static method  cannot be referenced
        // static메서드에서 인스턴스 메서드를 호출 할 수 없다
    }
}
