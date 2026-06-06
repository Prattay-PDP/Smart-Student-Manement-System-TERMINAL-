public class ReceptionistPanel {
 
    StudentManager manager;
 
    ReceptionistPanel(StudentManager manager) {
        this.manager = manager;
    }
 
    void show() {
        while (true) {
            Input.header("👩‍💼 RECEPTIONIST PANEL");
            System.out.println("  1. Search Student");
            System.out.println("  2. Update Student Info");
            System.out.println("  0. Logout");
            Input.line();
 
            String choice = Input.str("Choose");
 
            switch (choice) {
                case "1": searchStudent(); break;
                case "2": updateStudent(); break;
                case "0":
                    System.out.println("\n  Logged out.\n");
                    return;
                default:
                    System.out.println("  ❌ Invalid choice.");
            }
        }
    }
 
    void searchStudent() {
        Input.header("🔍 SEARCH STUDENT");
        int id = Input.num("Enter Student ID");
        Student s = manager.searchStudent(id);
        if (s == null) {
            System.out.println("  ❌ Student Not Found.");
        } else {
            printStudent(s);
        }
        Input.pause();
    }
 
    void updateStudent() {
        Input.header("✏️  UPDATE STUDENT INFO");
        int id = Input.num("Enter Student ID");
        Student s = manager.searchStudent(id);
        if (s == null) {
            System.out.println("  ❌ Student Not Found.");
            Input.pause();
            return;
        }
 
        System.out.println("  (Press ENTER to keep current value)");
        System.out.println();
 
        System.out.print("  Mobile [" + s.mobile + "]: ");
        String mobile = new java.util.Scanner(System.in).nextLine().trim();
        if (!mobile.isEmpty()) s.mobile = mobile;
 
        System.out.print("  Email [" + s.email + "]: ");
        String email = new java.util.Scanner(System.in).nextLine().trim();
        if (!email.isEmpty()) s.email = email;
 
        System.out.print("  Semester [" + s.semester + "]: ");
        String semester = new java.util.Scanner(System.in).nextLine().trim();
        if (!semester.isEmpty()) s.semester = semester;
 
        System.out.print("  Section [" + s.section + "]: ");
        String section = new java.util.Scanner(System.in).nextLine().trim();
        if (!section.isEmpty()) s.section = section;
 
        manager.saveFile();
        System.out.println("\n  ✅ Student Info Updated Successfully!");
        Input.pause();
    }
 
    void printStudent(Student s) {
        System.out.println();
        System.out.println("  ID         : " + s.id);
        System.out.println("  Name       : " + s.name);
        System.out.println("  Age        : " + s.age);
        System.out.println("  Department : " + s.dept);
        System.out.println("  Semester   : " + s.semester);
        System.out.println("  Section    : " + s.section);
        System.out.println("  Mobile     : " + s.mobile);
        System.out.println("  Email      : " + s.email);
        System.out.printf("  CGPA       : %.2f%n", s.cgpa);
        System.out.printf("  Attendance : %.2f%%%n", s.getAttPer());
        System.out.println("  Total Fee  : " + s.totalFee);
        System.out.println("  Paid Fee   : " + s.paidFee);
        System.out.println("  Due Fee    : " + s.dueFee());
        System.out.println("  Status     : " + s.status);
    }
}