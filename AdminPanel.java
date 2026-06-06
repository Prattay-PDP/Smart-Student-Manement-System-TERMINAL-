public class AdminPanel {
 
    StudentManager manager;
 
    AdminPanel(StudentManager manager) {
        this.manager = manager;
    }
 
    void show() {
        while (true) {
            Input.header("👨‍💼 ADMIN PANEL");
            System.out.println("  1. Search Student");
            System.out.println("  2. Add Student");
            System.out.println("  3. Delete Student");
            System.out.println("  4. Post Notice");
            System.out.println("  5. View All Stats");
            System.out.println("  0. Logout");
            Input.line();
 
            String choice = Input.str("Choose");
 
            switch (choice) {
                case "1": searchStudent(); break;
                case "2": addStudent();    break;
                case "3": deleteStudent(); break;
                case "4": postNotice();    break;
                case "5": viewStats();     break;
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
 
    void addStudent() {
        Input.header("➕ ADD STUDENT");
        try {
            int    id         = Input.num("ID");
            String name       = Input.str("Name");
            int    age        = Input.num("Age");
            String mobile     = Input.str("Mobile");
            String email      = Input.str("Email");
            String pass       = Input.str("Password");
            String dept       = Input.str("Department");
            String semester   = Input.str("Semester");
            String section    = Input.str("Section");
            double cgpa       = Input.decimal("CGPA");
            int    totalClass = Input.num("Total Classes");
            int    attended   = Input.num("Attended Classes");
            double totalFee   = Input.decimal("Total Fee");
 
            Student s = new Student(
                id, name, age, mobile, email, pass,
                dept, semester, section,
                cgpa, totalClass, attended,
                totalFee, 0
            );
 
            manager.addStudent(s);
            System.out.println("\n  ✅ Student Added & Saved Successfully!");
 
        } catch (Exception e) {
            System.out.println("  ❌ Error: " + e.getMessage());
        }
        Input.pause();
    }
 
    void deleteStudent() {
        Input.header("🗑 DELETE STUDENT");
        int id = Input.num("Enter Student ID to Delete");
        Student s = manager.searchStudent(id);
        if (s == null) {
            System.out.println("  ❌ Student Not Found.");
        } else {
            System.out.println("  Found: " + s.name);
            String confirm = Input.str("Confirm delete? (yes/no)");
            if (confirm.equalsIgnoreCase("yes")) {
                manager.deleteStudent(id);
                System.out.println("  ✅ Student Deleted.");
            } else {
                System.out.println("  Cancelled.");
            }
        }
        Input.pause();
    }
 
    void postNotice() {
        Input.header("📢 POST NOTICE");
        System.out.println("  Current: " + NoticeBoard.notice);
        String msg = Input.str("New Notice");
        NoticeBoard.saveNotice(msg);
        System.out.println("  ✅ Notice Updated.");
        Input.pause();
    }
 
    void viewStats() {
        Input.header("📊 SYSTEM STATS");
        System.out.println("  Total Students  : " + manager.totalStudents());
        System.out.printf("  Average CGPA    : %.2f%n", manager.avgCGPA());
        System.out.println("  Low Attendance  : " + manager.lowAttendance() + " students");
        System.out.printf("  Total Due Fee   : %.2f%n", manager.totalDue());
 
        Student top = manager.topper();
        if (top != null)
            System.out.println("  Topper          : " + top.name + " (CGPA: " + top.cgpa + ")");
 
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
 