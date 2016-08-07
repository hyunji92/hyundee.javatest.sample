package dish.realpractice;

import java.util.Arrays;
import java.util.List;

/**
 * Created by hyunji on 2016. 7. 10..
 */
public class PracticeReal {
    public static void main(String[] args) {

        Trader raoul =  new Trader("Raoul" , "Camridge");
        Trader mario = new Trader("Mario" , "Millan");
        Trader seoul = new Trader("Korea" , "Seoul");
        Trader seoul1 = new Trader("A" , "Seoul");
        Trader seoul2 = new Trader("B" , "Seoul");
        Trader seoul3 = new Trader("C" , "Seoul");

        List<Transaction> transactions = Arrays.asList(
                new Transaction(raoul, 2011, 300),
                new Transaction(raoul, 2012, 400),
                new Transaction(mario, 2013, 500),
                new Transaction(seoul, 2012, 600),
                new Transaction(seoul, 2014, 400),
                new Transaction(seoul3, 2014, 400),
                new Transaction(seoul1, 2014, 400),
                new Transaction(seoul2, 2014, 400),
                new Transaction(seoul, 2015, 300),
                new Transaction(seoul, 2011, 200),
                new Transaction(seoul, 2011, 200)
        );

        // 1. 다시
        boolean trans2011 = transactions.stream() // Stream<Transaction>
                .anyMatch((Transaction t) ->  t.getYear()==2014);


        /*.filter( (Transaction  t) -> t.getYear() == 2011) // Stream<Transaction>
                .map(t-> t.getTrader().getName()) // Stream<String>
                .distinct()
                .map(String::length) // Stream<Inteager>
                //(String s) -> , s-> , String ::length 3가지
                .collect(Collectors.toList()); // Stream 을 List로 List<String>*/
        // 1.
        /*List<Transaction> trans2016 = transactions.stream()
                .filter(transaction -> transaction.getYear() == 2011)
                .sorted(Comparator.comparing(Transaction::getValue))
                .collect(Collectors.toList());*/

        System.out.println(trans2011);


        // 2.
//        List<String> trans2012 = transactions.stream()
//                .map(transaction -> transaction.getTrader().getName())
//                .distinct()
//                .collect(Collectors.toList());
//
//        System.out.println(trans2012);
//
//        //3.

        // 그냥 sorted를 해버리면 객체를 비교하게 되어 값이 나오지 않는다
//        List<Trader> traders = transactions.stream()
//                .map(Transaction::getTrader) //Stream<Trader>
//                .filter(trader -> trader.getCity().equals("Seoul")) //Stream<Trader>
//                .distinct() //Stream<Trader>
//                // .map(Trader::getName) //Stream<String>
//                .sorted(Comparator.comparing(t-> t.getName()))
//                .collect(Collectors.toList());
        // 찾을 것을 명시해서 sort해줘야 비교하여 값이 나온다.

//        List<String> traders2 = transactions.stream()
//                .map(Transaction::getTrader) //Stream<Trader>
//                .filter(trader -> trader.getCity().equals("Seoul")) //Stream<Trader>
//                .distinct() //Stream<Trader>
//                .map(Trader::getName) //Stream<String>
//                .sorted()
//                .collect(Collectors.toList());
//        //.sorted(Comparator.comparing(Trader::getName))
        //.collect(Collectors.toList());

       /* System.out.println(traders + "**");
        System.out.println(traders2 + "**");*/


        //4.
//        List<String>  allTraders = transactions.stream()
//                .allMatch(transaction -> )

        //5.
        /*boolean seoulTraders = transactions.stream()
                .anyMatch(transaction -> transaction.getTrader()
                .getCity()
                .equals("seoul"));

        System.out.println(seoulTraders);*/

        //6.
//        List<Transaction> allTransaction = transactions.stream()
//                .filter( t-> traders.equals(t.getTrader().getCity()))
//                .map(Transaction::getValue)
                //.collect(transactions);

       /* //7.
        Optional<Integer> transMax = transactions.stream()
                .map(Transaction::getValue)
                .reduce(Integer::max);
        System.out.println(transMax);

        //8.
        Optional<Integer> transMin = transactions.stream()
                .map(Transaction::getValue)
                .reduce(Integer::min);
        System.out.println(transMin);*/



    }
}
