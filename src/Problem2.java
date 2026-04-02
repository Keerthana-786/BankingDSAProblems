public class Problem2 {

    static class Client {
        String name;
        int riskScore;
        double accountBalance;

        Client(String name, int riskScore, double accountBalance) {
            this.name = name;
            this.riskScore = riskScore;
            this.accountBalance = accountBalance;
        }

        public String toString() {
            return name + "(risk=" + riskScore + ", bal=$" + accountBalance + ")";
        }
    }

    // Bubble Sort ascending by riskScore
    static int bubbleSort(Client[] arr) {
        int n = arr.length, swaps = 0;
        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j].riskScore > arr[j + 1].riskScore) {
                    Client temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swaps++;
                    swapped = true;
                }
            }
            if (!swapped) break;
        }
        return swaps;
    }

    // Insertion Sort descending by riskScore, then ascending by balance
    static void insertionSort(Client[] arr) {
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            Client key = arr[i];
            int j = i - 1;
            while (j >= 0 && (arr[j].riskScore < key.riskScore ||
                   (arr[j].riskScore == key.riskScore && arr[j].accountBalance > key.accountBalance))) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

    static void printArray(Client[] arr) {
        for (Client c : arr) System.out.print("  " + c);
        System.out.println();
    }

    public static void main(String[] args) {
        System.out.println("=== Problem 2: Client Risk Score Ranking ===");

        Client[] clients = {
            new Client("clientC", 80, 15000),
            new Client("clientA", 20, 5000),
            new Client("clientB", 50, 12000),
            new Client("clientD", 95, 3000),
            new Client("clientE", 35, 8000)
        };

        // Bubble Sort
        Client[] bubbleArr = clients.clone();
        int swaps = bubbleSort(bubbleArr);
        System.out.println("\nBubble Sort (risk asc), Swaps: " + swaps);
        printArray(bubbleArr);

        // Insertion Sort
        Client[] insertArr = clients.clone();
        insertionSort(insertArr);
        System.out.println("Insertion Sort (risk desc + balance asc):");
        printArray(insertArr);

        // Top 3 highest risk
        System.out.println("Top 3 highest risk clients:");
        for (int i = 0; i < 3; i++)
            System.out.println("  " + (i + 1) + ". " + insertArr[i]);
    }
}
