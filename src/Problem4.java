import java.util.Arrays;
import java.util.Random;

public class Problem4 {

    static class Asset {
        String name;
        double returnRate;
        double volatility;

        Asset(String name, double returnRate, double volatility) {
            this.name = name;
            this.returnRate = returnRate;
            this.volatility = volatility;
        }

        public String toString() {
            return name + "(ret=" + returnRate + "%, vol=" + volatility + ")";
        }
    }

    // Merge Sort by returnRate ascending (stable)
    static void mergeSort(Asset[] arr, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);
            merge(arr, left, mid, right);
        }
    }

    static void merge(Asset[] arr, int left, int mid, int right) {
        Asset[] temp = Arrays.copyOfRange(arr, left, right + 1);
        int i = 0, j = mid - left + 1, k = left;
        while (i <= mid - left && j <= right - left) {
            if (temp[i].returnRate <= temp[j].returnRate) arr[k++] = temp[i++];
            else arr[k++] = temp[j++];
        }
        while (i <= mid - left) arr[k++] = temp[i++];
        while (j <= right - left) arr[k++] = temp[j++];
    }

    // Quick Sort: returnRate DESC + volatility ASC, random pivot
    static void quickSort(Asset[] arr, int low, int high) {
        if (low < high) {
            if (high - low < 5) {
                insertionSort(arr, low, high); // hybrid for small partitions
                return;
            }
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    static int partition(Asset[] arr, int low, int high) {
        // Random pivot
        int pivotIdx = low + new Random().nextInt(high - low + 1);
        Asset temp = arr[pivotIdx]; arr[pivotIdx] = arr[high]; arr[high] = temp;
        Asset pivot = arr[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (arr[j].returnRate > pivot.returnRate ||
               (arr[j].returnRate == pivot.returnRate && arr[j].volatility < pivot.volatility)) {
                i++;
                Asset t = arr[i]; arr[i] = arr[j]; arr[j] = t;
            }
        }
        Asset t = arr[i + 1]; arr[i + 1] = arr[high]; arr[high] = t;
        return i + 1;
    }

    static void insertionSort(Asset[] arr, int low, int high) {
        for (int i = low + 1; i <= high; i++) {
            Asset key = arr[i];
            int j = i - 1;
            while (j >= low && arr[j].returnRate < key.returnRate) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

    public static void main(String[] args) {
        System.out.println("=== Problem 4: Portfolio Return Sorting ===");

        Asset[] assets = {
            new Asset("AAPL", 12.0, 0.3),
            new Asset("TSLA", 8.0,  0.7),
            new Asset("GOOG", 15.0, 0.4),
            new Asset("AMZN", 12.0, 0.2)
        };

        // Merge Sort
        Asset[] mergeArr = assets.clone();
        mergeSort(mergeArr, 0, mergeArr.length - 1);
        System.out.println("\nMergeSort (return asc, stable): " + Arrays.toString(mergeArr));

        // Quick Sort
        Asset[] quickArr = assets.clone();
        quickSort(quickArr, 0, quickArr.length - 1);
        System.out.println("QuickSort (return desc + vol asc): " + Arrays.toString(quickArr));
    }
}
