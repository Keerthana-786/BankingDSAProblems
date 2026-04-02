import java.util.ArrayList;
import java.util.List;

public class Problem1 {

    static class Transaction {
        String id;
        double fee;
        String timestamp;

        Transaction(String id, double fee, String timestamp) {
            this.id = id;
            this.fee = fee;
            this.timestamp = timestamp;
        }

        public String toString() {
            return id + ":$" + fee + "@" + timestamp;
        }
    }

    // Bubble Sort by fee ascending
    static void bubbleSort(List<Transaction> list) {
        int n = list.size();
        int passes = 0, swaps = 0;
        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (list.get(j).fee > list.get(j + 1).fee) {
                    Transaction temp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, temp);
                    swaps++;
                    swapped = true;
                }
            }
            passes++;
            if (!swapped) break; // early termination
        }
        System.out.println("BubbleSort passes: " + passes + ", swaps: " + swaps);
    }

    // Insertion Sort by fee + timestamp
    static void insertionSort(List<Transaction> list) {
        int n = list.size();
        for (int i = 1; i < n; i++) {
            Transaction key = list.get(i);
            int j = i - 1;
            while (j >= 0 && (list.get(j).fee > key.fee ||
                   (list.get(j).fee == key.fee && list.get(j).timestamp.compareTo(key.timestamp) > 0))) {
                list.set(j + 1, list.get(j));
                j--;
            }
            list.set(j + 1, key);
        }
    }

    // Flag high-fee outliers
    static void flagOutliers(List<Transaction> list) {
        System.out.println("High-fee outliers (> $50):");
        boolean found = false;
        for (Transaction t : list) {
            if (t.fee > 50) {
                System.out.println("  [OUTLIER] " + t);
                found = true;
            }
        }
        if (!found) System.out.println("  None found.");
    }

    public static void main(String[] args) {
        System.out.println("=== Problem 1: Transaction Fee Sorting ===");

        List<Transaction> batch = new ArrayList<>();
        batch.add(new Transaction("id1", 10.5, "10:00"));
        batch.add(new Transaction("id2", 25.0, "09:30"));
        batch.add(new Transaction("id3", 5.0,  "10:15"));
        batch.add(new Transaction("id4", 55.0, "08:45"));
        batch.add(new Transaction("id5", 10.5, "09:00"));

        System.out.println("\nOriginal: " + batch);

        // Bubble Sort copy
        List<Transaction> bubbleList = new ArrayList<>(batch);
        bubbleSort(bubbleList);
        System.out.println("BubbleSort (fee asc): " + bubbleList);

        // Insertion Sort copy
        List<Transaction> insertList = new ArrayList<>(batch);
        insertionSort(insertList);
        System.out.println("InsertionSort (fee+ts): " + insertList);

        flagOutliers(bubbleList);
    }
}
