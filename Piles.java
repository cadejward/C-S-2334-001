import java.util.Arrays;

public class Piles {
    private int[] sizes;

    public Piles(int... initSizes) {
        if (initSizes == null || initSizes.length == 0) {
            throw new IllegalArgumentException("Array reference is null or has zero length.");
        }

        for (int size : initSizes) {
            if (size <= 0) {
                throw new IllegalArgumentException("Nonpositive object number: " + size);
            }
        }

        this.sizes = Arrays.copyOf(initSizes, initSizes.length);
    }

    public int[] getSizes() {
        return Arrays.copyOf(sizes, sizes.length);
    }

    public void removeObjects(int[] move) throws IllegalMoveException {
        if (move == null) {
            throw new IllegalMoveException("null move");
        }

        if (move.length != 2) {
            throw new IllegalMoveException("Invalid length: " + move.length);
        }

        int pileIndex = move[0];
        int numObjects = move[1];

        if (pileIndex < 0 || pileIndex >= sizes.length) {
            throw new IllegalMoveException("Index out of bounds: " + pileIndex);
        }

        if (sizes[pileIndex] == 0) {
            throw new IllegalMoveException("Pile " + pileIndex + " is empty.");
        }

        if (numObjects <= 0) {
            throw new IllegalMoveException("Nonpositive object number: " + numObjects);
        }

        if (numObjects > sizes[pileIndex]) {
            throw new IllegalMoveException("Object number greater than pile size: " + numObjects + " > " + sizes[pileIndex]);
        }

        sizes[pileIndex] -= numObjects;
    }

    public boolean isEmpty() {
        for (int size : sizes) {
            if (size > 0) {
                return false;
            }
        }
        return true;
    }
}
