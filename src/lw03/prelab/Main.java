package lw03.prelab;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
        problem1();
        System.out.println();
        problem2();
        System.out.println();
        problem3();
    }

    // ===================== PROBLEM 1 — List =====================
    private static void problem1() {
        List<String> playlist = new ArrayList<>();

        Scanner scanner = new Scanner(
            Main.class.getResourceAsStream("playlist.txt")
        );
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();
            if (line.isEmpty()) continue;
            String[] parts = line.split("\\s+", 3);
            String op = parts[0];

            if (op.equals("ADD")) {
                String song = parts[1];
                playlist.add(song);
            } else if (op.equals("INSERT")) {
                int index = Integer.parseInt(parts[1]);
                String song = parts[2];
                playlist.add(index, song);
            } else if (op.equals("REMOVE")) {
                String song = parts[1];
                playlist.remove(song); // hapus kemunculan pertama
            }
        }
        scanner.close();

        System.out.println("===== Problem 1 =====");
        System.out.println("Total songs: " + playlist.size());
        for (int i = 0; i < playlist.size(); i++) {
            System.out.println((i + 1) + ": " + playlist.get(i));
        }
    }

    // ===================== PROBLEM 2 — Set =====================
    private static void problem2() {
        Set<String> participants = new LinkedHashSet<>();
        int duplicateCount = 0;

        Scanner scanner = new Scanner(
            Main.class.getResourceAsStream("participants.txt")
        );
        while (scanner.hasNextLine()) {
            String name = scanner.nextLine().trim();
            if (name.isEmpty()) continue;

            if (participants.contains(name)) {
                duplicateCount++;
            } else {
                participants.add(name);
            }
        }
        scanner.close();

        System.out.println("===== Problem 2 =====");
        System.out.println("Unique participants: " + participants.size());
        int i = 1;
        for (String name : participants) {
            System.out.println(i + ". " + name);
            i++;
        }
        System.out.println("Duplicate registrations: " + duplicateCount);
    }

    // ===================== PROBLEM 3 — Map =====================
    private static void problem3() {
        Map<String, Integer> inventory = new LinkedHashMap<>();
        int failedSales = 0;

        Scanner scanner = new Scanner(
            Main.class.getResourceAsStream("inventory.txt")
        );
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();
            if (line.isEmpty()) continue;
            String[] parts = line.split("\\s+");
            String type = parts[0];
            String product = parts[1];
            int qty = Integer.parseInt(parts[2]);

            if (type.equals("ADD")) {
                if (inventory.containsKey(product)) {
                    inventory.put(product, inventory.get(product) + qty);
                } else {
                    inventory.put(product, qty);
                }
            } else if (type.equals("SELL")) {
                if (inventory.containsKey(product) && inventory.get(product) >= qty) {
                    inventory.put(product, inventory.get(product) - qty);
                } else {
                    failedSales++;
                }
            }
        }
        scanner.close();

        System.out.println("===== Problem 3 =====");
        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
        System.out.println("Failed sales: " + failedSales);
    }
}