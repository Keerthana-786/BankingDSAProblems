import org.junit.Test;
import static org.junit.Assert.*;

public class Problem5Test {

    // Helper - linear search first
    int linearSearchFirst(Problem5.TransactionLog[] logs, String target) {
        for (int i = 0; i < logs.length; i++)
            if (logs[i].accountId.equals(target)) return i;
        return -1;
    }

    // Helper - binary search
    int binarySearch(Problem5.TransactionLog[] logs, String target) {
        int low = 0, high = logs.length - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            int cmp = logs[mid].accountId.compareTo(target);
            if (cmp == 0) return mid;
            else if (cmp < 0) low = mid + 1;
            else high = mid - 1;
        }
        return -1;
    }

    // Helper - count occurrences
    int countOccurrences(Problem5.TransactionLog[] logs, String target) {
        int count = 0;
        for (Problem5.TransactionLog log : logs)
            if (log.accountId.equals(target)) count++;
        return count;
    }

    Problem5.TransactionLog[] getSortedLogs() {
        return new Problem5.TransactionLog[]{
            new Problem5.TransactionLog("accA", "txn001"),
            new Problem5.TransactionLog("accB", "txn002"),
            new Problem5.TransactionLog("accB", "txn003"),
            new Problem5.TransactionLog("accC", "txn004"),
            new Problem5.TransactionLog("accD", "txn005")
        };
    }

    // Test 1: Linear search - found
    @Test
    public void testLinearSearchFound() {
        assertEquals(1, linearSearchFirst(getSortedLogs(), "accB"));
    }

    // Test 2: Linear search - not found
    @Test
    public void testLinearSearchNotFound() {
        assertEquals(-1, linearSearchFirst(getSortedLogs(), "accX"));
    }

    // Test 3: Linear search - first element
    @Test
    public void testLinearSearchFirstElement() {
        assertEquals(0, linearSearchFirst(getSortedLogs(), "accA"));
    }

    // Test 4: Linear search - last element
    @Test
    public void testLinearSearchLastElement() {
        assertEquals(4, linearSearchFirst(getSortedLogs(), "accD"));
    }

    // Test 5: Binary search - found
    @Test
    public void testBinarySearchFound() {
        assertNotEquals(-1, binarySearch(getSortedLogs(), "accB"));
    }

    // Test 6: Binary search - not found
    @Test
    public void testBinarySearchNotFound() {
        assertEquals(-1, binarySearch(getSortedLogs(), "accZ"));
    }

    // Test 7: Count occurrences - duplicate
    @Test
    public void testCountOccurrencesDuplicate() {
        assertEquals(2, countOccurrences(getSortedLogs(), "accB"));
    }

    // Test 8: Count occurrences - single
    @Test
    public void testCountOccurrencesSingle() {
        assertEquals(1, countOccurrences(getSortedLogs(), "accA"));
    }
}
