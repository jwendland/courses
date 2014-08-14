import java.util.*;

public class RemoveDuplicates
{
    private static List<Integer> testList = new ArrayList<Integer> (){{
        add(10);
        add(1);
        add(5);
        add(10);
        add(2);
        add(8);
        add(10);
        add(5);
        add(7);
        add(6);
    }};

    public static void main(String args[]) {
        List<Integer> resultList = removeDuplicates(testList);
        for (Integer i: resultList) {
            System.out.println(i);
        }
    }


    public static List<Integer> removeDuplicates(List<Integer> list) {
        Set<Integer> retval = new LinkedHashSet<Integer>();

        for (Integer i: list) {
            retval.add(i);
        }
            
        return new ArrayList<Integer>(retval);
    }
}
