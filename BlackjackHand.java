import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BlackjackHand {
    private static final int MAX_VALUE = 21;
    private static final Map<Rank, Integer> CARD_VALUES = initCardValues();

    private static Map<Rank, Integer> initCardValues() {
        Map<Rank, Integer> cardValues = new HashMap<>();
        for (Rank rank : Rank.values()) {
            int value = switch (rank) {
                case TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE -> rank.ordinal() + 2;
                case TEN, JACK, QUEEN, KING -> 10;
                case ACE -> 11;
                default -> throw new IllegalArgumentException("Unexpected rank: " + rank);
            };
            cardValues.put(rank, value);
        }
        return cardValues;
    }

    private final List<Card> cards;
    private int value;
    private int numAcesAs11;

    public BlackjackHand(Card c1, Card c2) {
        cards = new ArrayList<>();
        cards.add(c1);
        cards.add(c2);
        value = calculateInitialValue(c1, c2);
    }

    private int calculateInitialValue(Card c1, Card c2) {
        int value = CARD_VALUES.get(c1.getRank()) + CARD_VALUES.get(c2.getRank());
        numAcesAs11 = (c1.getRank() == Rank.ACE ? 1 : 0) + (c2.getRank() == Rank.ACE ? 1 : 0);
        return adjustValue(value);
    }

    private int adjustValue(int value) {
        while (value > MAX_VALUE && numAcesAs11 > 0) {
            value -= 10;
            numAcesAs11--;
        }
        return value;
    }

    public void addCard(Card card) {
        if (card == null) {
            throw new NullPointerException("Card cannot be null");
        }
        if (value < MAX_VALUE) {
            cards.add(card);
            int cardValue = CARD_VALUES.get(card.getRank());
            if (card.getRank() == Rank.ACE) {
                if (value + 11 <= MAX_VALUE) {
                    value += 11;
                    numAcesAs11++;
                } else {
                    value += 1;
                }
            } else {
                value += cardValue;
            }
            value = adjustValue(value);
        }
    }

    public int size() {
        return cards.size();
    }

    public static Map<Rank, Integer> getCardValues() {
        return new HashMap<>(CARD_VALUES);
    }

    public List<Card> getCards() {
        return new ArrayList<>(cards);
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        StringBuilder stb = new StringBuilder("[");
        for (Card card : cards) {
            stb.append(card.toString()).append(", ");
        }
        if (cards.size() > 0) {
            stb.setLength(stb.length() - 2);
        }
        stb.append("]");
        return stb.toString();
    }
}

