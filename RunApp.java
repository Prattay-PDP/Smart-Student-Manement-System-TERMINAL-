import java.util.*;

public class RunApp {
    static StudentManager manager = new StudentManager();
    static TeacherManager teacherManager = new TeacherManager();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        NoticeBoard.loadNotice();
        mainMenu();
    }

    static void mainMenu() {
        while (true) {
            System.out.println("\n=====================================");
            System.out.println("   USTC STUDENT MANAGEMENT SYSTEM");
            System.out.println("=====================================");
            System.out.println("1. Super Admin");
            System.out.println("2. Admin");
            System.out.println("3. Student");
            System.out.println("4. Teacher");
            System.out.println("5. Parent");
            System.out.println("6. Librarian");
            System.out.println("7. Receptionist");
            System.out.println("8. Accountant");
            System.out.println("9. Annual Calendar (View)");
            System.out.println("10. Online Courses (View)");
            System.out.println("11. Online Exam Results (View)");
            System.out.println("0. Exit");
            System.out.print("Enter Choice: ");

            String choice = sc.nextLine().trim();

            switch (choice) {
                case "1": superAdminLogin(); break;
                case "2": adminLogin(); break;
                case "3": studentLogin(); break;
                case "4": teacherLogin(); break;
                case "5": parentLogin(); break;
                case "6": LibrarianMenu.show(sc, manager); break;
                case "7": ReceptionistMenu.show(sc, manager); break;
                case "8": AccountantMenu.show(sc, manager); break;
                case "9": CalendarMenu.show(sc, false); break;
                case "10": CourseMenu.show(sc, manager, false); break;
                case "11": ExamMenu.show(sc, manager, false); break;
                case "0": System.out.println("Exiting... Goodbye!"); System.exit(0); break;
                default: System.out.println("Invalid Choice!");
            }
        }
    }

    static String[] loginMethod() {
        System.out.println("Login Method: 1. Mobile  2. Email");
        System.out.print("Choose (1/2): ");
        String m = sc.nextLine().trim();
        String input;

        if (m.equals("1")) {
            System.out.print("Enter 9 digits (after 8801): ");
            String raw = sc.nextLine().trim();
            if (!raw.matches("\\d{9}")) {
                System.out.println("Invalid! Must be 9 digits.");
                return null;
            }
            input = "8801" + raw;
        } else {
            System.out.print("Enter Email: ");
            input = sc.nextLine().trim();
        }

        System.out.print("Enter Password: ");
        String pass = sc.nextLine().trim();

        return new String[]{input, pass};
    }

    static void superAdminLogin() {
        String[] data = loginMethod();
        if (data == null) return;

        if ((data[0].equals("8801962367310") || data[0].equals("superadmin@ustc.ac.bd")) && data[1].equals("super1234")) {
            SuperAdminMenu.show(sc, manager, teacherManager);
        } else {
            System.out.println("❌ Wrong Super Admin Credentials!");
        }
    }

    static void adminLogin() {
        String[] data = loginMethod();
        if (data == null) return;

        if ((data[0].equals("8801962367310") || data[0].equals("admin@gmail.com")) && data[1].equals("1234")) {
            AdminMenu.show(sc, manager);
        } else {
            System.out.println("❌ Wrong Admin Credentials!");
        }
    }

    static void studentLogin() {
        String[] data = loginMethod();
        if (data == null) return;

        Student s = manager.login(data[0], data[1]);
        if (s != null) {
            StudentMenu.show(sc, s, manager);
        } else {
            System.out.println("❌ Wrong Student Credentials!");
        }
    }

    static void teacherLogin() {
        String[] data = loginMethod();
        if (data == null) return;

        Teacher t = teacherManager.login(data[0], data[1]);
        if (t != null) {
            TeacherMenu.show(sc, t);
        } else if (data[0].equals("8801962367310") && data[1].equals("teacher")) {
            TeacherMenu.show(sc, null);
        } else {
            System.out.println("❌ Wrong Teacher Credentials!");
        }
    }

    static void parentLogin() {
        System.out.print("Enter Student ID: ");
        try {
            int id = Integer.parseInt(sc.nextLine().trim());
            Student s = manager.searchStudent(id);
            if (s != null) {
                ParentMenu.show(s);
            } else {
                System.out.println("❌ Student Not Found!");
            }
        } catch (Exception ex) {
            System.out.println("❌ Invalid Input");
        }
    }
}