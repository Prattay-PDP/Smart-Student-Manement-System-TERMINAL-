import java.util.*;

public class SuperAdminMenu {
    static void show(Scanner sc, StudentManager studentManager, TeacherManager teacherManager) {
        while (true) {
            System.out.println("\n===== SUPER ADMIN PANEL =====");
            System.out.println("1. All Students");
            System.out.println("2. Search Student");
            System.out.println("3. Add Student");
            System.out.println("4. All Teachers");
            System.out.println("5. Add Teacher");
            System.out.println("6. Delete Teacher");
            System.out.println("7. Fees Collection");
            System.out.println("8. Income/Expense");
            System.out.println("9. Reports");
            System.out.println("10. Online Course");
            System.out.println("11. Online Exam");
            System.out.println("12. Behaviour/Result");
            System.out.println("13. HR / Staff");
            System.out.println("14. CV/Certificates");
            System.out.println("15. Annual Calendar");
            System.out.println("16. Notice Board");
            System.out.println("17. Statistics");
            System.out.println("0. Logout");
            System.out.print("Choice: ");

            String c = sc.nextLine().trim();
            switch (c) {
                case "1": AdminMenu.viewAll(studentManager); break;
                case "2": AdminMenu.searchStudent(sc, studentManager); break;
                case "3": AdminMenu.addStudent(sc, studentManager); break;
                case "4": viewAllTeachers(teacherManager); break;
                case "5": addTeacher(sc, teacherManager); break;
                case "6": deleteTeacher(sc, teacherManager); break;
                case "7": FeesMenu.show(sc, studentManager); break;
                case "8": FinanceMenu.show(sc); break;
                case "9": ReportMenu.show(sc, studentManager); break;
                case "10": CourseMenu.show(sc, studentManager, true); break;
                case "11": ExamMenu.show(sc, studentManager, true); break;
                case "12": BehaviourMenu.show(sc, studentManager); break;
                case "13": StaffMenu.show(sc); break;
                case "14": CVMenu.show(sc, studentManager); break;
                case "15": CalendarMenu.show(sc, true); break;
                case "16": AdminMenu.editNotice(sc); break;
                case "17": stats(studentManager, teacherManager); break;
                case "0": return;
                default: System.out.println("Invalid Choice!");
            }
        }
    }

    static void viewAllTeachers(TeacherManager teacherManager) {
        System.out.println(String.format("%-5s %-20s %-12s %-16s %-20s %-15s",
                "ID", "NAME", "DEPT", "MOBILE", "EMAIL", "SUBJECT"));
        System.out.println("=".repeat(90));
        for (Teacher t : teacherManager.list) {
            System.out.println(String.format("%-5d %-20s %-12s %-16s %-20s %-15s",
                    t.id, t.name, t.dept, t.mobile, t.email, t.subject));
        }
        if (teacherManager.list.isEmpty()) System.out.println("No teachers found.");
    }

    static void addTeacher(Scanner sc, TeacherManager teacherManager) {
        try {
            System.out.print("Name: "); String name = sc.nextLine();
            System.out.print("Department: "); String dept = sc.nextLine();
            System.out.print("Mobile (8801xxxxxxxxx): "); String mobile = sc.nextLine();
            System.out.print("Email: "); String email = sc.nextLine();
            System.out.print("Password: "); String pass = sc.nextLine();
            System.out.print("Subject: "); String subject = sc.nextLine();

            int id = teacherManager.nextId();
            Teacher t = new Teacher(id, name, dept, mobile, email, pass, subject);
            teacherManager.addTeacher(t);
            System.out.println("✅ Teacher Added! ID: " + id);
        } catch (Exception ex) {
            System.out.println("❌ Invalid Input!");
        }
    }

    static void deleteTeacher(Scanner sc, TeacherManager teacherManager) {
        System.out.print("Enter Teacher ID to Delete: ");
        try {
            int id = Integer.parseInt(sc.nextLine().trim());
            Teacher t = teacherManager.searchTeacher(id);
            if (t == null) { System.out.println("Teacher Not Found!"); return; }

            System.out.print("Confirm Delete " + t.name + "? (y/n): ");
            String c = sc.nextLine().trim();
            if (c.equalsIgnoreCase("y")) {
                teacherManager.deleteTeacher(id);
                System.out.println("✅ Teacher Deleted: " + t.name);
            }
        } catch (Exception ex) {
            System.out.println("Invalid ID!");
        }
    }

    static void stats(StudentManager studentManager, TeacherManager teacherManager) {
        System.out.println("Total Students   : " + studentManager.totalStudents());
        System.out.println("Total Teachers   : " + teacherManager.totalTeachers());
        System.out.println("Average CGPA     : " + String.format("%.2f", studentManager.avgCGPA()));
        System.out.println("Low Attendance   : " + studentManager.lowAttendance() + " students");
        System.out.println("Total Due Fee    : " + String.format("%.2f", studentManager.totalDue()));
        if (studentManager.topper() != null)
            System.out.println("Top Student      : " + studentManager.topper().name + " (CGPA: " + studentManager.topper().cgpa + ")");
    }
}