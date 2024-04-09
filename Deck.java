import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Deck {
    private final List<Card> cards;

    public Deck() {
        cards = new ArrayList<>();
        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                cards.add(new Card(rank, suit));
            }
        }
    }

    public int size() {
        return cards.size();
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

    public Card draw() {
        if (cards.isEmpty()) {
            return null;
        }
        return cards.remove(0);
    }

    public List<Card> draw(int count) {
        if (count < 0) {
            return new ArrayList<>();
        }
        List<Card> drawnCards = new ArrayList<>();
        for (int i = 0; i < count && !cards.isEmpty(); i++) {
            drawnCards.add(cards.remove(0));
        }
        return drawnCards;
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public List<Card> getCardsByRank(Rank rank) {
        return cards.stream()
                .filter(card -> card.getRank() == rank)
                .collect(Collectors.toList());
    }
}