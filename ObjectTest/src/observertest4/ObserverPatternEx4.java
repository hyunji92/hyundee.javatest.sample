package observertest4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by hyunji on 2016. 9. 3..
 */
interface Observer { //  추상화된 통보 대상
    void update();  //  데이터의 변경을 통보했을 때 처리하는 메서드
    // 옵저버를 상속받고 서브젝트에 등록이 된애들에게만 등록한다.

}

abstract class Subject {
    private List<Observer> observers = new ArrayList<>();   //  추상화된 통보 대상 목록

    public void attach(Observer observer) {
        observers.add(observer);
    }

    public void detach(Observer observer) {
        observers.remove(observer);
    }

    public void notifyObservers() {
        for (Observer o : observers) {
            o.update();
        }
    }
}

class ScoreRecord extends Subject {
    private List<Integer> scores = new ArrayList<>();

    public void addScore(int score) {
        scores.add(score);
        notifyObservers();  //  데이터가 변경되면 Subject 클래스의 notifyObservers 메서드를 호출함
    }

    public List<Integer> getScoreRecord() {
        return scores;
    }
}

class DataSheetView implements Observer {
    //Observer가아니라 concrateObserver 추상화된
    private ScoreRecord scoreRecord;
    private int viewCount;

    public DataSheetView(ScoreRecord scoreRecord, int viewCount) {
        this.scoreRecord = scoreRecord;
        this.viewCount = viewCount;
    }

    @Override
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

class MinMaxView implements Observer {
    private ScoreRecord scoreRecord;

    public MinMaxView(ScoreRecord scoreRecord) {
        this.scoreRecord = scoreRecord;
    }

    @Override
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

public class ObserverPatternEx4 {
    public static void main(String[] args) {
        ScoreRecord scoreRecord = new ScoreRecord();
        DataSheetView dataSheetView3 = new DataSheetView(scoreRecord, 3);
        DataSheetView dataSheetView5 = new DataSheetView(scoreRecord, 5);
        MinMaxView minMaxView = new MinMaxView(scoreRecord);

        //  3개 목록 DataSheetView를 ScoreRecord에 Observer로 추가함
        scoreRecord.attach(dataSheetView3);

        //  5개 목록 DataSheetView를 ScoreRecord에 Observer로 추가함
        scoreRecord.attach(dataSheetView5);

        //  MinMaxView를 ScordRecord에 Observer로 추가함
        scoreRecord.attach(minMaxView);

        for (int index = 1; index <= 5; index++) {
            int score = index * 10;
            System.out.println("Adding " + score);

            //  10, 20, 30, 40, 50을 추가함 추가할 때 마다 최대 3개의 점수만 출력함
            //  추가할 때마다 최대 3개 목록 최대 5개 목록 그리고 최소/최대 값이 출력됨
            scoreRecord.addScore(score);
        }
    }
}
