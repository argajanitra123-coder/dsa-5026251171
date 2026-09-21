import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("Rentals.txt"));

        int total = scanner.nextInt();
        Rental[] rentals = new Rental[total];
        int[] units = new int[total];

        for (int i = 0; i < total; i++) {
            String type = scanner.next();
            String id = scanner.next();
            int days = scanner.nextInt();
            units[i] = scanner.nextInt();

            if (type.equals("LAPTOP")) {
                rentals[i] = new LaptopRental(id, days);
            } else if (type.equals("PROJECTOR")) {
                rentals[i] = new ProjectorRental(id, days);
            }
        }

        for (int i = 0; i < total; i++) {
            System.out.println(
                rentals[i].getId() + " | "
                + rentals[i].label() + " | "
                + rentals[i].calculateCharge(units[i])
            );
        }

        scanner.close();
    }
}