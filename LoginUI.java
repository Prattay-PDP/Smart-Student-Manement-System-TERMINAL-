public class LoginUI {
 
    StudentManager manager;
 
    LoginUI(StudentManager manager) {
        this.manager = manager;
    }
 
    void show() {
        while (true) {
            Input.header("🏫 USTC STUDENT MANAGEMENT SYSTEM");
            System.out.println("  Select Role:");
            System.out.println("  1. Student");
            System.out.println("  2. Teacher");
            System.out.println("  3. Admin");
            System.out.println("  4. Accountant");
            System.out.println("  5. Receptionist");
            System.out.println("  6. Librarian");
            System.out.println("  7. Parent");
            System.out.println("  0. Exit");
            Input.line();
 
            String role = Input.str("Choose Role");
 
            switch (role) {
                case "1": studentLogin();      break;
                case "2": staffLogin("teacher",      "📚 Teacher");      break;
                case "3": staffLogin("admin",        "👨‍💼 Admin");        break;
                case "4": staffLogin("accountant",   "💰 Accountant");   break;
                case "5": staffLogin("receptionist", "👩‍💼 Receptionist"); break;
                case "6": staffLogin("librarian",    "📚 Librarian");    break;
                case "7": parentLogin();       break;
                case "0":
                    System.out.println("\n  Goodbye! 👋\n");
                    System.exit(0);
                default:
                    System.out.println("  ❌ Invalid choice.");
            }
        }
    }
 
    
    void studentLogin() {
        Input.header("🎓 STUDENT LOGIN");
        String input = Input.str("Mobile / Email");
        String pass  = Input.str("Password");
 
        Student s = manager.login(input, pass);
        if (s == null) {
            System.out.println("  ❌ Invalid credentials.");
            Input.pause();
        } else {
            System.out.println("  ✅ Welcome, " + s.name + "!");
            new StudentPanel(s).show();
        }
    }
 
   
    void staffLogin(String requiredRole, String label) {
        Input.header(label + " LOGIN");
        String mobile = Input.str("Mobile");
        String pass   = Input.str("Password");
 
        String role = manager.staffLogin(mobile, pass);
 
        if (role == null || !role.equalsIgnoreCase(requiredRole)) {
            System.out.println("  ❌ Invalid credentials.");
            Input.pause();
            return;
        }
 
        System.out.println("  ✅ Welcome, " + label + "!");
 
        switch (requiredRole.toLowerCase()) {
            case "teacher":
                new TeacherPanel(manager).show();
                break;
            case "admin":
                new AdminPanel(manager).show();
                break;
            case "accountant":
                new AccountantPanel(manager).show();
                break;
            case "receptionist":
                new ReceptionistPanel(manager).show();
                break;
            case "librarian":
                new LibrarianPanel(manager).show();
                break;
        }
    }
 
    
    void parentLogin() {
        Input.header("👨‍👩‍👦 PARENT LOGIN");
        System.out.println("  (Parents login using child's Student ID)");
        int id = Input.num("Child's Student ID");
        Student s = manager.searchStudent(id);
        if (s == null) {
            System.out.println("  ❌ Student ID not found.");
            Input.pause();
        } else {
            System.out.println("  ✅ Welcome, Parent of " + s.name + "!");
            new ParentPanel(manager).show();
        }
    }
}
 