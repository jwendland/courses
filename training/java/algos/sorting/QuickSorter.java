import java.util.*;

public class QuickSorter<T extends Comparable<T>> implements Sorter<T> {

    public List<T> sort(List<T> input) {
        if (input.size() <= 1) {
            return input;
        }

        List<T> left = new ArrayList<T>();
        List<T> right = new ArrayList<T>();

        int size = input.size();
        int pivotIdx = size / 2;
        T pivotElem = input.get(pivotIdx);
        for (int i = 0; i < size; i++) {
            if (i == pivotIdx) {
                continue;
            }
            T elem = input.get(i);
            if (elem.compareTo(pivotElem) < 0) {
                left.add(elem);
            } else {
                right.add(elem);
            }
        }


        List<T> retval = sort(left);
        retval.add(pivotElem);
        retval.addAll(sort(right));
        return retval;
    }

}
