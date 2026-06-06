import java.io.*;
import java.util.*;
 
public class LibrarianPanel {
 
    StudentManager manager;
    ArrayList<String> books       = new ArrayList<>();
    ArrayList<String> issuedBooks = new ArrayList<>();
 
    String BOOK_FILE   = "books.txt";
    String ISSUED_FILE = "issued.txt";
 
    LibrarianPanel(StudentManager manager) {
        this.manager = manager;
        loadBooks();
        loadIssued();
    }
 
    void show() {
        while (true) {
            Input.header("📚 LIBRARIAN PANEL");
            System.out.println("  1. View All Books");
            System.out.println("  2. Add Book");
            System.out.println("  3. Delete Book");
            System.out.println("  4. Search Book");
            System.out.println("  5. Issue Book to Student");
            System.out.println("  6. View Issued Books");
            System.out.println("  0. Logout");
            Input.line();
 
            String choice = Input.str("Choose");
 
            switch (choice) {
                case "1": viewBooks();   break;
                case "2": addBook();     break;
                case "3": deleteBook();  break;
                case "4": searchBook();  break;
                case "5": issueBook();   break;
                case "6": viewIssued();  break;
                case "0":
                    System.out.println("\n  Logged out.\n");
                    return;
                default:
                    System.out.println("  ❌ Invalid choice.");
            }
        }
    }
 
    void viewBooks() {
        Input.header("📖 ALL BOOKS");
        if (books.isEmpty()) {
            System.out.println("  No books in library.");
        } else {
            for (int i = 0; i < books.size(); i++) {
                System.out.println("  " + (i + 1) + ". " + books.get(i));
            }
        }
        Input.pause();
    }
 
    void addBook() {
        Input.header("➕ ADD BOOK");
        String book = Input.str("Book Name");
        books.add(book);
        saveBooks();
        System.out.println("  ✅ Book Added: " + book);
        Input.pause();
    }
 
    void deleteBook() {
        Input.header("🗑 DELETE BOOK");
        viewBooks();
        int num = Input.num("Enter Book Number to Delete");
        if (num < 1 || num > books.size()) {
            System.out.println("  ❌ Invalid number.");
        } else {
            String removed = books.remove(num - 1);
            saveBooks();
            System.out.println("  ✅ Deleted: " + removed);
        }
        Input.pause();
    }
 
    void searchBook() {
        Input.header("🔍 SEARCH BOOK");
        String key = Input.str("Enter keyword").toLowerCase();
        boolean found = false;
        for (String b : books) {
            if (b.toLowerCase().contains(key)) {
                System.out.println("  ✅ " + b);
                found = true;
            }
        }
        if (!found) System.out.println("  ❌ No book found.");
        Input.pause();
    }
 
    void issueBook() {
        Input.header("📤 ISSUE BOOK");
        int id = Input.num("Enter Student ID");
        Student s = manager.searchStudent(id);
        if (s == null) {
            System.out.println("  ❌ Student Not Found.");
            Input.pause();
            return;
        }
        System.out.println("  Student: " + s.name + " | Dept: " + s.dept);
        String book = Input.str("Book Name");
        String date = java.time.LocalDate.now().toString();
        String info = "ID:" + s.id + " | " + s.name + " | " + s.mobile +
                      " | " + s.dept + " | Book: " + book + " | Date: " + date;
        issuedBooks.add(info);
        saveIssued();
        System.out.println("  ✅ Book Issued Successfully!");
        Input.pause();
    }
 
    void viewIssued() {
        Input.header("📋 ISSUED BOOKS");
        if (issuedBooks.isEmpty()) {
            System.out.println("  No books issued.");
        } else {
            for (String i : issuedBooks) {
                System.out.println("  - " + i);
            }
        }
        Input.pause();
    }
 
    void saveBooks() {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(BOOK_FILE));
            for (String b : books) { bw.write(b); bw.newLine(); }
            bw.close();
        } catch (Exception e) { System.out.println("Book Save Error"); }
    }
 
    void loadBooks() {
        try {
            File f = new File(BOOK_FILE);
            if (!f.exists()) return;
            BufferedReader br = new BufferedReader(new FileReader(f));
            String line;
            while ((line = br.readLine()) != null)
                if (!line.trim().isEmpty()) books.add(line.trim());
            br.close();
        } catch (Exception e) { System.out.println("Book Load Error"); }
    }
 
    void saveIssued() {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(ISSUED_FILE));
            for (String i : issuedBooks) { bw.write(i); bw.newLine(); }
            bw.close();
        } catch (Exception e) { System.out.println("Issued Save Error"); }
    }
 
    void loadIssued() {
        try {
            File f = new File(ISSUED_FILE);
            if (!f.exists()) return;
            BufferedReader br = new BufferedReader(new FileReader(f));
            String line;
            while ((line = br.readLine()) != null)
                if (!line.trim().isEmpty()) issuedBooks.add(line.trim());
            br.close();
        } catch (Exception e) { System.out.println("Issued Load Error"); }
    }
}