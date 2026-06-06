public class ParentPanel {
 
    StudentManager manager;
 
    ParentPanel(StudentManager manager) {
        this.manager = manager;
    }
 
    void show() {
        while (true) {
            Input.header("👨‍👩‍👦 PARENT PANEL");
            System.out.println("  1. View Child's Info");
            System.out.println("  2. View Notice");
            System.out.println("  0. Logout");
            Input.line();
 
            String choice = Input.str("Choose");
 
            switch (choice) {
                case "1": viewChild();  break;
                case "2": viewNotice(); break;
                case "0":
                    System.out.println("\n  Logged out.\n");
                    return;
                default:
                    System.out.println("  ❌ Invalid choice.");
            }
        }
    }
 
    void viewChild() {
        Input.header("👨‍🎓 CHILD INFO");
        int id = Input.num("Enter Child's Student ID");
        Student s = manager.searchStudent(id);
        if (s == null) {
            System.out.println("  ❌ Student Not Found.");
        } else {
            System.out.println();
            System.out.println("  ID         : " + s.id);
            System.out.println("  Name       : " + s.name);
            System.out.println("  Department : " + s.dept);
            System.out.println("  Semester   : " + s.semester);
            System.out.printf("  CGPA       : %.2f%n", s.cgpa);
            System.out.printf("  Attendance : %.2f%%%n", s.getAttPer());
            System.out.println("  Total Fee  : " + s.totalFee);
            System.out.println("  Paid Fee   : " + s.paidFee);
            System.out.println("  Due Fee    : " + s.dueFee());
            System.out.println("  Status     : " + s.status);
        }
        Input.pause();
    }
 
    void viewNotice() {
        Input.header("📢 NOTICE BOARD");
        System.out.println("  " + NoticeBoard.notice);
        Input.pause();
    }
}