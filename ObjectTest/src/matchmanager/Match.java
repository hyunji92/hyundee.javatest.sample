package matchmanager;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by hyunji on 2016. 8. 21..
 */
public class Match {

    public static List<User> splitHobbyEachUser(String fileName, int userCount) {
        List<User> resultList = new ArrayList<>();

        try (BufferedReader in = new BufferedReader(new FileReader(fileName))) {

            String s;
            int lineNumber = 0;

            while ((s = in.readLine()) != null) {
                lineNumber++;
                User user = new User();
                user.setLineNumber(lineNumber);
                user.setHobbies(new HashSet<>(Arrays.asList(s.split(" "))));
                if (lineNumber == userCount) {
                    break;
                }
            }
            in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return resultList;
    }

    public static String findMatchUser(List<User> userArray) {
        List<User> resultUsers = new ArrayList<>();
        for(User userOne : userArray) {
            final Set<String> userOneHobbies = userOne.getHobbies();
            for (User userTwo: userArray) {
                final Set<String> userTwoHobbies = userTwo.getHobbies();
                long similirarityCount = userOneHobbies.stream()
                                        .filter(h -> userTwoHobbies.contains(h))
                                        .count();

                if(similirarityCount > userOne.getSimiliarity()) {
                    userOne.setSimiliarity(similirarityCount);
                    userOne.setMatchedUsers(new HashSet<>());
                }

                if(similirarityCount == userOne.getSimiliarity()) {
                    userOne.getMatchedUsers().add(userTwo.getLineNumber());
                }
            }

        }
        long maxSimilirarity = userArray.stream().max(Comparator.comparing(User::getSimiliarity)).get().getSimiliarity();
        List<User> definedArray = userArray.stream().filter(u -> u.getSimiliarity() == maxSimilirarity).collect(Collectors.toList());

        Set<String> matchedSet = new HashSet<>();
        definedArray.forEach(u1 ->
            u1.getMatchedUsers().forEach(u2 -> matchedSet.add(u1.getLineNumber() + "-" + u2)));


        return resultUsers.stream().map(String::valueOf).collect(Collectors.joining(", "));
    }
}
