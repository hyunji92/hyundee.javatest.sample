package apple;

import apple.MethodReference;

/**
 * Created by hyunji on 2016. 7. 31..
 */
public class AppleSimpleFormatter   implements MethodReference.AppleFormatter {

    @Override
    public String accept(Apple apple) {
        return "An apple of  " + apple.getWeight() + "g ";
    }
}