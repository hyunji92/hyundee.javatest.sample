package test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by hyunji on 2016. 8. 20..
 */
public class Match {

    public static void main(String[] args) {
        final String fileName = "data.txt";
        Scanner scanner = new Scanner(System.in);

        System.out.println("커플 매칭 대상자의 수를 입력해주세요 : ");
        int personNumber = scanner.nextInt();
        System.out.println(" 매칭 대상자의 수 : " + personNumber + " ");

        List<Set<String>> userHobbies = splitHobbyEachUser(fileName, personNumber);
        //findMatchUser(userHobbies);

        System.out.println(" 결과 값  : " + findMatchUser(userHobbies));
    }

    public static int solution(int a, int b) {
        if (b < 0 || b < a) {
            return 0;
        }
        if (a <= 0) a = 1;
       // System.out.println(PhoneNumberUtil.formateToPhoneNumber(str,"XXX-XXX-XX-XX",str.length()));
        return (int) Math.ceil(Math.sqrt(b) - Math.sqrt(a));
    }


    public static List<Set<String>> splitHobbyEachUser(String fileName, int userCount) {
        List<Set<String>> resultList = new ArrayList<>();

//        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
//
//            return reader.lines()
//                    .map(line -> Arrays.asList(line.split(separator)))
//                    .isParallel();
//                    //.collect(Collectors.toList());
////            .substream(1)
////            .map(line -> Arrays.asList(line.split(separator)))
////            .collect(Collectors.toList());
//        } catch (IOException e) {
//            throw new UncheckedIOException(e);
//        }


        try (BufferedReader in = new BufferedReader(new FileReader(fileName))) {

            String s;
            int lineNumber = 0;

            while ((s = in.readLine()) != null) {
                Set<String> userHobbies = new HashSet<>(Arrays.asList(s.split(" ")));
                resultList.add(userHobbies);
                lineNumber++;
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

    public static String findMatchUser(List<Set<String>> userHobbies) {
        List<String> matchedUsers = new ArrayList<>();

        for (int originIdx = 0; originIdx < userHobbies.size(); originIdx++) {
            final int originNameIdx = originIdx + 1;
            Set<String> originHobbies = userHobbies.get(originIdx);

            List<String> tempMatchedUsers = new ArrayList<>();
            long maxSameCount = 0;

            for (int targetIdx = 0; targetIdx < userHobbies.size(); targetIdx++) {
                if (originIdx == targetIdx) {
                    continue;
                }
                /*if(originIdx > targetIdx){
                    continue;
                   // 이렇게 하면 아래 로직을 한번도 타지 않는다.
                   // maxSameCount 가 한번도 변경되지 않고 0
                }*/
                final int targetNameIdx = targetIdx + 1;
                Set<String> targetHobbies = userHobbies.get(targetIdx);
                // 두 사용자의 취미 배열을 비교해서 같은 취미 ( 두배열을 교집합 ) 의 개수를 걸러내 얻도록한다.
                long sameCount = originHobbies.stream()
                        .filter(hobby -> targetHobbies.contains(hobby))
                        .count();

                // 위에서 구한 두배열의 같은 취미 개수와 0으로 초기화 해놓은 maxSameCount 비교
                if (sameCount > maxSameCount) {
                    maxSameCount = sameCount;
                    //처음에는 0 이였지만 같은 취미가 몇개나 있는지 구했으므로 maxSameCount에 넣어준다.
                    tempMatchedUsers = new ArrayList<>();
                    // 만약 1-2 가 8개의 취미가 같고 1-3이 10개의 취미가 같으면 1-2보다 1-3이 교집합 되는 수가 더크기때문에
                    // 1-2가 들어가지 않아도 되므로 1-2 들어가는 것을 방지하기 위해 더 큰 maxSameCount에 따라 기존에 1-2를 넣은 리스트를 초기화 시켜준다
                    // 1의
                }

                if (sameCount == maxSameCount) {
                    if (originIdx < targetIdx) {
                        // 1-3, 3-1 과 같은 중복되는 것을 방지하기 위해 구현.
                        tempMatchedUsers.add(originNameIdx + "-" + targetNameIdx);
                    }
                }

            }
            System.out.println("유저 번호 : " + originNameIdx);
            matchedUsers.addAll(tempMatchedUsers);
        }
        return matchedUsers.stream()
                .collect(Collectors.joining(", "));
    }
}
