import java.util.*;

public class LibrarianPanel {
    ArrayList<String> books = new ArrayList<>();
    ArrayList<String> issuedBooks = new ArrayList<>();
    Scanner sc = new Scanner(System.in);

    void show() {
        while (true) {
            System.out.println("\n=== LIBRARIAN PANEL ===");
            System.out.println("1. Add Book");
            System.out.println("2. Delete Book");
            System.out.println("3. Search Book");
            System.out.println("4. Issue Book");
            System.out.println("5. View All Books");
            System.out.println("0. Back");
            System.out.print("Choose: ");
            String c = sc.nextLine().trim();

            switch (c) {
                case "1":
                    System.out.print("Book Name: ");
                    books.add(sc.nextLine().trim());
                    System.out.println("Book Added!");
                    break;
                case "2":
                    System.out.print("Book Name to Delete: ");
                    String del = sc.nextLine().trim();
                    if (books.remove(del)) System.out.println("Deleted!");
                    else System.out.println("Not Found!");
                    break;
                case "3":
                    System.out.print("Search: ");
                    String key = sc.nextLine().trim().toLowerCase();
                    for (String b : books) if (b.toLowerCase().contains(key)) System.out.println(b);
                    break;
                case "4":
                    System.out.print("Student ID: ");
                    try {
                        int id = Integer.parseInt(sc.nextLine().trim());
                        Student s = AppController.manager.searchStudent(id);
                        if (s == null) { System.out.println("Student Not Found!"); break; }
                        System.out.print("Book Name: ");
                        String book = sc.nextLine().trim();
                        String info = "ID: "+s.id+" | "+s.name+" | "+s.dept+" | Book: "+book+" | Date: "+java.time.LocalDate.now();
                        issuedBooks.add(info);
                        System.out.println("Book Issued! " + info);
                    } catch (Exception e) { System.out.println("Invalid!"); }
                    break;
                case "5":
                    if (books.isEmpty()) System.out.println("No books.");
                    else books.forEach(System.out::println);
                    break;
                case "0": return;
                default: System.out.println("Invalid!");
            }
        }
    }
}