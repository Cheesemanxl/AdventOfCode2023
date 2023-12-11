package Day7;

import java.util.*;

import static aoc.Helpers.getKeySubset;

public class Hand {
    String rawCards;
    List<Card> cards = new ArrayList<>();
    Type type;
    Card.Value highValue = Card.Value.Null;
    Card.Value pairValueOne = Card.Value.Null;
    Card.Value pairValueTwo = Card.Value.Null;
    Integer bid = 0;

    Map<Card.Value, Integer> amountsOfEachCard = new TreeMap<>();
    {
        amountsOfEachCard.put(Card.Value.Two, 0);
        amountsOfEachCard.put(Card.Value.Three, 0);
        amountsOfEachCard.put(Card.Value.Four, 0);
        amountsOfEachCard.put(Card.Value.Five, 0);
        amountsOfEachCard.put(Card.Value.Six, 0);
        amountsOfEachCard.put(Card.Value.Seven, 0);
        amountsOfEachCard.put(Card.Value.Eight, 0);
        amountsOfEachCard.put(Card.Value.Nine, 0);
        amountsOfEachCard.put(Card.Value.Ten, 0);
        amountsOfEachCard.put(Card.Value.Jack, 0);
        amountsOfEachCard.put(Card.Value.Queen, 0);
        amountsOfEachCard.put(Card.Value.King, 0);
        amountsOfEachCard.put(Card.Value.Ace, 0);
    }

    Hand(String cards, String bid) {
        this.rawCards = cards;

        for (Character c : cards.toCharArray()) {
             this.cards.add(new Card(c));
        }

        for (Card card : this.cards) {
            if(card.getValue().isGreaterThan(highValue)){
                this.highValue = card.getValue();
            }
        }

        countNumberOfCardsOfEachValue();
        this.type = determineHandType();
        this.bid = Integer.parseInt(bid);
    }

    void countNumberOfCardsOfEachValue() {
        //Count number of cards of each value ex.
        // 32T3K returns {0, 1, 2, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0}
        // T55J5 returns {0, 0, 0, 0, 3, 0, 0, 0, 0, 1, 1, 0, 0, 0}
        // KK677 returns {0, 0, 0, 0, 0, 1, 2, 0, 0, 0, 0, 0, 2, 0}
        // KTJJT returns {0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 0, 1, 0}
        // QQQJA returns {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 3, 0, 1}
        for (Card.Value cardValue : amountsOfEachCard.keySet()) {
            for (Card card : cards) {
                if(card.value.equals(cardValue)) {
                    amountsOfEachCard.put(cardValue, amountsOfEachCard.get(cardValue) + 1);
                }
            }
        }
    }

    Type determineHandType() {


        for (Card.Value cardValueKey : amountsOfEachCard.keySet()) {
            switch(amountsOfEachCard.get(cardValueKey)) {
                case 2:
                    pairValueOne = cardValueKey;

                    for (Card.Value cardValueKeyAgain : getKeySubset(amountsOfEachCard, cardValueKey, Card.Value.Ace)) {
                        if(amountsOfEachCard.get(cardValueKeyAgain).equals(2)) {
                            pairValueTwo = cardValueKeyAgain;
                            return Type.TwoPair;
                        } else if (amountsOfEachCard.get(cardValueKeyAgain).equals(3)) {
                            pairValueTwo = cardValueKeyAgain;
                            return Type.FullHouse;
                        }
                    }
                    return Type.OnePair;
                case 3:
                    pairValueOne = cardValueKey;

                    for (Card.Value cardValueKeyAgain : amountsOfEachCard.keySet()) {
                        if(amountsOfEachCard.get(cardValueKeyAgain).equals(2)) {
                            pairValueTwo = cardValueKeyAgain;
                            return Type.FullHouse;
                        }
                        return Type.ThreeOfAKind;
                    }
                    break;
                case 4:
                    pairValueOne = cardValueKey;
                    return Type.FourOfAKind;
                case 5:
                    pairValueOne = cardValueKey;
                    return Type.FiveOfAKind;
                default:
                    break;
            }
        }

        return Type.HighCard;
    }

    enum Type {
        HighCard,
        OnePair,
        TwoPair,
        ThreeOfAKind,
        FullHouse,
        FourOfAKind,
        FiveOfAKind;

        public boolean isGreaterThan(Type other) {
            return this.ordinal() > other.ordinal();
        }

        public boolean isLessThan(Type other) {
            return this.ordinal() < other.ordinal();
        }
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("Cards: ").append(this.rawCards).append('\n');
        str.append("Type: ").append(this.type).append('\n');
        str.append("Highest Card Value: ").append(this.highValue.toString()).append('\n');
        str.append("Pair/Full House Value One: ").append(this.pairValueOne).append('\n');
        str.append("Pair/Full House Value Two: ").append(this.pairValueTwo).append('\n');
        str.append("Bid: ").append(this.bid).append('\n');
        str.append("| 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 | T | J | Q | K | A |").append('\n');
        str.append("| ");
        for (Card.Value cardValueKey : amountsOfEachCard.keySet()) {
            str.append(amountsOfEachCard.get(cardValueKey));
            if(!cardValueKey.equals(Card.Value.Ace)) {
                str.append(" | ");
            }
        }
        str.append(" |");
        return str.toString();
    }

    public List<Card> getCards() {
        return cards;
    }

    public Integer getBid() {
        return bid;
    }
}
