package ie.logn.utils.container;

public class Tuple<X, Y> {
    private final X first;

    private final Y second;

    public Tuple(X first, Y second) {
        this.first = first;
        this.second = second;
    }

    public final X getFirst() {
        return first;
    }

    public final Y getSecond() {
        return second;
    }
}
