public class SortedList extends IntegerList {

    public SortedList() {
        super();
    }

    public SortedList(int capacity) {
        super(capacity);
    }

    @Override
    public void add(int integer) {
        int index = 0;
        while (index < size && array[index] < integer) {
            index++;
        }
        super.insert(index, integer);
    }

    @Override
    public void insert(int index, int integer) {
        throw new UnsupportedOperationException("Insertion at arbitrary positions is not allowed in SortedList.");
    }
}