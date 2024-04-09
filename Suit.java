public enum Suit {
    CLUBS, DIAMONDS, HEARTS, SPADES;

    @Override
    public String toString() {
        switch (this) {
            case CLUBS:
                return "C";
            case DIAMONDS:
                return "D";
            case HEARTS:
                return "H";
            case SPADES:
                return "S";
            default:
                throw new IllegalStateException("Unexpected value: " + this);
        }
    }
}