import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.Scanner;

public class Main {
    static final int MAX_BORROW = 2;

    public static void main(String[] args) throws FileNotFoundException {

        // Menyimpan semua request dari file
        LinkedList<String[]> requests = new LinkedList<>();

        // Menyimpan book dan stock
        LinkedList<String[]> books = new LinkedList<>();
        books.add(new String[]{"Kalkulus", "2"});
        books.add(new String[]{"Fisika", "1"});
        books.add(new String[]{"Statistika", "2"});

        // Menyimpan member dan jumlah buku yang dipinjam
        LinkedList<String[]> members = new LinkedList<>();

        // Membaca Borrowing.txt
        Scanner scanner = new Scanner(new File("Borrowing.txt"));

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            Scanner lineScanner = new Scanner(line);

            String name = lineScanner.next();
            String book = lineScanner.next();

            requests.add(new String[]{name, book});

            // Tambahkan member hanya jika belum ada
            boolean exists = false;

            for (String[] member : members) {
                if (member[0].equals(name)) {
                    exists = true;
                    break;
                }
            }

            if (!exists) {
                members.add(new String[]{name, "0"});
            }

            lineScanner.close();
        }

        scanner.close();

        // Queue untuk memproses request FIFO
        Queue<String[]> queue = new LinkedList<>();

        for (String[] request : requests) {
            queue.offer(request);
        }

        // Menyimpan request yang berhasil
        LinkedList<String[]> successful = new LinkedList<>();

        // Stack untuk request gagal
        Stack<String[]> failed = new Stack<>();

        // Proses Queue
        while (!queue.isEmpty()) {
            String[] request = queue.poll();

            String name = request[0];
            String bookName = request[1];

            String[] bookData = null;
            String[] memberData = null;

            // Cari book
            for (String[] book : books) {
                if (book[0].equals(bookName)) {
                    bookData = book;
                    break;
                }
            }

            // Cari member
            for (String[] member : members) {
                if (member[0].equals(name)) {
                    memberData = member;
                    break;
                }
            }

            int stock = Integer.parseInt(bookData[1]);
            int borrowed = Integer.parseInt(memberData[1]);

            // Cek stock dan limit member
            if (stock > 0 && borrowed < MAX_BORROW) {

                // Berhasil
                bookData[1] = String.valueOf(stock - 1);
                memberData[1] = String.valueOf(borrowed + 1);

                successful.add(request);

            } else {

                // Gagal
                failed.push(request);
            }
        }

        // Output
        System.out.println("=== Successfully Processed Requests ===");

        for (String[] request : successful) {
            System.out.println(request[0] + " " + request[1]);
        }

        System.out.println("=== Remaining Book Stock ===");

        for (String[] book : books) {
            System.out.println(book[0] + " : " + book[1]);
        }

        System.out.println("=== Failed Requests ===");

        while (!failed.isEmpty()) {
            String[] request = failed.pop();
            System.out.println(request[0] + " " + request[1]);
        }
    }
}