import org.junit.Test;
import static org.junit.Assert.*;

public class Problem4Test {

    // Helper - merge sort ascending by returnRate
    void mergeSort(Problem4.Asset[] arr, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);
            merge(arr, left, mid, right);
        }
    }

    void merge(Problem4.Asset[] arr, int left, int mid, int right) {
        int n1 = mid - left + 1, n2 = right - mid;
        Problem4.Asset[] L = new Problem4.Asset[n1];
        Problem4.Asset[] R = new Problem4.Asset[n2];
        for (int i = 0; i < n1; i++) L[i] = arr[left + i];
        for (int j = 0; j < n2; j++) R[j] = arr[mid + 1 + j];
        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            if (L[i].returnRate <= R[j].returnRate) arr[k++] = L[i++];
            else arr[k++] = R[j++];
        }
        while (i < n1) arr[k++] = L[i++];
        while (j < n2) arr[k++] = R[j++];
    }

    // Test 1: Merge sort ascending returnRate
    @Test
    public void testMergeSortAscendingReturn() {
        Problem4.Asset[] assets = {
            new Problem4.Asset("AAPL", 12.0, 0.3),
            new Problem4.Asset("TSLA", 8.0,  0.7),
            new Problem4.Asset("GOOG", 15.0, 0.4)
        };
        mergeSort(assets, 0, assets.length - 1);
        assertEquals(8.0,  assets[0].returnRate, 0.001);
        assertEquals(12.0, assets[1].returnRate, 0.001);
        assertEquals(15.0, assets[2].returnRate, 0.001);
    }

    // Test 2: Stable sort - ties preserve order
    @Test
    public void testMergeSortStableOnTies() {
        Problem4.Asset[] assets = {
            new Problem4.Asset("AAPL", 12.0, 0.3),
            new Problem4.Asset("AMZN", 12.0, 0.2),
            new Problem4.Asset("TSLA", 8.0,  0.7)
        };
        mergeSort(assets, 0, assets.length - 1);
        assertEquals(8.0,  assets[0].returnRate, 0.001);
        assertEquals(12.0, assets[1].returnRate, 0.001);
        assertEquals(12.0, assets[2].returnRate, 0.001);
        // AAPL should come before AMZN (stable)
        assertEquals("AAPL", assets[1].name);
        assertEquals("AMZN", assets[2].name);
    }

    // Test 3: Single asset
    @Test
    public void testSingleAsset() {
        Problem4.Asset[] assets = { new Problem4.Asset("AAPL", 12.0, 0.3) };
        mergeSort(assets, 0, 0);
        assertEquals(12.0, assets[0].returnRate, 0.001);
    }

    // Test 4: Highest return is last after asc sort
    @Test
    public void testHighestReturnLast() {
        Problem4.Asset[] assets = {
            new Problem4.Asset("AAPL", 12.0, 0.3),
            new Problem4.Asset("TSLA", 8.0,  0.7),
            new Problem4.Asset("GOOG", 15.0, 0.4)
        };
        mergeSort(assets, 0, assets.length - 1);
        assertEquals("GOOG", assets[assets.length - 1].name);
    }

    // Test 5: Lowest return is first after asc sort
    @Test
    public void testLowestReturnFirst() {
        Problem4.Asset[] assets = {
            new Problem4.Asset("AAPL", 12.0, 0.3),
            new Problem4.Asset("TSLA", 8.0,  0.7),
            new Problem4.Asset("GOOG", 15.0, 0.4)
        };
        mergeSort(assets, 0, assets.length - 1);
        assertEquals("TSLA", assets[0].name);
    }

    // Test 6: Negative return rates
    @Test
    public void testNegativeReturnRates() {
        Problem4.Asset[] assets = {
            new Problem4.Asset("A", -5.0, 0.5),
            new Problem4.Asset("B", -1.0, 0.3),
            new Problem4.Asset("C", -10.0, 0.8)
        };
        mergeSort(assets, 0, assets.length - 1);
        assertEquals(-10.0, assets[0].returnRate, 0.001);
        assertEquals(-1.0,  assets[2].returnRate, 0.001);
    }

    // Test 7: Two assets
    @Test
    public void testTwoAssets() {
        Problem4.Asset[] assets = {
            new Problem4.Asset("B", 20.0, 0.4),
            new Problem4.Asset("A", 10.0, 0.2)
        };
        mergeSort(assets, 0, assets.length - 1);
        assertEquals(10.0, assets[0].returnRate, 0.001);
        assertEquals(20.0, assets[1].returnRate, 0.001);
    }

    // Test 8: All same return rates
    @Test
    public void testAllSameReturnRates() {
        Problem4.Asset[] assets = {
            new Problem4.Asset("A", 10.0, 0.1),
            new Problem4.Asset("B", 10.0, 0.2),
            new Problem4.Asset("C", 10.0, 0.3)
        };
        mergeSort(assets, 0, assets.length - 1);
        assertEquals(10.0, assets[0].returnRate, 0.001);
        assertEquals(10.0, assets[2].returnRate, 0.001);
    }
}
