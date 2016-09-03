package observertest;

/**
 * Created by hyunji on 2016. 9. 3..
 */
public class ObserverPatternEx1 {
    public static void main(String[] args) {
        observertest2.ScoreRecord scoreRecord = new observertest2.ScoreRecord();

        //  3개 까지의 점수만 출력함
        DataSheetView dataSheetView = new DataSheetView(scoreRecord, 3);
        scoreRecord.setDataSheetView(dataSheetView);

        for (int index = 1; index <= 5; index++) {
            int score = index * 10;
            System.out.println("Adding " + score);

            //  10, 20, 30, 40, 50을 추가함 추가할 때 마다 최대 3개의 점수만 출력함
            scoreRecord.addScore(score);
        }
    }
}