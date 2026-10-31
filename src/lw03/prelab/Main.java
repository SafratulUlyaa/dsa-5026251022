
package lw03.prelab;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        System.out.println("=== problem 1 ===");
        List<String> playlist = new ArrayList<>();

        Scanner scanner = new Scanner(
            Main.class.getResourceAsStream("playlist.txt")
        );
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] parts = line.split("  ", 3);
            String op = parts[0];
            String song = parts[1];

            if (op.equals("ADD")) {
                playlist.add(song);
            } else if (op.equals("INSERT")) {
                String[] insertData = song.split("  ", 2);

                int index = Integer.parseInt(insertData[0]);
                String songName = insertData[1];
                playlist.add(index, songName);

            } else if (op.equals("REMOVE")) {
                String songName = parts[1];
                playlist.remove(songName);
            }
        }
        scanner.close();

        System.out.println("Total songs: " + playlist.size());

        for (int i = 0; i < playlist.size(); i++) {
            System.out.println((i + 1) + ": " + playlist.get(i));
        }


        System.out.println("=== Problem 2 ===");
        Set<String> participants = new LinkedHashSet<>();
        int duplicateCount = 0;

        scanner = new Scanner(
            Main.class.getResourceAsStream("participants.txt")
        );
        while (scanner.hasNextLine()) {
            String name = scanner.nextLine().trim();

            if (!participants.add(name)) {
                duplicateCount++;
            }
        }
        scanner.close();

        System.out.println("Unique participants: " + participants.size());
        int number = 1;
        for (String name : participants) {
            System.out.println(number + ". " + name);
            number++;
        }
        System.out.println("Duplicate registrations: " + duplicateCount);


        System.out.println("===== Problem 3 =====");
        Map<String, Integer> inventory = new LinkedHashMap<>();
        int failedSales = 0;

        scanner = new Scanner(
            Main.class.getResourceAsStream("inventory.txt")
        );
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] parts = line.split("\\s+");
            String type = parts[0];
            String product = parts[1];
            int qty = Integer.parseInt(parts[2]);

            if (type.equals("ADD")) {
                if (inventory.containsKey(product)) {
                    int currentStock = inventory.get(product);
                    inventory.put(product, Math.min(currentStock + qty, 100));
                } else {
                    inventory.put(product, Math.min(qty, 100));
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

        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
        System.out.println("Failed sales: " + failedSales);
    }
}