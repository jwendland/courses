import java.util.*;

public class QuickSorter<T extends Comparable<T>> implements Sorter<T> {

    public List<T> sort(List<T> input) {
        if (input.size() <= 1) {
            return input;
        }

        List<T> left = new ArrayList<T>();
        List<T> right = new ArrayList<T>();

        System.out.println("A : size() == " + input.size());
        int size = input.size();
        int pivotIdx = size / 2;
        T pivotElem = input.get(pivotIdx);
        System.out.println("B : pivot == " + pivotElem);
        for (int i = 0; i < size; i++) {
            if (i == pivotIdx) {
                continue;
            }
            T elem = input.get(i);
            if (elem.compareTo(pivotElem) < 0) {
                System.out.println("C1: left.add " + elem);
                left.add(elem);
            } else {
                System.out.println("C2: right.add " + elem);
                right.add(elem);
            }
        }


        List<T> retval = sort(left);
        retval.add(pivotElem);
        retval.addAll(sort(right));
        return retval;
    }

}
