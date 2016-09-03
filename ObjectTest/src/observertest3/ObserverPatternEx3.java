package observertest3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by hyunji on 2016. 9. 3..
 */
class ScoreRecord {
    private List<Integer> scores = new ArrayList<>();   //  점수를 저장함
    private List<DataSheetView> dataSheetViews = new ArrayList<>();    //  복수개의 DataSheetView
    private MinMaxView minMaxView;

    public void addDataSheetView(DataSheetView dataSheetView) {
        dataSheetViews.add(dataSheetView);
    }

    public void setMinMaxView(MinMaxView minMaxView) {
        this.minMaxView = minMaxView;
    }

    public void addScore(int score) {
        scores.add(score);
        for (DataSheetView dataSheetView : dataSheetViews) {
            dataSheetView.update(); //각 DataSheetView에 값으 변경을 통보함
        }
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

class DataSheetView {
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
}

public class ObserverPatternEx3 {
    public static void main(String[] args) {
        ScoreRecord scoreRecord = new ScoreRecord();

        //  3개 목록의 DataSheetView 생성
        DataSheetView dataSheetView3 = new DataSheetView(scoreRecord, 3);

        //  5개 목록의 DataSheetView 생성
        DataSheetView dataSheetView5 = new DataSheetView(scoreRecord, 5);


        //  MinMax 생성
        MinMaxView minMaxView = new MinMaxView(scoreRecord);

        scoreRecord.addDataSheetView(dataSheetView3);
        scoreRecord.addDataSheetView(dataSheetView5);
        scoreRecord.setMinMaxView(minMaxView);

        for (int index = 1; index <= 5; index++) {
            int score = index * 10;
            System.out.println("Adding " + score);

            //  10, 20, 30, 40, 50을 추가함 추가할 때 마다 최대 3개의 점수만 출력함
            //  추가할 때마다 최대 3개 목록 최대 5개 목록 그리고 최소/최대 값이 출력됨
            scoreRecord.addScore(score);
        }
    }
}
