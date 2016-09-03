package observertest;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hyunji on 2016. 9. 3..
 */
class ScoreRecord {
    private List<Integer> scores = new ArrayList<>();   //  점수를 저장함
    private DataSheetView dataSheetView;    //  목록 형태로 점수를 출력하는 클래스

    public void setDataSheetView(DataSheetView dataSheetView) {
        this.dataSheetView = dataSheetView;
    }

    public void addScore(int score) {    //새로운 점수를 추가함
        scores.add(score);  //  scores 목록에 주어진 점수를 추가함
        dataSheetView.update(); //  scores가 변경됨을 통보함
    }

    public List<Integer> getScoreRecord() {
        return scores;
    }
}

