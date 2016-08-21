package test;

import java.util.*;

/**
 * Created by hyunji on 2016. 8. 20..
 */
public class MatchTest {
    public static void main(String[] args) throws Exception {

        testSplitHobbyEachUser();
        //testFindMatchUsers();
        testFindMatchUsers2();
    }

    private static void testFindMatchUsers2() throws Exception {
        final int userCount = 3;
        final String testFileName = "test_array2.txt";
        List<Set<String>> matchArray = Match.splitHobbyEachUser(testFileName, userCount);

        String output = Match.findMatchUser(matchArray);
        System.out.println("2222 Result : " + output);
        assertIn("1-3,", output);

        assertNotIn("2-3", output);

    }

    private static void testFindMatchUsers() throws Exception {
        final int userCount = 12;
        final String testFileName = "test_array.txt";
        List<Set<String>> matchArray = Match.splitHobbyEachUser(testFileName, userCount);

        String output = Match.findMatchUser(matchArray);
        System.out.println("Result : " + output);
        assertIn("1-3,", output);
        assertIn("2-8,", output);
        assertIn("3-5,", output);

        assertNotIn("1-1,", output);
        assertNotIn("3-1,", output);
        assertNotIn("11-12", output);
    }

    private static void assertIn(String expect, String wrapper) throws Exception {
        if(wrapper.contains(expect)){
            System.out.println(expect + " is Exist !!!");
        } else {
            throw new Exception(expect + " is Not Found !!!");
        }
    }

    private static void assertNotIn(String expect, String wrapper) throws Exception {
        if(wrapper.contains(expect)){
            throw new Exception(expect + " is Found !!!");
        } else {
            System.out.println(expect + " is Not Exist !!!");
        }
    }
    private static void testSplitHobbyEachUser() throws Exception {
        Set<String> testArray = new HashSet<>(Arrays.asList("A", "B", "C", "D", "E", "F", "G", "H", "I", "J"));

        final int userCount = 12;
        final String testFileName = "test_array.txt";
        List<Set<String>> matchArray = Match.splitHobbyEachUser(testFileName, userCount);
        if(matchArray.get(0).equals(testArray)){
            System.out.println("SplitHobbyEachUser is Success !!!" );
            System.out.println("Match!!!! : " + matchArray);
        } else {
            throw new Exception("SplitHobbyEachUser Error!!!!!");
        }

        if(matchArray.size() == userCount){
            System.out.println(" Count Match !!!" );
        } else {
            throw new Exception(" Count Error !!! ");
        }
    }
}
