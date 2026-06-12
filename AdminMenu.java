import java.util.*;

public class AdminMenu {
    static void show(Scanner sc, StudentManager manager) {
        while (true) {
            System.out.println("\n----- ADMIN PANEL -----");
            System.out.println("1. Add Student");
            System.out.println("2. Delete Student");
            System.out.println("3. Search Student");
            System.out.println("4. View All Students");
            System.out.println("5. Statistics");
            System.out.println("6. Edit Notice Board");
            System.out.println("0. Back to Login");
            System.out.print("Choice: ");

            String c = sc.nextLine().trim();
            switch (c) {
                case "1": addStudent(sc, manager); break;
                case "2": deleteStudent(sc, manager); break;
                case "3": searchStudent(sc, manager); break;
                case "4": viewAll(manager); break;
                case "5": stats(manager); break;
                case "6": editNotice(sc); break;
                case "0": return;
                default: System.out.println("Invalid Choice!");
            }
        }
    }

    static void addStudent(Scanner sc, StudentManager manager) {
        try {
            System.out.print("Name: "); String name = sc.nextLine();
            System.out.print("Age: "); int age = Integer.parseInt(sc.nextLine());
            System.out.print("Mobile (8801xxxxxxxxx): "); String mobile = sc.nextLine();
            System.out.print("Email: "); String email = sc.nextLine();
            System.out.print("Password: "); String pass = sc.nextLine();
            System.out.print("Department: "); String dept = sc.nextLine();
            System.out.print("Semester: "); String sem = sc.nextLine();
            System.out.print("Section: "); String sec = sc.nextLine();
            System.out.print("CGPA: "); double cgpa = Double.parseDouble(sc.nextLine());
            System.out.print("Total Classes: "); int tc = Integer.parseInt(sc.nextLine());
            System.out.print("Attended: "); int att = Integer.parseInt(sc.nextLine());
            System.out.print("Total Fee: "); double tf = Double.parseDouble(sc.nextLine());
            System.out.print("Paid Fee: "); double pf = Double.parseDouble(sc.nextLine());

            int id = manager.list.isEmpty() ? 1 :
                     manager.list.stream().mapToInt(s -> s.id).max().getAsInt() + 1;

            Student s = new Student(id, name, age, mobile, email, pass, dept, sem, sec, cgpa, tc, att, tf, pf);
            manager.addStudent(s);
            System.out.println("✅ Student Added! ID: " + id);
        } catch (Exception ex) {
            System.out.println("❌ Invalid Input!");
        }
    }

    static void deleteStudent(Scanner sc, StudentManager manager) {
        System.out.print("Enter Student ID to Delete: ");
        try {
            int id = Integer.parseInt(sc.nextLine().trim());
            Student s = manager.searchStudent(id);
            if (s == null) { System.out.println("Student Not Found!"); return; }

            System.out.print("Confirm Delete " + s.name + "? (y/n): ");
            String c = sc.nextLine().trim();
            if (c.equalsIgnoreCase("y")) {
                manager.deleteStudent(id);
                System.out.println("✅ Student Deleted: " + s.name);
            }
        } catch (Exception ex) {
            System.out.println("Invalid ID!");
        }
    }

    static void searchStudent(Scanner sc, StudentManager manager) {
        System.out.print("Enter Student ID: ");
        try {
            int id = Integer.parseInt(sc.nextLine().trim());
            Student s = manager.searchStudent(id);
            if (s == null) { System.out.println("Student Not Found!"); return; }
            printStudent(s);
        } catch (Exception ex) {
            System.out.println("Invalid ID!");
        }
    }

    static void printStudent(Student s) {
        System.out.println(
            "ID         : " + s.id + "\n" +
            "Name       : " + s.name + "\n" +
            "Age        : " + s.age + "\n" +
            "Department : " + s.dept + "\n" +
            "Semester   : " + s.semester + "\n" +
            "Section    : " + s.section + "\n" +
            "Mobile     : " + s.mobile + "\n" +
            "Email      : " + s.email + "\n" +
            "CGPA       : " + s.cgpa + "\n" +
            "Attendance : " + String.format("%.2f", s.getAttPer()) + "%\n" +
            "Total Fee  : " + s.totalFee + "\n" +
            "Paid Fee   : " + s.paidFee + "\n" +
            "Due Fee    : " + s.dueFee() + "\n" +
            "Status     : " + s.status
        );
    }

    static void viewAll(StudentManager manager) {
        System.out.println(String.format("%-6s %-20s %-12s %-10s %-6s %-8s %-8s",
                "ID", "NAME", "DEPT", "SEMESTER", "CGPA", "ATT%", "DUE"));
        System.out.println("=".repeat(80));
        for (Student s : manager.list) {
            System.out.println(String.format("%-6d %-20s %-12s %-10s %-6.2f %-8.1f %-8.0f",
                    s.id, s.name, s.dept, s.semester, s.cgpa, s.getAttPer(), s.dueFee()));
        }
        if (manager.list.isEmpty()) System.out.println("No students found.");
    }

    static void stats(StudentManager manager) {
        System.out.println("Total Students  : " + manager.totalStudents());
        System.out.println("Average CGPA    : " + String.format("%.2f", manager.avgCGPA()));
        System.out.println("Low Attendance  : " + manager.lowAttendance() + " students");
        System.out.println("Total Due Fee   : " + String.format("%.2f", manager.totalDue()));
        if (manager.topper() != null)
            System.out.println("Top Student     : " + manager.topper().name + " (CGPA: " + manager.topper().cgpa + ")");
    }

    static void editNotice(Scanner sc) {
        System.out.println("Current Notice: " + NoticeBoard.notice);
        System.out.print("New Notice: ");
        String msg = sc.nextLine();
        NoticeBoard.saveNotice(msg);
        System.out.println("✅ Notice Updated!");
    }
}