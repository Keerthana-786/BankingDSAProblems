import org.junit.Test;
import static org.junit.Assert.*;

public class Problem6Test {

    int[] riskBands = {10, 25, 50, 100};

    // Helper - linear search
    int linearSearch(int[] bands, int target) {
        for (int i = 0; i < bands.length; i++)
            if (bands[i] == target) return i;
        return -1;
    }

    // Helper - floor binary search
    int floorBinary(int[] bands, int target) {
        int low = 0, high = bands.length - 1, result = -1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (bands[mid] <= target) { result = bands[mid]; low = mid + 1; }
            else high = mid - 1;
        }
        return result;
    }

    // Helper - ceiling binary search
    int ceilingBinary(int[] bands, int target) {
        int low = 0, high = bands.length - 1, result = -1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (bands[mid] >= target) { result = bands[mid]; high = mid - 1; }
            else low = mid + 1;
        }
        return result;
    }

    // Test 1: Linear search - found exact
    @Test
    public void testLinearSearchFound() {
        assertNotEquals(-1, linearSearch(riskBands, 25));
    }

    // Test 2: Linear search - not found
    @Test
    public void testLinearSearchNotFound() {
        assertEquals(-1, linearSearch(riskBands, 30));
    }

    // Test 3: Floor - value between bands
    @Test
    public void testFloorBetweenBands() {
        assertEquals(25, floorBinary(riskBands, 30));
    }

    // Test 4: Floor - exact match
    @Test
    public void testFloorExactMatch() {
        assertEquals(25, floorBinary(riskBands, 25));
    }

    // Test 5: Floor - below all bands
    @Test
    public void testFloorBelowAll() {
        assertEquals(-1, floorBinary(riskBands, 5));
    }

    // Test 6: Ceiling - value between bands
    @Test
    public void testCeilingBetweenBands() {
        assertEquals(50, ceilingBinary(riskBands, 30));
    }

    // Test 7: Ceiling - exact match
    @Test
    public void testCeilingExactMatch() {
        assertEquals(25, ceilingBinary(riskBands, 25));
    }

    // Test 8: Ceiling - above all bands
    @Test
    public void testCeilingAboveAll() {
        assertEquals(-1, ceilingBinary(riskBands, 150));
    }
}
