import java.util.Scanner;

public class AppController {
    static StudentManager manager = new StudentManager();
    static TeacherManager teacherManager = new TeacherManager();
    static Scanner sc = new Scanner(System.in);

    public static void start() {
        while (true) {
            System.out.println("\n╔══════════════════════════════════════╗");
            System.out.println("║   USTC STUDENT MANAGEMENT SYSTEM     ║");
            System.out.println("║  University of Science & Technology   ║");
            System.out.println("╚══════════════════════════════════════╝");
            System.out.println("1. Super Admin");
            System.out.println("2. Admin");
            System.out.println("3. Student");
            System.out.println("4. Teacher");
            System.out.println("5. Parent");
            System.out.println("6. Librarian");
            System.out.println("7. Receptionist");
            System.out.println("8. Accountant");
            System.out.println("9. Exit");
            System.out.print("Choose: ");

            String choice = sc.nextLine().trim();

            switch (choice) {
                case "1": superAdminLogin(); break;
                case "2": adminLogin(); break;
                case "3": studentLogin(); break;
                case "4": teacherLogin(); break;
                case "5": parentLogin(); break;
                case "6": librarianLogin(); break;
                case "7": receptionistLogin(); break;
                case "8": accountantLogin(); break;
                case "9": System.out.println("Goodbye!"); System.exit(0); break;
                default: System.out.println("Invalid choice!");
            }
        }
    }

    static String[] loginMethod() {
        System.out.println("Login: 1=Mobile  2=Email");
        System.out.print("Choose: ");
        String method = sc.nextLine().trim();

        String input;
        if (method.equals("1")) {
            System.out.print("Enter mobile (8801xxxxxxxxx): ");
            input = sc.nextLine().trim();
        } else {
            System.out.print("Enter email: ");
            input = sc.nextLine().trim();
        }

        System.out.print("Enter password: ");
        String pass = sc.nextLine().trim();

        return new String[]{input, pass};
    }

    static void superAdminLogin() {
        String[] d = loginMethod();
        if ((d[0].equals("8801962367310") || d[0].equals("superadmin@ustc.ac.bd")) && d[1].equals("super1234")) {
            new SuperAdminPanel(manager, teacherManager).show();
        } else {
            System.out.println("Wrong Super Admin credentials!");
        }
    }

    static void adminLogin() {
        String[] d = loginMethod();
        if ((d[0].equals("8801962367310") || d[0].equals("admin@gmail.com")) && d[1].equals("1234")) {
            new AdminPanel(manager).show();
        } else {
            System.out.println("Wrong Admin credentials!");
        }
    }

    static void studentLogin() {
        String[] d = loginMethod();
        Student s = manager.login(d[0], d[1]);
        if (s != null) {
            new StudentPanel(s, manager).show();
        } else {
            System.out.println("Wrong Student credentials!");
        }
    }

    static void teacherLogin() {
        String[] d = loginMethod();
        Teacher t = teacherManager.login(d[0], d[1]);
        if (t != null) {
            new TeacherPanel().show();
        } else if (d[0].equals("8801962367310") && d[1].equals("teacher")) {
            new TeacherPanel().show();
        } else {
            System.out.println("Wrong Teacher credentials!");
        }
    }

    static void parentLogin() {
        System.out.print("Enter Student ID: ");
        try {
            int id = Integer.parseInt(sc.nextLine().trim());
            Student s = manager.searchStudent(id);
            if (s != null) {
                new ParentPanel(s).show();
            } else {
                System.out.println("Student Not Found!");
            }
        } catch (Exception e) {
            System.out.println("Invalid input!");
        }
    }

    static void librarianLogin() {
        String[] d = loginMethod();
        if (d[0].equals("8801962367310") && d[1].equals("library")) {
            new LibrarianPanel().show();
        } else {
            System.out.println("Wrong Librarian credentials!");
        }
    }

    static void receptionistLogin() {
        String[] d = loginMethod();
        if (d[0].equals("8801962367310") && d[1].equals("1234")) {
            new ReceptionistPanel(manager).show();
        } else {
            System.out.println("Wrong Receptionist credentials!");
        }
    }

    static void accountantLogin() {
        String[] d = loginMethod();
        if (d[0].equals("8801962367310") && d[1].equals("1234")) {
            new AccountantPanel(manager).show();
        } else {
            System.out.println("Wrong Accountant credentials!");
        }
    }
}