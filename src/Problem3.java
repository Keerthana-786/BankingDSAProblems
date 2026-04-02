import java.util.Arrays;

public class Problem3 {

    static class Trade {
        String id;
        int volume;

        Trade(String id, int volume) {
            this.id = id;
            this.volume = volume;
        }

        public String toString() {
            return id + ":" + volume;
        }
    }

    // Merge Sort ascending
    static void mergeSort(Trade[] arr, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);
            merge(arr, left, mid, right);
        }
    }

    static void merge(Trade[] arr, int left, int mid, int right) {
        Trade[] temp = Arrays.copyOfRange(arr, left, right + 1);
        int i = 0, j = mid - left + 1, k = left;
        while (i <= mid - left && j <= right - left) {
            if (temp[i].volume <= temp[j].volume) arr[k++] = temp[i++];
            else arr[k++] = temp[j++];
        }
        while (i <= mid - left) arr[k++] = temp[i++];
        while (j <= right - left) arr[k++] = temp[j++];
    }

    // Quick Sort descending
    static void quickSort(Trade[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    static int partition(Trade[] arr, int low, int high) {
        int pivot = arr[high].volume;
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (arr[j].volume >= pivot) { // descending
                i++;
                Trade temp = arr[i]; arr[i] = arr[j]; arr[j] = temp;
            }
        }
        Trade temp = arr[i + 1]; arr[i + 1] = arr[high]; arr[high] = temp;
        return i + 1;
    }

    // Merge two sorted arrays
    static Trade[] mergeSessions(Trade[] morning, Trade[] afternoon) {
        Trade[] merged = new Trade[morning.length + afternoon.length];
        int i = 0, j = 0, k = 0;
        while (i < morning.length && j < afternoon.length) {
            if (morning[i].volume <= afternoon[j].volume) merged[k++] = morning[i++];
            else merged[k++] = afternoon[j++];
        }
        while (i < morning.length) merged[k++] = morning[i++];
        while (j < afternoon.length) merged[k++] = afternoon[j++];
        return merged;
    }

    public static void main(String[] args) {
        System.out.println("=== Problem 3: Historical Trade Volume Analysis ===");

        Trade[] trades = {
            new Trade("trade3", 500),
            new Trade("trade1", 100),
            new Trade("trade2", 300)
        };

        // Merge Sort
        Trade[] mergeArr = trades.clone();
        mergeSort(mergeArr, 0, mergeArr.length - 1);
        System.out.println("\nMergeSort (asc): " + Arrays.toString(mergeArr));

        // Quick Sort
        Trade[] quickArr = trades.clone();
        quickSort(quickArr, 0, quickArr.length - 1);
        System.out.println("QuickSort (desc): " + Arrays.toString(quickArr));

        // Merge sessions
        Trade[] morning   = { new Trade("m1", 100), new Trade("m2", 300) };
        Trade[] afternoon = { new Trade("a1", 200), new Trade("a2", 400) };
        Trade[] merged = mergeSessions(morning, afternoon);
        int total = Arrays.stream(merged).mapToInt(t -> t.volume).sum();
        System.out.println("Merged sessions: " + Arrays.toString(merged));
        System.out.println("Total volume: " + total);
    }
}
