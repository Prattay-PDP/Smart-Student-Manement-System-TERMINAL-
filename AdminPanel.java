import java.util.Scanner;

public class AdminPanel {
    StudentManager manager;
    Scanner sc = new Scanner(System.in);

    AdminPanel(StudentManager manager) { this.manager = manager; }

    void show() {
        while (true) {
            System.out.println("\n=== ADMIN PANEL — USTC ===");
            System.out.println("1. Add Student");
            System.out.println("2. Delete Student");
            System.out.println("3. Search Student");
            System.out.println("4. All Students");
            System.out.println("5. Statistics");
            System.out.println("6. Notice Board");
            System.out.println("0. Back to Login");
            System.out.print("Choose: ");
            String c = sc.nextLine().trim();

            switch (c) {
                case "1": addStudent(); break;
                case "2": deleteStudent(); break;
                case "3": searchStudent(); break;
                case "4": viewAll(); break;
                case "5": showStats(); break;
                case "6": editNotice(); break;
                case "0": return;
                default: System.out.println("Invalid!");
            }
        }
    }

    void addStudent() {
        try {
            System.out.print("Name: "); String name = sc.nextLine();
            System.out.print("Age: "); int age = Integer.parseInt(sc.nextLine().trim());
            System.out.print("Mobile (8801xxxxxxxxx): "); String mobile = sc.nextLine();
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

            int id = manager.list.isEmpty() ? 1 :
                     manager.list.stream().mapToInt(s -> s.id).max().getAsInt() + 1;

            manager.addStudent(new Student(id, name, age, mobile, email, pass,
                                           dept, sem, sec, cgpa, tc, att, tf, pf));
            System.out.println("Student Added! ID: " + id);
        } catch (Exception e) { System.out.println("Invalid input!"); }
    }

    void deleteStudent() {
        System.out.print("Enter Student ID: ");
        try {
            int id = Integer.parseInt(sc.nextLine().trim());
            Student s = manager.searchStudent(id);
            if (s == null) { System.out.println("Not Found!"); return; }
            System.out.print("Delete " + s.name + "? (y/n): ");
            if (sc.nextLine().trim().equalsIgnoreCase("y")) {
                manager.deleteStudent(id);
                System.out.println("Deleted: " + s.name);
            }
        } catch (Exception e) { System.out.println("Invalid ID!"); }
    }

    void searchStudent() {
        System.out.print("Enter Student ID: ");
        try {
            int id = Integer.parseInt(sc.nextLine().trim());
            Student s = manager.searchStudent(id);
            if (s == null) { System.out.println("Not Found!"); return; }
            printStudent(s);
        } catch (Exception e) { System.out.println("Invalid ID!"); }
    }

    void viewAll() {
        System.out.printf("\n%-6s %-20s %-12s %-10s %-6s %-8s %-8s%n",
            "ID","NAME","DEPT","SEMESTER","CGPA","ATT%","DUE");
        System.out.println("=".repeat(76));
        for (Student s : manager.list)
            System.out.printf("%-6d %-20s %-12s %-10s %-6.2f %-8.1f %-8.0f%n",
                s.id, s.name, s.dept, s.semester, s.cgpa, s.getAttPer(), s.dueFee());
        if (manager.list.isEmpty()) System.out.println("No students.");
    }

    void showStats() {
        System.out.println("\n=== STATISTICS ===");
        System.out.println("Total Students : " + manager.totalStudents());
        System.out.printf("Average CGPA   : %.2f%n", manager.avgCGPA());
        System.out.println("Low Attendance : " + manager.lowAttendance());
        System.out.printf("Total Due Fee  : %.2f ৳%n", manager.totalDue());
        if (manager.topper() != null)
            System.out.println("Top Student    : " + manager.topper().name + " (CGPA: " + manager.topper().cgpa + ")");
    }

    void editNotice() {
        System.out.println("Current Notice: " + NoticeBoard.notice);
        System.out.print("New Notice: ");
        String n = sc.nextLine().trim();
        if (!n.isEmpty()) { NoticeBoard.saveNotice(n); System.out.println("Notice Updated!"); }
    }

    void printStudent(Student s) {
        System.out.println("\n--- STUDENT INFO ---");
        System.out.println("ID         : " + s.id);
        System.out.println("Name       : " + s.name);
        System.out.println("Age        : " + s.age);
        System.out.println("Department : " + s.dept);
        System.out.println("Semester   : " + s.semester);
        System.out.println("Section    : " + s.section);
        System.out.println("Mobile     : " + s.mobile);
        System.out.println("Email      : " + s.email);
        System.out.printf("CGPA       : %.2f%n", s.cgpa);
        System.out.printf("Attendance : %.2f%%%n", s.getAttPer());
        System.out.println("Total Fee  : " + s.totalFee);
        System.out.println("Paid Fee   : " + s.paidFee);
        System.out.println("Due Fee    : " + s.dueFee());
        System.out.println("Status     : " + s.status);
    }
}