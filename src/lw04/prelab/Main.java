import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    // Insertion sort descending, berhenti tepat setelah 'passes' pass
    static void insertionSort(int[] a, int passes) {
        for (int i = 1; i <= passes && i < a.length; i++) {
            int key = a[i];
            int j = i - 1;
            while (j >= 0 && a[j] < key) {
                a[j + 1] = a[j];
                j--;
            }
            a[j + 1] = key;
        }
    }

    // Linear search, mengembalikan index atau -1
    static int linearSearch(int[] a, int target) {
        for (int i = 0; i < a.length; i++) {
            if (a[i] == target) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(new File("branches.txt"));

            int n = sc.nextInt();
            int[] branches = new int[n];
            for (int i = 0; i < n; i++) {
                branches[i] = sc.nextInt();
            }

            int m = sc.nextInt();
            int[] targets = new int[m];
            for (int i = 0; i < m; i++) {
                targets[i] = sc.nextInt();
            }
            sc.close();

            insertionSort(branches, 3);

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < n; i++) {
                if (i > 0) sb.append(" ");
                sb.append(branches[i]);
            }
            System.out.println(sb);

            for (int t : targets) {
                int idx = linearSearch(branches, t);
                if (idx != -1) {
                    System.out.println(t + ": FOUND at index " + idx);
                } else {
                    System.out.println(t + ": NOT FOUND");
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File branches.txt tidak ditemukan.");
        }
    }
}