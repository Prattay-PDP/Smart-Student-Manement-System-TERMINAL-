import java.util.Scanner;

public class AccountantPanel {
    StudentManager manager;
    Scanner sc = new Scanner(System.in);

    AccountantPanel(StudentManager manager) { this.manager = manager; }

    void show() {
        while (true) {
            System.out.println("\n=== ACCOUNTANT PANEL ===");
            System.out.println("1. Search Student");
            System.out.println("2. Add Payment");
            System.out.println("0. Back");
            System.out.print("Choose: ");
            String c = sc.nextLine().trim();

            switch (c) {
                case "1": search(); break;
                case "2": addPayment(); break;
                case "0": return;
                default: System.out.println("Invalid!");
            }
        }
    }

    void search() {
        System.out.print("Student ID: ");
        try {
            int id = Integer.parseInt(sc.nextLine().trim());
            Student s = manager.searchStudent(id);
            if (s == null) { System.out.println("Not Found!"); return; }
            printFee(s);
        } catch (Exception e) { System.out.println("Invalid ID!"); }
    }

    void addPayment() {
        System.out.print("Student ID: ");
        try {
            int id = Integer.parseInt(sc.nextLine().trim());
            Student s = manager.searchStudent(id);
            if (s == null) { System.out.println("Not Found!"); return; }
            System.out.print("Payment Amount: ");
            double amount = Double.parseDouble(sc.nextLine().trim());
            s.paidFee += amount;
            manager.saveFile();
            System.out.println("Payment Added!");
            printFee(s);
        } catch (Exception e) { System.out.println("Invalid input!"); }
    }

    void printFee(Student s) {
        System.out.println("ID: "+s.id+" | Name: "+s.name+" | Total: "+s.totalFee+
            " | Paid: "+s.paidFee+" | Due: "+s.dueFee());
    }
}