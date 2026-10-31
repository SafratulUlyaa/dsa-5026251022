package lw04.prelab;

import java.util.*;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(
            Main.class.getResourceAsStream("branches.txt")
        );

        int N = scanner.nextInt();
        int[] branches = new int[N];

        for (int i = 0; i < N; i++) {
            branches[i] = scanner.nextInt();
        }

        int M = scanner.nextInt();
        int[] targets = new int[M];

        for (int i = 0; i < M; i++) {
            targets[i] = scanner.nextInt();
        }

        scanner.close();

        // Insertion Sort - Descending (3 Passes)
        for (int i = 1; i <= 3; i++) {

            int key = branches[i];
            int j = i - 1;

            while (j >= 0 && branches[j] < key) {
                branches[j + 1] = branches[j];
                j--;
            }

            branches[j + 1] = key;
        }

        // Display branches after 3 passes
        for (int i = 0; i < N; i++) {
            System.out.print(branches[i]);

            if (i < N - 1) {
                System.out.print(" ");
            }
        }

        System.out.println();

        // Linear Search
        for (int i = 0; i < M; i++) {

            int index = -1;

            for (int j = 0; j < N; j++) {
                if (branches[j] == targets[i]) {
                    index = j;
                    break;
                }
            }

            if (index != -1) {
                System.out.println(
                    targets[i] + ": FOUND at index " + index
                );
            } else {
                System.out.println(
                    targets[i] + ": NOT FOUND"
                );
            }
        }
    }
}