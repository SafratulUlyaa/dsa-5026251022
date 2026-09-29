package lw02.unguided;
import java.util.*;

public class Main {
    public static void main(String[] args) {

        LinkedList<String[]> orders = new LinkedList<>();
        LinkedList<String[]> foods = new LinkedList<>();
        LinkedList<String[]> drinks = new LinkedList<>();
        LinkedList<String[]> successful = new LinkedList<>();

        Queue<String[]> queue = new LinkedList<>();
        Stack<String[]> failed = new Stack<>();

        Scanner scanner = new Scanner(
            Main.class.getResourceAsStream("orders.txt")
        );

        while (scanner.hasNext()) {
            String[] order = new String[4];

            order[0] = scanner.next();
            order[1] = scanner.next();
            order[2] = scanner.next();
            order[3] = scanner.next();

            orders.add(order);
        }

        scanner.close();

        foods.add(new String[]{"Bakso", "2"});
        foods.add(new String[]{"Sate", "1"});
        foods.add(new String[]{"Soto", "2"});

        drinks.add(new String[]{"EsTeh", "4"});
        drinks.add(new String[]{"EsJeruk", "2"});

        queue.addAll(orders);

        while (!queue.isEmpty()) {

            String[] order = queue.poll();

            String name = order[0];
            String food = order[1];
            String drink = order[2];
            String table = order[3];

            String[] foodData = null;
            String[] drinkData = null;

            if (!food.equals("-")) {
                for (String[] data : foods) {
                    if (data[0].equals(food)) {
                        foodData = data;
                        break;
                    }
                }
            }

            if (!drink.equals("-")) {
                for (String[] data : drinks) {
                    if (data[0].equals(drink)) {
                        drinkData = data;
                        break;
                    }
                }
            }

            boolean available = true;

            if (!food.equals("-")) {
                int stock = Integer.parseInt(foodData[1]);

                if (stock <= 0) {
                    available = false;
                }
            }

            if (!drink.equals("-")) {
                int stock = Integer.parseInt(drinkData[1]);

                if (stock <= 0) {
                    available = false;
                }
            }

            if (available) {

                if (!food.equals("-")) {
                    int stock = Integer.parseInt(foodData[1]);
                    stock--;
                    foodData[1] = String.valueOf(stock);
                }

                if (!drink.equals("-")) {
                    int stock = Integer.parseInt(drinkData[1]);
                    stock--;
                    drinkData[1] = String.valueOf(stock);
                }

                successful.add(order);

            } else {
                failed.push(order);
            }
        }

        System.out.println("=== Successfully Processed Orders ===");

        for (String[] order : successful) {
            System.out.println(
                order[0] + " " +
                order[1] + " " +
                order[2] + " " +
                order[3]
            );
        }

        System.out.println("\n=== Remaining Food Stock ===");

        for (String[] food : foods) {
            System.out.println(food[0] + " : " + food[1]);
        }

        System.out.println("\n=== Remaining Drink Stock ===");

        for (String[] drink : drinks) {
            System.out.println(drink[0] + " : " + drink[1]);
        }

        System.out.println("\n=== Failed Orders ===");

        while (!failed.isEmpty()) {
            String[] order = failed.pop();

            System.out.println(
                order[0] + " " +
                order[1] + " " +
                order[2] + " " +
                order[3]
            );
        }
    }
}

