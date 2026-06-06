public class StudentPanel {
 
    Student s;
 
    StudentPanel(Student s) {
        this.s = s;
    }
 
    void show() {
        Input.header("🎓 STUDENT DASHBOARD");
        System.out.println("  📢 NOTICE: " + NoticeBoard.notice);
        Input.line();
        System.out.println("  ID         : " + s.id);
        System.out.println("  Name       : " + s.name);
        System.out.println("  Age        : " + s.age);
        System.out.println("  Department : " + s.dept);
        System.out.println("  Semester   : " + s.semester);
        System.out.println("  Section    : " + s.section);
        System.out.printf("  CGPA       : %.2f%n", s.cgpa);
        System.out.printf("  Attendance : %.2f%%%n", s.getAttPer());
        System.out.println("  Total Fee  : " + s.totalFee);
        System.out.println("  Paid Fee   : " + s.paidFee);
        System.out.println("  Due Fee    : " + s.dueFee());
        System.out.println("  Status     : " + s.status);
        Input.line();
        System.out.println("  (Press ENTER to logout)");
        Input.pause();
    }
}