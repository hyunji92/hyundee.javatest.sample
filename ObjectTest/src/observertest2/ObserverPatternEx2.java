package observertest2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by hyunji on 2016. 9. 3..
 */
class ScoreRecord {
    private List<Integer> scores = new ArrayList<>();   //  점수를 저장함
    //private DataSheetView dataSheetView;    //  목록 형태로 점수를 출력하는 클래스
    private MinMaxView minMaxView;

    /* public void setDataSheetView(DataSheetView dataSheetView) {
        this.dataSheetView = dataSheetView;
    }*/

    public void setMinMaxView(MinMaxView minMaxView) {
        this.minMaxView = minMaxView;
    }

    public void addScore(int score) {    //새로운 점수를 추가함
        scores.add(score);  //  scores 목록에 주어진 점수를 추가함
        //dataSheetView.update(); //  scores가 변경됨을 통보함
        minMaxView.update();    //  MinMaxView에게 점수의 변경을 통보함
    }

    public List<Integer> getScoreRecord() {
        return scores;
    }
}

class MinMaxView {
    private ScoreRecord scoreRecord;

    public MinMaxView(ScoreRecord scoreRecord) {
        this.scoreRecord = scoreRecord;
    }

    public void update() {
        List<Integer> record = scoreRecord.getScoreRecord();    //  점수를 조회함
        displayMinMax(record);  //최소값과 최대값을 출력함
    }

    private void displayMinMax(List<Integer> record) {
        int min = Collections.min(record, null);
        int max = Collections.max(record, null);
        System.out.println("Min: " + min + " Max: " + max);
    }
}

/*class DataSheetView {
    private ScoreRecord scoreRecord;
    private int viewCount;

    public DataSheetView(ScoreRecord scoreRecord, int viewCount) {
        this.scoreRecord = scoreRecord;
        this.viewCount = viewCount;
    }

    public void update() {  //  점수의 변경을 통보받음
        List<Integer> record = scoreRecord.getScoreRecord();    // 점수를 조회함
        displayScores(record, viewCount);   //  조회된 점수를 viewCount 만큼만 출력함
    }

    private void displayScores(List<Integer> record, int viewCount) {
        System.out.println("List of " + viewCount + " entries: ");
        for (int i = 0; i < viewCount && i < record.size(); i++) {
            System.out.println(record.get(i) + " ");
        }
        System.out.println();
    }
}*/

public class ObserverPatternEx2 {
    public static void main(String[] args) {
        ScoreRecord scoreRecord = new ScoreRecord();

        /*//  3개 까지의 점수만 출력함
        DataSheetView dataSheetView = new DataSheetView(scoreRecord, 3);
        scoreRecord.setDataSheetView(dataSheetView);*/

        MinMaxView minMaxView = new MinMaxView(scoreRecord);
        scoreRecord.setMinMaxView(minMaxView);

        for (int index = 1; index <= 5; index++) {
            int score = index * 10;
            System.out.println("Adding " + score);

            //  10, 20, 30, 40, 50을 추가함 추가할 때 마다 최소/최대 값을 출력함
            scoreRecord.addScore(score);
        }
    }
}