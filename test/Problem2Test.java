import org.junit.Test;
import static org.junit.Assert.*;

public class Problem2Test {

    // Helper - bubble sort
    Problem2.Client[] bubbleSort(Problem2.Client[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j].riskScore > arr[j + 1].riskScore) {
                    Problem2.Client temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
        return arr;
    }

    // Helper - insertion sort descending
    Problem2.Client[] insertionSort(Problem2.Client[] arr) {
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            Problem2.Client key = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j].riskScore < key.riskScore) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
        return arr;
    }

    // Test 1: Bubble sort ascending
    @Test
    public void testBubbleSortAscending() {
        Problem2.Client[] clients = {
            new Problem2.Client("C", 80, 15000),
            new Problem2.Client("A", 20, 5000),
            new Problem2.Client("B", 50, 12000)
        };
        bubbleSort(clients);
        assertEquals(20, clients[0].riskScore);
        assertEquals(50, clients[1].riskScore);
        assertEquals(80, clients[2].riskScore);
    }

    // Test 2: Insertion sort descending
    @Test
    public void testInsertionSortDescending() {
        Problem2.Client[] clients = {
            new Problem2.Client("C", 80, 15000),
            new Problem2.Client("A", 20, 5000),
            new Problem2.Client("B", 50, 12000)
        };
        insertionSort(clients);
        assertEquals(80, clients[0].riskScore);
        assertEquals(50, clients[1].riskScore);
        assertEquals(20, clients[2].riskScore);
    }

    // Test 3: Top 1 highest risk after sort
    @Test
    public void testTopHighestRisk() {
        Problem2.Client[] clients = {
            new Problem2.Client("C", 80, 15000),
            new Problem2.Client("A", 20, 5000),
            new Problem2.Client("B", 50, 12000)
        };
        insertionSort(clients);
        assertEquals("C", clients[0].name);
        assertEquals(80, clients[0].riskScore);
    }

    // Test 4: Single client
    @Test
    public void testSingleClient() {
        Problem2.Client[] clients = {
            new Problem2.Client("A", 60, 10000)
        };
        bubbleSort(clients);
        assertEquals(1, clients.length);
        assertEquals(60, clients[0].riskScore);
    }

    // Test 5: All same risk scores
    @Test
    public void testAllSameRiskScores() {
        Problem2.Client[] clients = {
            new Problem2.Client("A", 50, 5000),
            new Problem2.Client("B", 50, 8000),
            new Problem2.Client("C", 50, 3000)
        };
        bubbleSort(clients);
        assertEquals(50, clients[0].riskScore);
        assertEquals(50, clients[1].riskScore);
        assertEquals(50, clients[2].riskScore);
    }

    // Test 6: Already sorted ascending
    @Test
    public void testAlreadySortedAscending() {
        Problem2.Client[] clients = {
            new Problem2.Client("A", 10, 1000),
            new Problem2.Client("B", 20, 2000),
            new Problem2.Client("C", 30, 3000)
        };
        bubbleSort(clients);
        assertEquals(10, clients[0].riskScore);
        assertEquals(30, clients[2].riskScore);
    }

    // Test 7: Reverse sorted input
    @Test
    public void testReverseSortedInput() {
        Problem2.Client[] clients = {
            new Problem2.Client("C", 90, 9000),
            new Problem2.Client("B", 60, 6000),
            new Problem2.Client("A", 30, 3000)
        };
        bubbleSort(clients);
        assertEquals(30, clients[0].riskScore);
        assertEquals(90, clients[2].riskScore);
    }

    // Test 8: Top 3 names after descending sort
    @Test
    public void testTop3Names() {
        Problem2.Client[] clients = {
            new Problem2.Client("D", 95, 1000),
            new Problem2.Client("C", 80, 2000),
            new Problem2.Client("A", 20, 3000),
            new Problem2.Client("B", 50, 4000)
        };
        insertionSort(clients);
        assertEquals("D", clients[0].name);
        assertEquals("C", clients[1].name);
        assertEquals("B", clients[2].name);
    }
}
