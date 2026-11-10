package lw04.unguided;

import java.util.*;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(
            Main.class.getResourceAsStream("parcels.txt")
        );

        int N = scanner.nextInt();
        int[] parcels = new int[N];

        for (int i = 0; i < N; i++) {
            parcels[i] = scanner.nextInt();
        }

        int M = scanner.nextInt();
        int[] targets = new int[M];

        for (int i = 0; i < M; i++) {
            targets[i] = scanner.nextInt();
        }

        scanner.close();

        // Bubble Sort - Descending (N - 1 Passes)
        int[] afterTwoPasses = new int[N];

        for (int i = 0; i < N - 1; i++) {

            for (int j = 0; j < N - 1 - i; j++) {
                if (parcels[j] < parcels[j + 1]) {
                    int temp = parcels[j];
                    parcels[j] = parcels[j + 1];
                    parcels[j + 1] = temp;
                }
            }

            // Save a copy right after the 2nd pass (battery dies)
            if (i == 1) {
                for (int k = 0; k < N; k++) {
                    afterTwoPasses[k] = parcels[k];
                }
            }
        }

        // Display shelf after 2 passes
        System.out.println("==== After 2 Passes ====");
        for (int i = 0; i < N; i++) {
            System.out.print(afterTwoPasses[i]);

            if (i < N - 1) {
                System.out.print(" ");
            }
        }

        System.out.println();

        // Display fully sorted shelf
        System.out.println("==== Fully Sorted ====");
        for (int i = 0; i < N; i++) {
            System.out.print(parcels[i]);

            if (i < N - 1) {
                System.out.print(" ");
            }
        }

        System.out.println();

        // Binary Search (Descending)
        System.out.println("==== Search Results ====");
        for (int i = 0; i < M; i++) {

            int low = 0;
            int high = N - 1;
            int index = -1;
            int steps = 0;

            while (low <= high) {
                int mid = (low + high) / 2;
                steps++;

                if (parcels[mid] == targets[i]) {
                    index = mid;
                    break;
                } else if (parcels[mid] < targets[i]) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }

            if (index != -1) {
                System.out.println(
                    targets[i] + ": FOUND at index " + index
                    + " (steps: " + steps + ")"
                );
            } else {
                System.out.println(
                    targets[i] + ": NOT FOUND (steps: " + steps + ")"
                );
            }
        }
    }
}