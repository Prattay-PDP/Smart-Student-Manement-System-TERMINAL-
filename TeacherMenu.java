import java.util.*;

public class TeacherMenu {
    static void show(Scanner sc, Teacher t) {
        while (true) {
            System.out.println("\n----- TEACHER DASHBOARD -----");
            System.out.println("1. Upload/Edit Marks");
            System.out.println("2. Manage Attendance");
            System.out.println("3. View Students (coming soon)");
            System.out.println("4. View Notice");
            System.out.println("0. Back to Login");
            System.out.print("Choice: ");

            String c = sc.nextLine().trim();
            switch (c) {
                case "1":
                    System.out.print("Student ID: ");
                    String id1 = sc.nextLine();
                    System.out.print("New Marks: ");
                    String marks = sc.nextLine();
                    System.out.println("✅ Marks updated for ID: " + id1 + " -> " + marks);
                    break;
                case "2":
                    System.out.print("Student ID: ");
                    String id2 = sc.nextLine();
                    System.out.print("Attendance %: ");
                    String att = sc.nextLine();
                    System.out.println("✅ Attendance updated for ID: " + id2 + " -> " + att + "%");
                    break;
                case "3":
                    System.out.println("Feature coming soon!");
                    break;
                case "4":
                    System.out.println("Notice: " + NoticeBoard.notice);
                    break;
                case "0": return;
                default: System.out.println("Invalid Choice!");
            }
        }
    }
}