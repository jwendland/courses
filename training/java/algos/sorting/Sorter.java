import java.util.List;

public interface Sorter<T extends Comparable<T>> {
    public List<T> sort(List<T> input);
}
