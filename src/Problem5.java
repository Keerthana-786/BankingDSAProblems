import java.util.Arrays;

public class Problem5 {

    static class TransactionLog {
        String accountId;
        String details;

        TransactionLog(String accountId, String details) {
            this.accountId = accountId;
            this.details = details;
        }

        public String toString() {
            return accountId;
        }
    }

    // Linear Search - first occurrence
    static int linearSearchFirst(TransactionLog[] logs, String target) {
        int comparisons = 0;
        for (int i = 0; i < logs.length; i++) {
            comparisons++;
            if (logs[i].accountId.equals(target)) {
                System.out.println("  Linear first '" + target + "': index " + i +
                                   " (" + comparisons + " comparisons)");
                return i;
            }
        }
        System.out.println("  Linear: '" + target + "' not found (" + comparisons + " comparisons)");
        return -1;
    }

    // Linear Search - last occurrence
    static int linearSearchLast(TransactionLog[] logs, String target) {
        int comparisons = 0, lastIdx = -1;
        for (int i = 0; i < logs.length; i++) {
            comparisons++;
            if (logs[i].accountId.equals(target)) lastIdx = i;
        }
        System.out.println("  Linear last '" + target + "': index " + lastIdx +
                           " (" + comparisons + " comparisons)");
        return lastIdx;
    }

    // Binary Search - exact match
    static int binarySearch(TransactionLog[] logs, String target) {
        int low = 0, high = logs.length - 1, comparisons = 0;
        while (low <= high) {
            int mid = (low + high) / 2;
            comparisons++;
            int cmp = logs[mid].accountId.compareTo(target);
            if (cmp == 0) {
                System.out.println("  Binary '" + target + "': index " + mid +
                                   " (" + comparisons + " comparisons)");
                return mid;
            } else if (cmp < 0) low = mid + 1;
            else high = mid - 1;
        }
        System.out.println("  Binary: '" + target + "' not found (" + comparisons + " comparisons)");
        return -1;
    }

    // Count occurrences using binary search bounds
    static int countOccurrences(TransactionLog[] logs, String target) {
        int first = lowerBound(logs, target);
        int last  = upperBound(logs, target);
        return last - first;
    }

    static int lowerBound(TransactionLog[] logs, String target) {
        int low = 0, high = logs.length;
        while (low < high) {
            int mid = (low + high) / 2;
            if (logs[mid].accountId.compareTo(target) < 0) low = mid + 1;
            else high = mid;
        }
        return low;
    }

    static int upperBound(TransactionLog[] logs, String target) {
        int low = 0, high = logs.length;
        while (low < high) {
            int mid = (low + high) / 2;
            if (logs[mid].accountId.compareTo(target) <= 0) low = mid + 1;
            else high = mid;
        }
        return low;
    }

    public static void main(String[] args) {
        System.out.println("=== Problem 5: Account ID Lookup in Transaction Logs ===");

        TransactionLog[] logs = {
            new TransactionLog("accA", "txn001"),
            new TransactionLog("accB", "txn002"),
            new TransactionLog("accB", "txn003"),
            new TransactionLog("accC", "txn004"),
            new TransactionLog("accD", "txn005")
        };

        // Already sorted for binary search
        System.out.println("\nLogs: " + Arrays.toString(logs));

        System.out.println("\n-- Linear Search --");
        linearSearchFirst(logs, "accB");
        linearSearchLast(logs, "accB");

        System.out.println("\n-- Binary Search --");
        binarySearch(logs, "accB");
        System.out.println("  Count of 'accB': " + countOccurrences(logs, "accB"));

        linearSearchFirst(logs, "accX"); // not found
        binarySearch(logs, "accX");      // not found
    }
}
