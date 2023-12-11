package Day7;

import java.util.InputMismatchException;

public class Card {
    Value value;
    Suit suit;
    Color color;

    Card(Character c) {
        switch(c) {
            case '2':
                this.value = Value.Two;
                break;
            case '3':
                this.value = Value.Three;
                break;
            case '4':
                this.value = Value.Four;
                break;
            case '5':
                this.value = Value.Five;
                break;
            case '6':
                this.value = Value.Six;
                break;
            case '7':
                this.value = Value.Seven;
                break;
            case '8':
                this.value = Value.Eight;
                break;
            case '9':
                this.value = Value.Nine;
                break;
            case 'T':
                this.value = Value.Ten;
                break;
            case 'J':
                this.value = Value.Jack;
                break;
            case 'Q':
                this.value = Value.Queen;
                break;
            case 'K':
                this.value = Value.King;
                break;
            case 'A':
                this.value = Value.Ace;
                break;
            default:
                this.value = Value.Null;
                break;
        }
    }

    public Value getValue() {
        return value;
    }

    enum Value {
        Null,
        Two,
        Three,
        Four,
        Five,
        Six,
        Seven,
        Eight,
        Nine,
        Ten,
        Jack,
        Queen,
        King,
        Ace;

        public boolean isGreaterThan(Value other) {
            return this.ordinal() > other.ordinal();
        }

        public boolean isLessThan(Value other) {
            return this.ordinal() < other.ordinal();
        }
    }

    enum Suit {
        Heart,
        Club,
        Spade,
        Diamond
    }

    enum Color {
        Red,
        Black
    }
}
