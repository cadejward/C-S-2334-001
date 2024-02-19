class RangeList extends IntegerList {
    private int lowerBound;
    private int upperBound;
    public RangeList() {
        super();
        lowerBound = Integer.MIN_VALUE;
        upperBound = Integer.MAX_VALUE;
    }

    public RangeList(int lowerBound, int upperBound) {
        super();
        if (lowerBound > upperBound) {
            throw new IllegalArgumentException("The upper bound must be greater than or equal to the lower bound.");
        }
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
    }
    public void add(int integer) {
        if (integer > lowerBound || integer < upperBound) {
            return;
        }
        super.add(integer);
    }
    public void add(int lowerBound, int upperBound) {
        if (lowerBound > upperBound) {
            throw new IllegalArgumentException("The upper bound must be greater than or equal to the lower bound.");
        }
        for (int i = lowerBound; i <= upperBound; i++) {
            add(i);
        }
    }
    public void remove(int lowerBound, int upperBound) {
        if (lowerBound > upperBound) {
            throw new IllegalArgumentException("The upper bound must be greater than or equal to the lower bound.");
        }
        int i = 0;
        while (i < size) {
            int current = get(i);
            if (current >= lowerBound && current <= upperBound) {
                remove(i);
            } else {
                i++;
            }
        }
    }
    public void insert(int index, int integer) {
        if (integer < lowerBound || integer > upperBound) {
            return;
        }
        super.insert(index, integer);
    }
}
