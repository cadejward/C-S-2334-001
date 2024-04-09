import java.util.Objects;

public class Card implements Comparable<Card> {
    private final Rank rank;
    private final Suit suit;

    public Card(Rank rank, Suit suit) {
        if (rank == null || suit == null) {
            throw new NullPointerException("Rank and Suit cannot be null");
        }
        this.rank = rank;
        this.suit = suit;
    }

    @Override
    public int compareTo(Card other) {
        int suitCompare = suit.compareTo(other.suit);
        if (suitCompare != 0) {
            return suitCompare;
        }
        return rank.compareTo(other.rank);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Card)) {
            return false;
        }
        Card other = (Card) obj;
        return rank == other.rank && suit == other.suit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rank, suit);
    }

    public Rank getRank() {
        return rank;
    }

    public Suit getSuit() {
        return suit;
    }

    @Override
    public String toString() {
        return rank.toString() + suit.toString();
    }
}