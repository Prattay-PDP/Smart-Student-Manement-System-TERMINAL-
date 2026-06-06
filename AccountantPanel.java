public class AccountantPanel {
 
    StudentManager manager;
 
    AccountantPanel(StudentManager manager) {
        this.manager = manager;
    }
 
    void show() {
        while (true) {
            Input.header("💰 ACCOUNTANT PANEL");
            System.out.println("  1. View Student Fee Info");
            System.out.println("  2. Add Payment");
            System.out.println("  3. View All Due Fees");
            System.out.println("  0. Logout");
            Input.line();
 
            String choice = Input.str("Choose");
 
            switch (choice) {
                case "1": viewFee();    break;
                case "2": addPayment(); break;
                case "3": viewAllDue(); break;
                case "0":
                    System.out.println("\n  Logged out.\n");
                    return;
                default:
                    System.out.println("  ❌ Invalid choice.");
            }
        }
    }
 
    void viewFee() {
        Input.header("🔍 VIEW FEE INFO");
        int id = Input.num("Enter Student ID");
        Student s = manager.searchStudent(id);
        if (s == null) {
            System.out.println("  ❌ Student Not Found.");
        } else {
            printFee(s);
        }
        Input.pause();
    }
 
    void addPayment() {
        Input.header("💵 ADD PAYMENT");
        int id = Input.num("Enter Student ID");
        Student s = manager.searchStudent(id);
        if (s == null) {
            System.out.println("  ❌ Student Not Found.");
        } else {
            System.out.println("  Name     : " + s.name);
            System.out.println("  Due Fee  : " + s.dueFee());
            double amount = Input.decimal("Payment Amount");
            s.paidFee += amount;
            s.updateStatus();
            manager.saveFile();
            System.out.println("\n  ✅ Payment Added Successfully!");
            printFee(s);
        }
        Input.pause();
    }
 
    void viewAllDue() {
        Input.header("📋 ALL DUE FEES");
        if (manager.list.isEmpty()) {
            System.out.println("  No students found.");
        } else {
            for (Student s : manager.list) {
                if (s.dueFee() > 0) {
                    System.out.printf("  ID: %-6d | %-20s | Due: %.2f%n",
                        s.id, s.name, s.dueFee());
                }
            }
            System.out.printf("%n  Total System Due: %.2f%n", manager.totalDue());
        }
        Input.pause();
    }
 
    void printFee(Student s) {
        System.out.println();
        System.out.println("  ID        : " + s.id);
        System.out.println("  Name      : " + s.name);
        System.out.println("  Dept      : " + s.dept);
        System.out.println("  Total Fee : " + s.totalFee);
        System.out.println("  Paid Fee  : " + s.paidFee);
        System.out.println("  Due Fee   : " + s.dueFee());
        System.out.println("  Status    : " + s.status);
    }
}
 