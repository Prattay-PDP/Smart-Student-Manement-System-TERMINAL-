import java.util.Scanner;

public class StudentPanel {
    Student s;
    StudentManager manager;
    Scanner sc = new Scanner(System.in);

    StudentPanel(Student s, StudentManager manager) {
        this.s = s; this.manager = manager;
    }

    void show() {
        NoticeBoard.loadNotice();
        System.out.println("\n=== STUDENT DASHBOARD ===");
        System.out.println("NOTICE: " + NoticeBoard.notice);
        System.out.println("\n--- YOUR INFO ---");
        System.out.println("ID        : " + s.id);
        System.out.println("Name      : " + s.name);
        System.out.println("Age       : " + s.age);
        System.out.println("Department: " + s.dept);
        System.out.println("Semester  : " + s.semester);
        System.out.println("Section   : " + s.section);
        System.out.printf("CGPA      : %.2f%n", s.cgpa);
        System.out.printf("Attendance: %.2f%%%n", s.getAttPer());
        System.out.println("Due Fee   : " + s.dueFee());
        System.out.println("Status    : " + s.status);
        System.out.println("\nPress Enter to go back...");
        sc.nextLine();
    }
}