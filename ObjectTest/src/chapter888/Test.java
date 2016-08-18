package chapter888;

/**
 * Created by hyunji on 2016. 8. 18..
 */
public class Test {

    public static void doSomething(Runnable r){ r.run();}
    //public static void doSomething(Task a){ r.execute();}


    //doSomething(()-> System.out.prinln("Danger ..." )); -  doSomething(Runnable r)과 doSomething(Task a) 중에
    // 어느것을 가르키는지 알 수 없는 모호함의 문제가 발생

    //그렇다면 doSomething(()-> System.out.prinln("Danger ..." ));

}
interface Task{
    public void execute();


}
