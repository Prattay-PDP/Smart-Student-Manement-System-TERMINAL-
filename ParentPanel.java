import java.util.Scanner;

public class ParentPanel {
    Student s;
    Scanner sc = new Scanner(System.in);

    ParentPanel(Student s) { this.s = s; }

    void show() {
        NoticeBoard.loadNotice();
        System.out.println("\n=== PARENT DASHBOARD ===");
        System.out.println("NOTICE: " + NoticeBoard.notice);
        System.out.println("\n--- STUDENT INFO ---");
        System.out.println("ID         : " + s.id);
        System.out.println("Name       : " + s.name);
        System.out.println("Department : " + s.dept);
        System.out.println("Semester   : " + s.semester);
        System.out.printf("CGPA       : %.2f%n", s.cgpa);
        System.out.printf("Attendance : %.2f%%%n", s.getAttPer());
        System.out.println("Total Fee  : " + s.totalFee);
        System.out.println("Paid Fee   : " + s.paidFee);
        System.out.println("Due Fee    : " + s.dueFee());
        System.out.println("Status     : " + s.status);
        System.out.println("\nPress Enter to go back...");
        sc.nextLine();
    }
}