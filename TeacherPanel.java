import java.util.Scanner;

public class TeacherPanel {
    Scanner sc = new Scanner(System.in);

    void show() {
        while (true) {
            System.out.println("\n=== TEACHER DASHBOARD ===");
            System.out.println("1. Upload/Edit Marks");
            System.out.println("2. Manage Attendance");
            System.out.println("3. View Notice");
            System.out.println("0. Back");
            System.out.print("Choose: ");
            String c = sc.nextLine().trim();

            switch (c) {
                case "1":
                    System.out.print("Student ID: "); String id = sc.nextLine();
                    System.out.print("Marks: "); String marks = sc.nextLine();
                    System.out.println("Marks updated for ID: " + id + " | Marks: " + marks);
                    break;
                case "2":
                    System.out.print("Student ID: "); String sid = sc.nextLine();
                    System.out.print("Attendance %: "); String att = sc.nextLine();
                    System.out.println("Attendance updated: " + att + "%");
                    break;
                case "3":
                    System.out.println("Notice: " + NoticeBoard.notice);
                    break;
                case "0": return;
                default: System.out.println("Invalid!");
            }
        }
    }
}