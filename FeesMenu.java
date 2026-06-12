import java.util.*;
import java.io.*;

public class FeesMenu {
    static void show(Scanner sc, StudentManager manager) {
        while (true) {
            System.out.println("\n----- FEES COLLECTION -----");
            System.out.println("1. Search Student");
            System.out.println("2. Collect Fee");
            System.out.println("3. View History");
            System.out.println("0. Back");
            System.out.print("Choice: ");

            String c = sc.nextLine().trim();
            switch (c) {
                case "1": search(sc, manager); break;
                case "2": collect(sc, manager); break;
                case "3": history(); break;
                case "0": return;
                default: System.out.println("Invalid Choice!");
            }
        }
    }

    static void search(Scanner sc, StudentManager manager) {
        System.out.print("Student ID: ");
        try {
            int id = Integer.parseInt(sc.nextLine().trim());
            Student s = manager.searchStudent(id);
            if (s == null) { System.out.println("Not Found"); return; }
            System.out.println("ID: " + s.id + " | Name: " + s.name + " | Total: " + s.totalFee + " | Paid: " + s.paidFee + " | Due: " + s.dueFee());
        } catch (Exception ex) { System.out.println("Invalid ID"); }
    }

    static void collect(Scanner sc, StudentManager manager) {
        System.out.print("Student ID: ");
        try {
            int id = Integer.parseInt(sc.nextLine().trim());
            Student s = manager.searchStudent(id);
            if (s == null) { System.out.println("Not Found"); return; }

            System.out.print("Amount: ");
            double tk = Double.parseDouble(sc.nextLine());

            System.out.print("Method (Cash/Bank/Mobile Banking/Card): ");
            String method = sc.nextLine();

            s.paidFee += tk;
            manager.saveFile();
            saveFeeRecord(s.id, s.name, tk, method);

            System.out.println("✅ Fee Collected. New Due: " + s.dueFee());
        } catch (Exception ex) { System.out.println("Invalid Input"); }
    }

    static void saveFeeRecord(int id, String name, double amt, String method) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("fees_history.txt", true));
            bw.write(id + "," + name + "," + amt + "," + method + "," + java.time.LocalDate.now());
            bw.newLine();
            bw.close();
        } catch (Exception e) { System.out.println("Fee Save Error"); }
    }

    static void history() {
        System.out.println(String.format("%-6s %-20s %-10s %-15s %-12s", "ID","NAME","AMOUNT","METHOD","DATE"));
        System.out.println("=".repeat(70));
        try {
            File f = new File("fees_history.txt");
            if (!f.exists()) { System.out.println("No history found."); return; }
            BufferedReader br = new BufferedReader(new FileReader(f));
            String line;
            while ((line = br.readLine()) != null) {
                String[] d = line.split(",");
                if (d.length >= 5)
                    System.out.println(String.format("%-6s %-20s %-10s %-15s %-12s", d[0],d[1],d[2],d[3],d[4]));
            }
            br.close();
        } catch (Exception e) { System.out.println("Error reading history"); }
    }
}