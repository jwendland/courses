import java.util.*;


public class SorterTest {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<Integer>() {{
            add(8);
            add(9);
            add(2);
            add(6);
            add(3);
            add(1);
            add(4);
            add(5);
            add(5);
            add(3);
            add(1);
            add(7);
            add(6);
            add(0);
        }};
        Sorter<Integer> sorter = new QuickSorter<Integer>();
        for (Integer i: sorter.sort(list)) {
            System.out.println(i);
        }
    }

}
