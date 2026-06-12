import java.util.*;

public class AccountantMenu {
    static void show(Scanner sc, StudentManager manager) {
        while (true) {
            System.out.println("\n----- ACCOUNTANT PANEL -----");
            System.out.println("1. Search Student Account");
            System.out.println("2. Add Payment");
            System.out.println("0. Back to Login");
            System.out.print("Choice: ");

            String c = sc.nextLine().trim();
            switch (c) {
                case "1": search(sc, manager); break;
                case "2": pay(sc, manager); break;
                case "0": return;
                default: System.out.println("Invalid Choice!");
            }
        }
    }

    static void search(Scanner sc, StudentManager manager) {
        System.out.print("Enter Student ID: ");
        try {
            int id = Integer.parseInt(sc.nextLine().trim());
            Student s = manager.searchStudent(id);
            if (s == null) { System.out.println("Student Not Found"); return; }

            System.out.println(
                "ID         : " + s.id + "\n" +
                "Name       : " + s.name + "\n" +
                "Department : " + s.dept + "\n" +
                "Total Fee  : " + s.totalFee + "\n" +
                "Paid Fee   : " + s.paidFee + "\n" +
                "Due Fee    : " + s.dueFee()
            );
        } catch (Exception ex) {
            System.out.println("Enter Valid ID");
        }
    }

    static void pay(Scanner sc, StudentManager manager) {
        System.out.print("Enter Student ID: ");
        try {
            int id = Integer.parseInt(sc.nextLine().trim());
            Student s = manager.searchStudent(id);
            if (s == null) { System.out.println("Student Not Found"); return; }

            System.out.print("Enter Payment Amount: ");
            double tk = Double.parseDouble(sc.nextLine());
            s.paidFee += tk;
            manager.saveFile();

            System.out.println(
                "✅ Payment Updated\n" +
                "ID         : " + s.id + "\n" +
                "Name       : " + s.name + "\n" +
                "Total Fee  : " + s.totalFee + "\n" +
                "Paid Fee   : " + s.paidFee + "\n" +
                "Due Fee    : " + s.dueFee()
            );
        } catch (Exception ex) {
            System.out.println("❌ Invalid Input");
        }
    }
}