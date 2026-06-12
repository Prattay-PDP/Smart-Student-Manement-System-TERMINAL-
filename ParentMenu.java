public class ParentMenu {
    static void show(Student s) {
        System.out.println("\n----- PARENT DASHBOARD -----");
        System.out.println("Notice: " + NoticeBoard.notice);
        System.out.println(
            "\nID         : " + s.id + "\n" +
            "Name       : " + s.name + "\n" +
            "Department : " + s.dept + "\n" +
            "Semester   : " + s.semester + "\n" +
            "CGPA       : " + s.cgpa + "\n" +
            "Attendance : " + String.format("%.2f", s.getAttPer()) + "%\n" +
            "Total Fee  : " + s.totalFee + "\n" +
            "Paid Fee   : " + s.paidFee + "\n" +
            "Due Fee    : " + s.dueFee() + "\n" +
            "Status     : " + s.status
        );
        System.out.println("\nPress Enter to go Back...");
        new java.util.Scanner(System.in).nextLine();
    }
}