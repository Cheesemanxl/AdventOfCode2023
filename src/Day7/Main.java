package Day7;

import aoc.Helpers;

import java.util.ArrayList;
import java.util.List;

import static aoc.Helpers.printList;

public class Main {
    static List<Hand> highCardHands = new ArrayList<>();
    static List<Hand> onePairHands = new ArrayList<>();
    static List<Hand> twoPairHands = new ArrayList<>();
    static List<Hand> threeOfAKindHands = new ArrayList<>();
    static List<Hand> fullHouseHands = new ArrayList<>();
    static List<Hand> fourOfAKindHands = new ArrayList<>();
    static List<Hand> fiveOfAKindHands = new ArrayList<>();

    static Integer finalTotal = 0;

    public static void main(String[] args) {
        List<String> input = Helpers.extractInputFromFile("src/inputs/Day7.txt");
        List<Hand> hands = new ArrayList<>();

        for (String s : input) {
            String[] inputs = s.split(" ");

            hands.add(new Hand(inputs[0], inputs[1]));
        }
        populateHandLists(hands);
        List<Hand> sortedHands = sortAllHands();
        printList(sortedHands);

        for (int i = 0; i < sortedHands.size(); i++) {
            System.out.println(sortedHands.get(i).getBid() + " * " + (sortedHands.size() - i) + " = " + (sortedHands.get(i).getBid()) * (sortedHands.size() - i));
            finalTotal += ((sortedHands.get(i).getBid()) * (sortedHands.size() - i));
        }

        System.out.println(finalTotal);
    }

    public static void sortHands(List<Hand> hands) {
        for (int i = 0; i < hands.size(); i++) {
            for (int j = 0; j < hands.size() - i - 1; j++) {
                if (compareHands(hands.get(j), hands.get(j + 1)) < 0) {
                    swapHands(hands, j, j + 1);
                }
            }
        }
    }

    private static int compareHands(Hand hand1, Hand hand2) {
        for (int k = 0; k < hand1.getCards().size(); k++) {
            Card.Value currentHandCardValue = hand1.getCards().get(k).getValue();
            Card.Value nextHandCardValue = hand2.getCards().get(k).getValue();

            int comparisonResult = currentHandCardValue.compareTo(nextHandCardValue);
            if (comparisonResult != 0) {
                return comparisonResult;
            }
        }
        return 0; // If all cards are equal, hands are considered equal
    }

    private static void swapHands(List<Hand> hands, int i, int j) {
        Hand temp = hands.get(i);
        hands.set(i, hands.get(j));
        hands.set(j, temp);
    }

    static List<Hand> sortAllHands(){
        sortHands(fiveOfAKindHands);
        sortHands(fourOfAKindHands);
        sortHands(fullHouseHands);
        sortHands(threeOfAKindHands);
        sortHands(twoPairHands);
        sortHands(onePairHands);
        sortHands(highCardHands);

        List<Hand> result = new ArrayList<>();
        result.addAll(fiveOfAKindHands);
        result.addAll(fourOfAKindHands);
        result.addAll(fullHouseHands);
        result.addAll(threeOfAKindHands);
        result.addAll(twoPairHands);
        result.addAll(onePairHands);
        result.addAll(highCardHands);
        return result;
    }

    static void populateHandLists(List<Hand> hands){
        for (Hand hand  : hands) {
            switch(hand.type) {
                case Hand.Type.HighCard -> highCardHands.add(hand);
                case Hand.Type.OnePair -> onePairHands.add(hand);
                case Hand.Type.TwoPair -> twoPairHands.add(hand);
                case Hand.Type.ThreeOfAKind -> threeOfAKindHands.add(hand);
                case Hand.Type.FullHouse -> fullHouseHands.add(hand);
                case Hand.Type.FourOfAKind -> fourOfAKindHands.add(hand);
                case Hand.Type.FiveOfAKind -> fiveOfAKindHands.add(hand);
            }
        }
    }

    static void printAllHandLists() {
        System.out.println("**********************************************************************************");
        System.out.println("     ***************************** High Card *******************************      ");
        System.out.println("**********************************************************************************");
        printList(highCardHands);
        System.out.println("**********************************************************************************");
        System.out.println("     ***************************** One Pair ********************************      ");
        System.out.println("**********************************************************************************");
        printList(onePairHands);
        System.out.println("**********************************************************************************");
        System.out.println("     ***************************** Two Pair ********************************      ");
        System.out.println("**********************************************************************************");
        printList(twoPairHands);
        System.out.println("**********************************************************************************");
        System.out.println("     *************************** Three of A Kind ****************************     ");
        System.out.println("**********************************************************************************");
        printList(threeOfAKindHands);
        System.out.println("**********************************************************************************");
        System.out.println("     **************************** Full House *******************************      ");
        System.out.println("**********************************************************************************");
        printList(fullHouseHands);
        System.out.println("**********************************************************************************");
        System.out.println("     *************************** Four of A Kind ****************************      ");
        System.out.println("**********************************************************************************");
        printList(fourOfAKindHands);
        System.out.println("**********************************************************************************");
        System.out.println("     *************************** Five of A Kind ****************************      ");
        System.out.println("**********************************************************************************");
        printList(fiveOfAKindHands);
        System.out.println("**********************************************************************************");
        System.out.println("    ***************************** End of List *******************************     ");
        System.out.println("**********************************************************************************");
    }
}
