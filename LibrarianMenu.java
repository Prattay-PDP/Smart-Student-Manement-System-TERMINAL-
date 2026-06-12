import java.util.*;
import java.io.*;

public class LibrarianMenu {
    static ArrayList<String> books = new ArrayList<>();
    static ArrayList<String> issuedBooks = new ArrayList<>();
    static final String FILE = "books.txt";

    static void show(Scanner sc, StudentManager manager) {
        loadBooks();
        while (true) {
            System.out.println("\n----- LIBRARIAN PANEL -----");
            System.out.println("1. Add Book");
            System.out.println("2. Delete Book");
            System.out.println("3. Search Book");
            System.out.println("4. View All Books");
            System.out.println("5. Issue Book");
            System.out.println("6. View Issued Books");
            System.out.println("0. Back to Login");
            System.out.print("Choice: ");

            String c = sc.nextLine().trim();
            switch (c) {
                case "1":
                    System.out.print("Book Name: ");
                    String b = sc.nextLine();
                    books.add(b);
                    saveBooks();
                    System.out.println("✅ Book Added");
                    break;
                case "2":
                    System.out.print("Enter Book Name to Delete: ");
                    String del = sc.nextLine();
                    if (books.remove(del)) { saveBooks(); System.out.println("✅ Deleted"); }
                    else System.out.println("Not Found");
                    break;
                case "3":
                    System.out.print("Search Keyword: ");
                    String key = sc.nextLine().toLowerCase();
                    for (String book : books) if (book.toLowerCase().contains(key)) System.out.println("- " + book);
                    break;
                case "4":
                    if (books.isEmpty()) System.out.println("No books found.");
                    for (String book : books) System.out.println("- " + book);
                    break;
                case "5":
                    issueBook(sc, manager);
                    break;
                case "6":
                    if (issuedBooks.isEmpty()) System.out.println("No issued books.");
                    for (String ib : issuedBooks) System.out.println(ib);
                    break;
                case "0": return;
                default: System.out.println("Invalid Choice!");
            }
        }
    }

    static void issueBook(Scanner sc, StudentManager manager) {
        try {
            System.out.print("Enter Student ID: ");
            int id = Integer.parseInt(sc.nextLine().trim());
            Student s = manager.searchStudent(id);
            if (s == null) { System.out.println("Student Not Found"); return; }

            System.out.print("Book Name: ");
            String book = sc.nextLine();
            String date = java.time.LocalDate.now().toString();
            String info = "ID: " + s.id + " | Name: " + s.name + " | Dept: " + s.dept + " | Book: " + book + " | Date: " + date;
            issuedBooks.add(info);
            System.out.println("✅ Book Issued Successfully");
        } catch (Exception ex) {
            System.out.println("❌ Invalid Input");
        }
    }

    static void saveBooks() {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(FILE));
            for (String b : books) { bw.write(b); bw.newLine(); }
            bw.close();
        } catch (Exception e) { System.out.println("Save Error"); }
    }

    static void loadBooks() {
        if (!books.isEmpty()) return;
        try {
            File f = new File(FILE);
            if (!f.exists()) return;
            BufferedReader br = new BufferedReader(new FileReader(f));
            String line;
            while ((line = br.readLine()) != null) books.add(line);
            br.close();
        } catch (Exception e) { System.out.println("Load Error"); }
    }
}