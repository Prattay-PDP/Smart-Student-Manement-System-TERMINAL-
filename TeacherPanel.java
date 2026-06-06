public class TeacherPanel {
 
    StudentManager manager;
 
    TeacherPanel(StudentManager manager) {
        this.manager = manager;
    }
 
    void show() {
        while (true) {
            Input.header("📚 TEACHER PANEL");
            System.out.println("  1. View Student Info");
            System.out.println("  2. Update CGPA / Marks");
            System.out.println("  3. Update Attendance");
            System.out.println("  4. View Notice");
            System.out.println("  0. Logout");
            Input.line();
 
            String choice = Input.str("Choose");
 
            switch (choice) {
                case "1": viewStudent();      break;
                case "2": updateMarks();      break;
                case "3": updateAttendance(); break;
                case "4": viewNotice();       break;
                case "0":
                    System.out.println("\n  Logged out.\n");
                    return;
                default:
                    System.out.println("  ❌ Invalid choice.");
            }
        }
    }
 
    void viewStudent() {
        Input.header("👨‍🎓 VIEW STUDENT");
        int id = Input.num("Enter Student ID");
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
            System.out.println("  Status     : " + s.status);
        }
        Input.pause();
    }
 
    void updateMarks() {
        Input.header("📝 UPDATE CGPA / MARKS");
        int id = Input.num("Enter Student ID");
        Student s = manager.searchStudent(id);
        if (s == null) {
            System.out.println("  ❌ Student Not Found.");
        } else {
            System.out.println("  Current CGPA: " + s.cgpa);
            double newCgpa = Input.decimal("New CGPA (0.0 - 4.0)");
            s.cgpa = newCgpa;
            s.updateStatus();
            manager.saveFile();
            System.out.println("  ✅ CGPA Updated for " + s.name);
        }
        Input.pause();
    }
 
    void updateAttendance() {
        Input.header("📅 UPDATE ATTENDANCE");
        int id = Input.num("Enter Student ID");
        Student s = manager.searchStudent(id);
        if (s == null) {
            System.out.println("  ❌ Student Not Found.");
        } else {
            System.out.printf("  Current Attendance: %.2f%%%n", s.getAttPer());
            System.out.println("  Total Classes: " + s.totalClass + " | Attended: " + s.attended);
            int totalClass = Input.num("New Total Classes");
            int attended   = Input.num("New Attended Classes");
            s.totalClass = totalClass;
            s.attended   = attended;
            s.updateStatus();
            manager.saveFile();
            System.out.printf("  ✅ Attendance Updated: %.2f%%%n", s.getAttPer());
        }
        Input.pause();
    }
 
    void viewNotice() {
        Input.header("📢 NOTICE BOARD");
        System.out.println("  " + NoticeBoard.notice);
        Input.pause();
    }
}
 