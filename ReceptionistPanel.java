import java.util.Scanner;

public class ReceptionistPanel {
    StudentManager manager;
    Scanner sc = new Scanner(System.in);

    ReceptionistPanel(StudentManager manager) { this.manager = manager; }

    void show() {
        while (true) {
            System.out.println("\n=== RECEPTIONIST PANEL ===");
            System.out.println("1. Search Student");
            System.out.println("2. Update Student Info");
            System.out.println("0. Back");
            System.out.print("Choose: ");
            String c = sc.nextLine().trim();

            switch (c) {
                case "1": search(); break;
                case "2": update(); break;
                case "0": return;
                default: System.out.println("Invalid!");
            }
        }
    }

    void search() {
        System.out.print("Student ID: ");
        try {
            int id = Integer.parseInt(sc.nextLine().trim());
            Student s = manager.searchStudent(id);
            if (s == null) { System.out.println("Not Found!"); return; }
            System.out.println("ID: "+s.id+" | Name: "+s.name+" | Dept: "+s.dept+
                " | Mobile: "+s.mobile+" | Email: "+s.email+
                " | Semester: "+s.semester+" | Section: "+s.section);
        } catch (Exception e) { System.out.println("Invalid ID!"); }
    }

    void update() {
        System.out.print("Student ID: ");
        try {
            int id = Integer.parseInt(sc.nextLine().trim());
            Student s = manager.searchStudent(id);
            if (s == null) { System.out.println("Not Found!"); return; }

            System.out.print("New Mobile (Enter to skip): ");
            String mobile = sc.nextLine().trim();
            System.out.print("New Email (Enter to skip): ");
            String email = sc.nextLine().trim();
            System.out.print("New Semester (Enter to skip): ");
            String sem = sc.nextLine().trim();
            System.out.print("New Section (Enter to skip): ");
            String sec = sc.nextLine().trim();

            if (!mobile.isEmpty()) s.mobile = mobile;
            if (!email.isEmpty()) s.email = email;
            if (!sem.isEmpty()) s.semester = sem;
            if (!sec.isEmpty()) s.section = sec;

            manager.saveFile();
            System.out.println("Student Info Updated!");
        } catch (Exception e) { System.out.println("Invalid ID!"); }
    }
}