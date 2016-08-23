package matchmanager;

import java.util.List;
/**
 * Created by hyunji on 2016. 8. 21..
 */
public class MatchTest {
    public static void main(String[] args) throws Exception {
        testFindMatchUsers();
    }


    private static void testFindMatchUsers() throws Exception {
        final int userCount = 3;
        final String testFileName = "test_array2.txt";
        List<User> userArray = Match.splitHobbyEachUser(testFileName, userCount);

        String output = Match.findMatchUser(userArray);
        System.out.println("2222 Result : " + output);

        assertIn("1-3,", output);

        assertNotIn("2-3", output);
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
}
