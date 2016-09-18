package observertest;

import java.util.List;

/**
 * Created by hyunji on 2016. 9. 3..
 */

public class DataSheetView {
    private observertest2.ScoreRecord scoreRecord;
    private int viewCount;

    public DataSheetView(observertest2.ScoreRecord scoreRecord, int viewCount) {
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
