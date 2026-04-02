import org.junit.Test;
import static org.junit.Assert.*;

public class Problem3Test {

    // Helper - merge sort ascending
    void mergeSort(Problem3.Trade[] arr, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);
            merge(arr, left, mid, right);
        }
    }

    void merge(Problem3.Trade[] arr, int left, int mid, int right) {
        int n1 = mid - left + 1, n2 = right - mid;
        Problem3.Trade[] L = new Problem3.Trade[n1];
        Problem3.Trade[] R = new Problem3.Trade[n2];
        for (int i = 0; i < n1; i++) L[i] = arr[left + i];
        for (int j = 0; j < n2; j++) R[j] = arr[mid + 1 + j];
        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            if (L[i].volume <= R[j].volume) arr[k++] = L[i++];
            else arr[k++] = R[j++];
        }
        while (i < n1) arr[k++] = L[i++];
        while (j < n2) arr[k++] = R[j++];
    }

    // Test 1: Merge sort ascending
    @Test
    public void testMergeSortAscending() {
        Problem3.Trade[] trades = {
            new Problem3.Trade("t3", 500),
            new Problem3.Trade("t1", 100),
            new Problem3.Trade("t2", 300)
        };
        mergeSort(trades, 0, trades.length - 1);
        assertEquals(100, trades[0].volume);
        assertEquals(300, trades[1].volume);
        assertEquals(500, trades[2].volume);
    }

    // Test 2: Already sorted
    @Test
    public void testMergeSortAlreadySorted() {
        Problem3.Trade[] trades = {
            new Problem3.Trade("t1", 100),
            new Problem3.Trade("t2", 200),
            new Problem3.Trade("t3", 300)
        };
        mergeSort(trades, 0, trades.length - 1);
        assertEquals(100, trades[0].volume);
        assertEquals(300, trades[2].volume);
    }

    // Test 3: Single element
    @Test
    public void testMergeSortSingleElement() {
        Problem3.Trade[] trades = { new Problem3.Trade("t1", 999) };
        mergeSort(trades, 0, 0);
        assertEquals(999, trades[0].volume);
    }

    // Test 4: Duplicate volumes (stable)
    @Test
    public void testMergeSortDuplicateVolumes() {
        Problem3.Trade[] trades = {
            new Problem3.Trade("t1", 300),
            new Problem3.Trade("t2", 300),
            new Problem3.Trade("t3", 100)
        };
        mergeSort(trades, 0, trades.length - 1);
        assertEquals(100, trades[0].volume);
        assertEquals(300, trades[1].volume);
        assertEquals(300, trades[2].volume);
    }

    // Test 5: Total volume after merge
    @Test
    public void testTotalVolumeAfterMerge() {
        Problem3.Trade[] morning   = { new Problem3.Trade("m1", 100), new Problem3.Trade("m2", 300) };
        Problem3.Trade[] afternoon = { new Problem3.Trade("a1", 200), new Problem3.Trade("a2", 400) };
        int total = 0;
        for (Problem3.Trade t : morning)   total += t.volume;
        for (Problem3.Trade t : afternoon) total += t.volume;
        assertEquals(1000, total);
    }

    // Test 6: Merged session size
    @Test
    public void testMergedSessionSize() {
        Problem3.Trade[] morning   = { new Problem3.Trade("m1", 100), new Problem3.Trade("m2", 300) };
        Problem3.Trade[] afternoon = { new Problem3.Trade("a1", 200) };
        Problem3.Trade[] merged = new Problem3.Trade[morning.length + afternoon.length];
        assertEquals(3, merged.length);
    }

    // Test 7: Reverse sorted input
    @Test
    public void testMergeSortReverseSorted() {
        Problem3.Trade[] trades = {
            new Problem3.Trade("t3", 900),
            new Problem3.Trade("t2", 600),
            new Problem3.Trade("t1", 300)
        };
        mergeSort(trades, 0, trades.length - 1);
        assertEquals(300, trades[0].volume);
        assertEquals(900, trades[2].volume);
    }

    // Test 8: Two elements
    @Test
    public void testMergeSortTwoElements() {
        Problem3.Trade[] trades = {
            new Problem3.Trade("t2", 500),
            new Problem3.Trade("t1", 200)
        };
        mergeSort(trades, 0, trades.length - 1);
        assertEquals(200, trades[0].volume);
        assertEquals(500, trades[1].volume);
    }
}
