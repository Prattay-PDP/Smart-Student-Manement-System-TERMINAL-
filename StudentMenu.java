import java.util.*;

public class StudentMenu {
    static void show(Scanner sc, Student s, StudentManager manager) {
        while (true) {
            System.out.println("\n----- STUDENT DASHBOARD -----");
            System.out.println("Notice: " + NoticeBoard.notice);
            System.out.println("\n1. View My Info");
            System.out.println("0. Back to Login");
            System.out.print("Choice: ");

            String c = sc.nextLine().trim();
            switch (c) {
                case "1":
                    System.out.println(
                        "ID        : " + s.id + "\n" +
                        "Name      : " + s.name + "\n" +
                        "Age       : " + s.age + "\n" +
                        "Department: " + s.dept + "\n" +
                        "Semester  : " + s.semester + "\n" +
                        "Section   : " + s.section + "\n" +
                        "CGPA      : " + s.cgpa + "\n" +
                        "Attendance: " + String.format("%.2f", s.getAttPer()) + "%\n" +
                        "Due Fee   : " + s.dueFee() + "\n" +
                        "Status    : " + s.status
                    );
                    break;
                case "0": return;
                default: System.out.println("Invalid Choice!");
            }
        }
    }
}