package lw03.unguided;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        System.out.println("===== Event Check-In Results =====");

        Set<String> registeredStudents = new LinkedHashSet<>();
        Set<String> checkedInStudents = new LinkedHashSet<>();

        int rejectedAttempts = 0;

        Scanner scanner = new Scanner(
            Main.class.getResourceAsStream("registrations.txt")
        );
        while (scanner.hasNextLine()) {
            String studentID = scanner.nextLine().trim();

            if (!studentID.isEmpty()) {
                registeredStudents.add(studentID);
            }
        }
        scanner.close();

        scanner = new Scanner(
            Main.class.getResourceAsStream("checkins.txt")
        );
        while (scanner.hasNextLine()) {
            String studentID = scanner.nextLine().trim();

            if (!registeredStudents.contains(studentID)) {
                System.out.println(
                    studentID + ": Rejected (not registered)"
                );
                rejectedAttempts++;

            } else if (checkedInStudents.contains(studentID)) {
                System.out.println(
                    studentID + ": Rejected (already checked in)"
                );
                rejectedAttempts++;

            } else {
                checkedInStudents.add(studentID);

                System.out.println(
                    studentID + ": Checked in"
                );
            }
        }
        scanner.close();
        System.out.println();
        System.out.println("===== Final Event Summary =====");
        System.out.println(
            "Registered students: " + registeredStudents.size()
        );
        System.out.println(
            "Successful check-ins: " + checkedInStudents.size()
        );
        System.out.println(
            "Absent students: " +
            (registeredStudents.size() - checkedInStudents.size())
        );
        System.out.println(
            "Rejected attempts: " + rejectedAttempts
        );
    }
}	