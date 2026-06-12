import java.util.*;

public class ReceptionistMenu {
    static void show(Scanner sc, StudentManager manager) {
        while (true) {
            System.out.println("\n----- RECEPTIONIST PANEL -----");
            System.out.println("1. Search Student");
            System.out.println("2. Update Student Info");
            System.out.println("0. Back to Login");
            System.out.print("Choice: ");

            String c = sc.nextLine().trim();
            switch (c) {
                case "1": search(sc, manager); break;
                case "2": update(sc, manager); break;
                case "0": return;
                default: System.out.println("Invalid Choice!");
            }
        }
    }

    static void search(Scanner sc, StudentManager manager) {
        System.out.print("Enter Student ID: ");
        try {
            int id = Integer.parseInt(sc.nextLine().trim());
            Student s = manager.searchStudent(id);
            if (s == null) { System.out.println("Student Not Found"); return; }

            System.out.println(
                "ID         : " + s.id + "\n" +
                "Name       : " + s.name + "\n" +
                "Age        : " + s.age + "\n" +
                "Department : " + s.dept + "\n" +
                "Semester   : " + s.semester + "\n" +
                "Section    : " + s.section + "\n" +
                "Mobile     : " + s.mobile + "\n" +
                "Email      : " + s.email + "\n" +
                "CGPA       : " + s.cgpa + "\n" +
                "Attendance : " + String.format("%.2f", s.getAttPer()) + "%\n" +
                "Total Fee  : " + s.totalFee + "\n" +
                "Paid Fee   : " + s.paidFee + "\n" +
                "Due Fee    : " + s.dueFee() + "\n" +
                "Status     : " + s.status
            );
        } catch (Exception ex) {
            System.out.println("Invalid ID!");
        }
    }

    static void update(Scanner sc, StudentManager manager) {
        System.out.print("Enter Student ID: ");
        try {
            int id = Integer.parseInt(sc.nextLine().trim());
            Student s = manager.searchStudent(id);
            if (s == null) { System.out.println("Student Not Found"); return; }

            System.out.print("New Mobile (" + s.mobile + "), Enter to skip: ");
            String m = sc.nextLine();
            System.out.print("New Email (" + s.email + "), Enter to skip: ");
            String e = sc.nextLine();
            System.out.print("New Semester (" + s.semester + "), Enter to skip: ");
            String sem = sc.nextLine();
            System.out.print("New Section (" + s.section + "), Enter to skip: ");
            String sec = sc.nextLine();

            if (!m.isEmpty()) s.mobile = m;
            if (!e.isEmpty()) s.email = e;
            if (!sem.isEmpty()) s.semester = sem;
            if (!sec.isEmpty()) s.section = sec;

            manager.saveFile();
            System.out.println("✅ Student Info Updated");
        } catch (Exception ex) {
            System.out.println("❌ Update Failed");
        }
    }
}