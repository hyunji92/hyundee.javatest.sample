package birdview;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * Created by hyunji on 2016. 8. 20..
 */
public class CoupleMatching {
    public static int personNumber;
    public static List<HashSet<String>> list;
    public static String[][] personTable;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("커플 매칭 대상자의 수를 입력해주세요 : ");
        personNumber = scanner.nextInt();
        System.out.println(" 매칭 대상자의 수 : " + personNumber + " ");
        dataRead();
        match(list);

    }

    public static void dataRead() {
        try {
            BufferedReader in = new BufferedReader(new FileReader("data.txt"));
            personTable = new String[personNumber][10];
            String s;
            list = new ArrayList<>();
            while ((s = in.readLine()) != null) {
                System.out.println("ssssss  : " + s);
                HashSet<String> splitHobby = new HashSet<>(Arrays.asList(s.split(" ")));
                list.add(splitHobby);
            }
            in.close();
        } catch (IOException e) {
            //혹시 입출력 에러가 발생했다면 어떤 에러인지 출력하고 끄자.
            System.err.println(e);
            System.exit(1);
        }

    }

    public static void match(List<HashSet<String>> list) {
        int userIdx = 1;
        List<HashSet<String>> matchList = new ArrayList(list);
        List<HashSet<String>> targetList = new ArrayList(list);
        while (list.size() > 1) {
            HashSet<String> user = list.get(0);
            list.remove(0);
            int maxDiffCount = 0;
            int another_idx = 1;
            List<String> temp_match_array = new ArrayList<>();

            for (HashSet<String> another : targetList) {

                if (userIdx == another_idx) {
                    another_idx += 1;
                    continue;
                }




//                if (diff < min_diff) {
//                    min_diff = diff;
//                    //temp_match_array = []
//                }
//                if (diff == min_diff){
//                    if(userIdx < another_idx){
//                        temp_match_array.add(userIdx + "-" +another_idx);
//                    }
//                }
                another_idx +=1;
            }
            userIdx +=1;
            System.out.println("userIdx  : "+userIdx);
//            matchList.add(String.valueOf(temp_match_array));
        }
    }
}
