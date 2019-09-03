import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class ProgrammingAssignment1 {

    public static void main(String[] args) {
	// write your code here

        if (args.length != 2) {
            System.out.println("Please enter a valid input");
        }
        else {
            String firstList = args[0];
            String secondList = args[1];

            List<String> firstValues = Arrays.asList(firstList.split(","));
            List<String> secondValues = Arrays.asList(secondList.split(","));

            Iterator<String> firstIterator = firstValues.iterator();
            Iterator<String> secondIterator = secondValues.iterator();

            List<Integer> firstIntegerValues = new ArrayList<Integer>();
            List<Integer> secondIntegerValues = new ArrayList<Integer>();

            while (firstIterator.hasNext()) {
                String value = firstIterator.next();
                int integerValue = Integer.parseInt(value);
                firstIntegerValues.add(integerValue);
            }

            while (secondIterator.hasNext()) {
                String value = secondIterator.next();
                int integerValue = Integer.parseInt(value);
                secondIntegerValues.add(integerValue);
            }

            System.out.println(longestSmallerPrefix(firstIntegerValues, secondIntegerValues));
        }



    }

     static List<Integer> longestSmallerPrefix(List<Integer> a, List<Integer> b) {
        List<Integer> result = new ArrayList<>();

        Iterator<Integer> aIterator = a.iterator();
        Iterator<Integer> bIterator = b.iterator();

        while (aIterator.hasNext() && bIterator.hasNext()) {
            int aValue = aIterator.next();
            int bValue = bIterator.next();

            //if using generics change how values are compared
            if (aValue <= bValue) {
                result.add(aValue);
            }
            else {
                break;
            }

        }

        return result;
    }
}
