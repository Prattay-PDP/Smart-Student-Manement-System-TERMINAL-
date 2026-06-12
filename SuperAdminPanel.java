import java.util.Scanner;

public class SuperAdminPanel {
    StudentManager studentManager;
    TeacherManager teacherManager;
    Scanner sc = new Scanner(System.in);

    SuperAdminPanel(StudentManager sm, TeacherManager tm) {
        this.studentManager = sm;
        this.teacherManager = tm;
    }

    void show() {
        while (true) {
            System.out.println("\n=== SUPER ADMIN PANEL — USTC ===");
            System.out.println("1. All Students");
            System.out.println("2. Search Student");
            System.out.println("3. Add Student");
            System.out.println("4. All Teachers");
            System.out.println("5. Add Teacher");
            System.out.println("6. Delete Teacher");
            System.out.println("7. Notice Board");
            System.out.println("8. Statistics");
            System.out.println("0. Logout");
            System.out.print("Choose: ");
            String c = sc.nextLine().trim();

            switch (c) {
                case "1": viewAllStudents(); break;
                case "2": searchStudent(); break;
                case "3": addStudent(); break;
                case "4": viewAllTeachers(); break;
                case "5": addTeacher(); break;
                case "6": deleteTeacher(); break;
                case "7": editNotice(); break;
                case "8": showStats(); break;
                case "0": return;
                default: System.out.println("Invalid!");
            }
        }
    }

    void viewAllStudents() {
        System.out.printf("\n%-6s %-20s %-12s %-10s %-6s %-8s %-8s%n",
            "ID","NAME","DEPT","SEMESTER","CGPA","ATT%","DUE");
        System.out.println("=".repeat(76));
        for (Student s : studentManager.list)
            System.out.printf("%-6d %-20s %-12s %-10s %-6.2f %-8.1f %-8.0f%n",
                s.id, s.name, s.dept, s.semester, s.cgpa, s.getAttPer(), s.dueFee());
        if (studentManager.list.isEmpty()) System.out.println("No students.");
    }

    void searchStudent() {
        System.out.print("Enter Student ID: ");
        try {
            int id = Integer.parseInt(sc.nextLine().trim());
            Student s = studentManager.searchStudent(id);
            if (s == null) { System.out.println("Not Found!"); return; }
            System.out.println("ID: "+s.id+" | Name: "+s.name+" | Dept: "+s.dept+
                " | CGPA: "+s.cgpa+" | Att: "+String.format("%.1f",s.getAttPer())+
                "% | Due: "+s.dueFee()+" | Status: "+s.status);
        } catch (Exception e) { System.out.println("Invalid ID!"); }
    }

    void addStudent() {
        try {
            System.out.print("Name: "); String name = sc.nextLine();
            System.out.print("Age: "); int age = Integer.parseInt(sc.nextLine().trim());
            System.out.print("Mobile: "); String mobile = sc.nextLine();
            System.out.print("Email: "); String email = sc.nextLine();
            System.out.print("Password: "); String pass = sc.nextLine();
            System.out.print("Department: "); String dept = sc.nextLine();
            System.out.print("Semester: "); String sem = sc.nextLine();
            System.out.print("Section: "); String sec = sc.nextLine();
            System.out.print("CGPA: "); double cgpa = Double.parseDouble(sc.nextLine().trim());
            System.out.print("Total Classes: "); int tc = Integer.parseInt(sc.nextLine().trim());
            System.out.print("Attended: "); int att = Integer.parseInt(sc.nextLine().trim());
            System.out.print("Total Fee: "); double tf = Double.parseDouble(sc.nextLine().trim());
            System.out.print("Paid Fee: "); double pf = Double.parseDouble(sc.nextLine().trim());
            int id = studentManager.list.isEmpty() ? 1 :
                     studentManager.list.stream().mapToInt(s -> s.id).max().getAsInt() + 1;
            studentManager.addStudent(new Student(id, name, age, mobile, email, pass,
                                                  dept, sem, sec, cgpa, tc, att, tf, pf));
            System.out.println("Student Added! ID: " + id);
        } catch (Exception e) { System.out.println("Invalid input!"); }
    }

    void viewAllTeachers() {
        System.out.printf("\n%-5s %-20s %-12s %-16s %-20s %-15s%n",
            "ID","NAME","DEPT","MOBILE","EMAIL","SUBJECT");
        System.out.println("=".repeat(90));
        for (Teacher t : teacherManager.list)
            System.out.printf("%-5d %-20s %-12s %-16s %-20s %-15s%n",
                t.id, t.name, t.dept, t.mobile, t.email, t.subject);
        if (teacherManager.list.isEmpty()) System.out.println("No teachers.");
    }

    void addTeacher() {
        try {
            System.out.print("Name: "); String name = sc.nextLine();
            System.out.print("Department: "); String dept = sc.nextLine();
            System.out.print("Mobile: "); String mobile = sc.nextLine();
            System.out.print("Email: "); String email = sc.nextLine();
            System.out.print("Password: "); String pass = sc.nextLine();
            System.out.print("Subject: "); String subject = sc.nextLine();
            int id = teacherManager.nextId();
            teacherManager.addTeacher(new Teacher(id, name, dept, mobile, email, pass, subject));
            System.out.println("Teacher Added! ID: " + id);
        } catch (Exception e) { System.out.println("Invalid input!"); }
    }

    void deleteTeacher() {
        System.out.print("Enter Teacher ID: ");
        try {
            int id = Integer.parseInt(sc.nextLine().trim());
            Teacher t = teacherManager.searchTeacher(id);
            if (t == null) { System.out.println("Not Found!"); return; }
            System.out.print("Delete " + t.name + "? (y/n): ");
            if (sc.nextLine().trim().equalsIgnoreCase("y")) {
                teacherManager.deleteTeacher(id);
                System.out.println("Deleted: " + t.name);
            }
        } catch (Exception e) { System.out.println("Invalid ID!"); }
    }

    void editNotice() {
        System.out.println("Current: " + NoticeBoard.notice);
        System.out.print("New Notice: ");
        String n = sc.nextLine().trim();
        if (!n.isEmpty()) { NoticeBoard.saveNotice(n); System.out.println("Updated!"); }
    }

    void showStats() {
        System.out.println("\n=== SYSTEM STATISTICS ===");
        System.out.println("Total Students : " + studentManager.totalStudents());
        System.out.println("Total Teachers : " + teacherManager.totalTeachers());
        System.out.printf("Average CGPA   : %.2f%n", studentManager.avgCGPA());
        System.out.println("Low Attendance : " + studentManager.lowAttendance());
        System.out.printf("Total Due Fee  : %.2f ৳%n", studentManager.totalDue());
        if (studentManager.topper() != null)
            System.out.println("Top Student    : " + studentManager.topper().name +
                " (CGPA: " + studentManager.topper().cgpa + ")");
    }
}