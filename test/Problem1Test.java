import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Problem1Test {

    // Helper - bubble sort
    List<Problem1.Transaction> bubbleSort(List<Problem1.Transaction> list) {
        int n = list.size();
        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (list.get(j).fee > list.get(j + 1).fee) {
                    Problem1.Transaction temp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, temp);
                    swapped = true;
                }
            }
            if (!swapped) break;
        }
        return list;
    }

    // Test 1: Bubble sort ascending order
    @Test
    public void testBubbleSortAscending() {
        List<Problem1.Transaction> list = new ArrayList<>(Arrays.asList(
            new Problem1.Transaction("id1", 10.5, "10:00"),
            new Problem1.Transaction("id2", 25.0, "09:30"),
            new Problem1.Transaction("id3", 5.0,  "10:15")
        ));
        bubbleSort(list);
        assertEquals(5.0,  list.get(0).fee, 0.001);
        assertEquals(10.5, list.get(1).fee, 0.001);
        assertEquals(25.0, list.get(2).fee, 0.001);
    }

    // Test 2: Already sorted list
    @Test
    public void testBubbleSortAlreadySorted() {
        List<Problem1.Transaction> list = new ArrayList<>(Arrays.asList(
            new Problem1.Transaction("id1", 5.0,  "10:00"),
            new Problem1.Transaction("id2", 10.0, "10:01"),
            new Problem1.Transaction("id3", 20.0, "10:02")
        ));
        bubbleSort(list);
        assertEquals(5.0,  list.get(0).fee, 0.001);
        assertEquals(10.0, list.get(1).fee, 0.001);
        assertEquals(20.0, list.get(2).fee, 0.001);
    }

    // Test 3: Single element
    @Test
    public void testBubbleSortSingleElement() {
        List<Problem1.Transaction> list = new ArrayList<>(Arrays.asList(
            new Problem1.Transaction("id1", 15.0, "10:00")
        ));
        bubbleSort(list);
        assertEquals(1, list.size());
        assertEquals(15.0, list.get(0).fee, 0.001);
    }

    // Test 4: Duplicate fees (stable)
    @Test
    public void testBubbleSortDuplicateFees() {
        List<Problem1.Transaction> list = new ArrayList<>(Arrays.asList(
            new Problem1.Transaction("id1", 10.5, "10:00"),
            new Problem1.Transaction("id2", 10.5, "09:00"),
            new Problem1.Transaction("id3", 5.0,  "11:00")
        ));
        bubbleSort(list);
        assertEquals(5.0,  list.get(0).fee, 0.001);
        assertEquals(10.5, list.get(1).fee, 0.001);
        assertEquals(10.5, list.get(2).fee, 0.001);
    }

    // Test 5: High fee outlier detected
    @Test
    public void testHighFeeOutlierDetected() {
        List<Problem1.Transaction> list = Arrays.asList(
            new Problem1.Transaction("id1", 10.5, "10:00"),
            new Problem1.Transaction("id2", 75.0, "09:30"),
            new Problem1.Transaction("id3", 5.0,  "10:15")
        );
        long outliers = list.stream().filter(t -> t.fee > 50).count();
        assertEquals(1, outliers);
    }

    // Test 6: No outliers
    @Test
    public void testNoHighFeeOutliers() {
        List<Problem1.Transaction> list = Arrays.asList(
            new Problem1.Transaction("id1", 10.5, "10:00"),
            new Problem1.Transaction("id2", 25.0, "09:30"),
            new Problem1.Transaction("id3", 5.0,  "10:15")
        );
        long outliers = list.stream().filter(t -> t.fee > 50).count();
        assertEquals(0, outliers);
    }

    // Test 7: Empty list
    @Test
    public void testBubbleSortEmptyList() {
        List<Problem1.Transaction> list = new ArrayList<>();
        bubbleSort(list);
        assertEquals(0, list.size());
    }

    // Test 8: Reverse sorted list
    @Test
    public void testBubbleSortReverseSorted() {
        List<Problem1.Transaction> list = new ArrayList<>(Arrays.asList(
            new Problem1.Transaction("id1", 30.0, "10:00"),
            new Problem1.Transaction("id2", 20.0, "10:01"),
            new Problem1.Transaction("id3", 10.0, "10:02")
        ));
        bubbleSort(list);
        assertEquals(10.0, list.get(0).fee, 0.001);
        assertEquals(20.0, list.get(1).fee, 0.001);
        assertEquals(30.0, list.get(2).fee, 0.001);
    }
}
