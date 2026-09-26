package lw02.prelab;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class Main {
     public static void main(String[] args) {
        LinkedList<String[]> transactionList = new LinkedList<>();
        LinkedList<String[]> customerList = new LinkedList<>();

        // 1. Baca transaksi dari file, simpan urutan aslinya
        Scanner scanner = new Scanner(
            Main.class.getResourceAsStream("transactions.txt")
        );
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();
            if (line.isEmpty()) continue;
            String[] parts = line.split("\\s+"); // {NAME, TYPE, AMOUNT}
            transactionList.add(parts);
        }
        scanner.close();

        // 2. Bangun data customer (urutan kemunculan pertama, tanpa duplikat, saldo awal 0)
        for (String[] t : transactionList) {
            String name = t[0];
            if (!isCustomerExist(customerList, name)) {
                customerList.add(new String[]{name, "0"});
            }
        }

        // 3. Pindahkan transaksi ke Queue, proses secara FIFO
        Queue<String[]> transactionQueue = new LinkedList<>(transactionList);
        Stack<String[]> failedStack = new Stack<>();

        while (!transactionQueue.isEmpty()) {
            String[] trx = transactionQueue.poll();
            String name = trx[0];
            String type = trx[1];
            int amount = Integer.parseInt(trx[2]);

            String[] customer = findCustomer(customerList, name);
            int balance = Integer.parseInt(customer[1]);

            if (type.equalsIgnoreCase("DEPOSIT")) {
                balance += amount;
                customer[1] = String.valueOf(balance);
            } else if (type.equalsIgnoreCase("WITHDRAW")) {
                if (amount > balance) {
                    failedStack.push(trx); // saldo tidak cukup -> gagal, simpan ke Stack
                } else {
                    balance -= amount;
                    customer[1] = String.valueOf(balance);
                }
            }
        }

        // 4. Tampilkan hasil akhir
        System.out.println("=== Final Balances ===");
        for (String[] c : customerList) {
            System.out.println(c[0] + " : " + c[1]);
        }

        System.out.println();
        System.out.println("=== Failed Transactions ===");
        while (!failedStack.isEmpty()) {
            String[] f = failedStack.pop(); // LIFO
            System.out.println(f[0] + " " + f[1] + " " + f[2]);
        }
    }

    private static boolean isCustomerExist(LinkedList<String[]> customerList, String name) {
        for (String[] c : customerList) {
            if (c[0].equals(name)) return true;
        }
        return false;
    }

    private static String[] findCustomer(LinkedList<String[]> customerList, String name) {
        for (String[] c : customerList) {
            if (c[0].equals(name)) return c;
        }
        return null;
    }
}

