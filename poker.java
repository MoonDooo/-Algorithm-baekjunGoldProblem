/**
 * 백준 1318 포커
 * author : MoonDooo
 * 정답 : TEXT 형태로 제출
 * 1005/3094
 * 486537/1017926
 * 24354/195755
 * 1408/39151
 * 14103/1017926
 * 9/4606
 * 9/4606
 * 25747/2544815
 * 228/27965
 * 3/4165
 * 184/2544815
 * 1/108290
 *
 * 약분은 따로 처리
 */

import java.util.*;

public class Main {
    public static void main(String[] args) {
        new Poker();
    }
}
class Poker{
    private Card[] cards;
    int[] result;
    int r = 0;
    public Poker(){
        cards = new Card[52];
        result = new int[12];
        int count =0;
        for (Shape s : Shape.values()){
            for(int i = 1; i<=13; i++){
                cards[count] = new Card(s, i);
                count++;
            }
        }
        for (int i =0; i<52; i++){
            for (int j =i+1; j<52; j++){
                for (int k =j+1; k<52; k++){
                    for (int l = k+1; l<52; l++){
                        for (int m = l+1; m<52; m++){
                            for (int n = m+1; n<52; n++){
                                sixPokerJokbo(i,j,k,l,m,n);
                            }
                        }
                    }
                }
            }
        }
        int sum = Arrays.stream(result).sum();
        for (int i = 0; i<12; i++){
            System.out.println(result[i]+"/"+sum);
        }
    }

    public void sixPokerJokbo(int card1, int card2, int card3, int card4, int card5, int card6){
        int[] cards = {card1, card2, card3, card4, card5, card6};
        int[] midResult = new int[12];
        for (int i =0; i<6; i++) {
            midResult[PokerJokbo(cards[i % 6], cards[(i + 1) % 6], cards[(i + 2) % 6], cards[(i + 3) % 6], cards[(i + 4) % 6])]++;
        }
        for (int i = 11; 0<=i; i--){
            if (0<midResult[i]){
                result[i]++;
                return;
            }
        }
    }

    private int PokerJokbo(int card1, int card2, int card3, int card4, int card5) {
        List<Card> fiveCards = new ArrayList<>();
        fiveCards.add(cards[card1]);
        fiveCards.add(cards[card2]);
        fiveCards.add(cards[card3]);
        fiveCards.add(cards[card4]);
        fiveCards.add(cards[card5]);

        int uniqueNumberNum = new HashSet<>(fiveCards.stream().map(Card::getNumber).toList()).size();

        /**
         * 로얄 스트레이트 플러쉬
         */
        boolean isRoyalStraightFlush = false;
        if (isFlush(fiveCards)){
            if (isBack(fiveCards, uniqueNumberNum)){
                isRoyalStraightFlush = true;
            }
        }
        if (isRoyalStraightFlush){
            return 11;
        }
        /**
         * 스트레이트 플러쉬
         */
        boolean isStraightFlush = false;
        if (isFlush(fiveCards)){
            if (isStraight(fiveCards,uniqueNumberNum)){
                isStraightFlush = true;
            }/*else{
                if (isMountain(fiveCards, uniqueNumberNum)){
                    isStraightFlush = true;
                }
            }*/
        }
        if (isStraightFlush){
            return 10;
        }
        /**
         * 포카드
         */
        boolean isFourCard = false;
        for (int i = 0; i<2; i++){
            int tmp = fiveCards.get(i).getNumber();
            if (fiveCards.stream().filter(c-> c.getNumber()==tmp).count() == 4)isFourCard=true;
            if (isFourCard)break;
        }
        if (isFourCard){
            return 9;
        }
        /**
         * 풀하우스
         */
        if (uniqueNumberNum==2){
            return 8;
        }

        /**
         * 플러쉬
         */
        if (isFlush(fiveCards))return 7;
        /**
         * 마운틴
         */
        if (isMountain(fiveCards, uniqueNumberNum))return 6;
        /**
         * 빽스트래이트
         */
        if (isBack(fiveCards, uniqueNumberNum))return 5;
        /**
         * 스트레이트
         */
        if (isStraight(fiveCards, uniqueNumberNum))return 4;
        /**
         * 트리플
         */
        boolean isTriple = false;
        for (int i = 0; i<3; i++){
            int tmp = fiveCards.get(i).getNumber();
            if (fiveCards.stream().filter(c-> c.getNumber()==tmp).count() == 3)isTriple=true;
            if (isTriple)break;
        }
        if (isTriple){
            return 3;
        }
        /**
         * 투페어
         */
        if (uniqueNumberNum==3)return 2;
        /**
         * 원페어
         */
        if (uniqueNumberNum==4)return 1;
        /**
         * 탑
         */
        return 0;
    }


    private static boolean isStraight(List<Card> fiveCards, int uniqueNumberNum) {
        if (uniqueNumberNum==5){
            List<Integer> numbers = new ArrayList<>(fiveCards.stream().map(Card::getNumber).toList());
            int max = Collections.max(numbers);
            int min = Collections.min(numbers);
            return max - min == 4;
        }
        return false;
    }

    private static boolean isMountain(List<Card> fiveCards, int uniqueNumberNum) {
        if (uniqueNumberNum==5){
            List<Integer> mountain = new ArrayList<>(Arrays.asList(1,10,11,12,13));
            return fiveCards.stream().filter(c -> mountain.contains(c.getNumber())).count() == fiveCards.size();
        }
        return false;
    }

    private static boolean isBack(List<Card> fiveCards, int uniqueNumberNum){
        if (uniqueNumberNum==5) {
            List<Integer> back = Arrays.asList(1,2,3,4,5);
            return fiveCards.stream().filter(c->back.contains(c.getNumber())).count()==fiveCards.size();
        }
        return false;
    }

    private static boolean isFlush(List<Card> fiveCards) {
        return fiveCards.stream().filter(c -> c.getShape() == fiveCards.get(0).getShape()).count() == fiveCards.size();
    }
}
class Card {
    private Shape shape;
    private int number;

    public Card(Shape shape, int number) {
        this.shape = shape;
        this.number = number;
    }

    public Shape getShape() {
        return shape;
    }

    public int getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return "Card{" +
                "shape=" + shape +
                ", number=" + number +
                '}';
    }
}
enum Shape{
    Club,
    Heart,
    Diamond,
    Spade
}
