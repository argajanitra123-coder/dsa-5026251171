import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {

        Map<String, Integer> enrollment = new LinkedHashMap<>();
        int rejectedOperations = 0;

        Scanner scanner = new Scanner(new File("enrollment.txt"));

        // Menyimpan hasil CHECK
        String[] checkCourses = new String[100];
        String[] checkResults = new String[100];
        int checkCount = 0;

        while (scanner.hasNextLine()) {

            String line = scanner.nextLine();
            String[] parts = line.split(" ");

            String operation = parts[0];
            String course = parts[1];

            if (operation.equals("REGISTER")) {

                int count = Integer.parseInt(parts[2]);

                if (count <= 0) {
                    rejectedOperations++;
                } else if (enrollment.containsKey(course)) {
                    enrollment.put(
                        course,
                        enrollment.get(course) + count
                    );
                } else {
                    enrollment.put(course, count);
                }

            } else if (operation.equals("WITHDRAW")) {

                int count = Integer.parseInt(parts[2]);

                if (count <= 0) {
                    rejectedOperations++;
                } else if (!enrollment.containsKey(course)) {
                    rejectedOperations++;
                } else if (enrollment.get(course) < count) {
                    rejectedOperations++;
                } else {
                    enrollment.put(
                        course,
                        enrollment.get(course) - count
                    );
                }

            } else if (operation.equals("CHECK")) {

                if (enrollment.containsKey(course)) {
                    checkCourses[checkCount] = course;
                    checkResults[checkCount] =
                        enrollment.get(course) + " students";
                } else {
                    checkCourses[checkCount] = course;
                    checkResults[checkCount] = "Not found";
                }

                checkCount++;
            }
        }

        scanner.close();

        // ==================== CHECK RESULTS ====================

        System.out.println("===== Enrollment Checks =====");

        for (int i = 0; i < checkCount; i++) {
            System.out.println(
                checkCourses[i] + ": " + checkResults[i]
            );
        }

        // ==================== FINAL ENROLLMENT ====================

        System.out.println("===== Final Enrollment =====");

        for (Map.Entry<String, Integer> entry : enrollment.entrySet()) {
            System.out.println(
                entry.getKey() + ": "
                + entry.getValue()
                + " students"
            );
        }

        System.out.println(
            "Rejected operations: " + rejectedOperations
        );
    }
}