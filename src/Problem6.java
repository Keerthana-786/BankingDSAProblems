import java.util.Arrays;

public class Problem6 {

    // Linear Search for threshold
    static int linearSearch(int[] bands, int target) {
        int comparisons = 0;
        for (int i = 0; i < bands.length; i++) {
            comparisons++;
            if (bands[i] == target) {
                System.out.println("  Linear: found " + target + " at index " + i +
                                   " (" + comparisons + " comparisons)");
                return i;
            }
        }
        System.out.println("  Linear: " + target + " not found (" + comparisons + " comparisons)");
        return -1;
    }

    // Binary Search - floor (largest value <= target)
    static int floorBinary(int[] bands, int target) {
        int low = 0, high = bands.length - 1, result = -1, comparisons = 0;
        while (low <= high) {
            int mid = (low + high) / 2;
            comparisons++;
            if (bands[mid] <= target) {
                result = bands[mid];
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        System.out.println("  Binary floor(" + target + "): " + result +
                           " (" + comparisons + " comparisons)");
        return result;
    }

    // Binary Search - ceiling (smallest value >= target)
    static int ceilingBinary(int[] bands, int target) {
        int low = 0, high = bands.length - 1, result = -1, comparisons = 0;
        while (low <= high) {
            int mid = (low + high) / 2;
            comparisons++;
            if (bands[mid] >= target) {
                result = bands[mid];
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        System.out.println("  Binary ceiling(" + target + "): " + result +
                           " (" + comparisons + " comparisons)");
        return result;
    }

    // Binary insertion point
    static int insertionPoint(int[] bands, int target) {
        int low = 0, high = bands.length;
        while (low < high) {
            int mid = (low + high) / 2;
            if (bands[mid] < target) low = mid + 1;
            else high = mid;
        }
        return low;
    }

    public static void main(String[] args) {
        System.out.println("=== Problem 6: Risk Threshold Binary Lookup ===");

        int[] riskBands = {10, 25, 50, 100};
        System.out.println("\nSorted risk bands: " + Arrays.toString(riskBands));

        System.out.println("\n-- Threshold = 30 --");
        linearSearch(riskBands, 30);
        floorBinary(riskBands, 30);
        ceilingBinary(riskBands, 30);
        System.out.println("  Insertion point for 30: index " + insertionPoint(riskBands, 30));

        System.out.println("\n-- Threshold = 25 (exact match) --");
        linearSearch(riskBands, 25);
        floorBinary(riskBands, 25);
        ceilingBinary(riskBands, 25);

        System.out.println("\n-- Threshold = 5 (below all) --");
        floorBinary(riskBands, 5);
        ceilingBinary(riskBands, 5);

        System.out.println("\n-- Threshold = 150 (above all) --");
        floorBinary(riskBands, 150);
        ceilingBinary(riskBands, 150);
    }
}
