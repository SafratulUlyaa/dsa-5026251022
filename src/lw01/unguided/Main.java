package lw01.unguided;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(
            Main.class.getResourceAsStream("washes.txt")
        );

        int count = 0;
        if (scanner.hasNextInt()) {
            count = scanner.nextInt(); 
        }

        
        WashService[] services = new WashService[count];
        int[] unitsArray = new int[count];

        for (int i = 0; i < count; i++) {
            String type = scanner.next();
            String id = scanner.next();
            int days = scanner.nextInt();
            int units = scanner.nextInt();

            WashService washes;

            if (type.equals("MOTORCYCLE")) {
                washes = new MotorcycleWash(id, days);
            } else {
                washes = new CarWash(id, days);
            }

            services[i] = washes;
            unitsArray[i] = units;
        }

        scanner.close();

        
        for (int i = 0; i < services.length; i++) {
            WashService washes = services[i];
            int units = unitsArray[i];
            
            System.out.println(washes.getId() + " | " + washes.label() + " | " + washes.calculateCharge(units));
        }
    }
}